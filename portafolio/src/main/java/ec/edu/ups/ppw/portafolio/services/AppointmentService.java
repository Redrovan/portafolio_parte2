package ec.edu.ups.ppw.portafolio.services;

import java.net.URI;
import java.time.*;
import java.util.*;

import ec.edu.ups.ppw.portafolio.bussines.GestionAppointment;
import ec.edu.ups.ppw.portafolio.dao.AppointmentDAO;
import ec.edu.ups.ppw.portafolio.dao.AvailabilityDAO;
import ec.edu.ups.ppw.portafolio.dao.UserDAO;
import ec.edu.ups.ppw.portafolio.model.*;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("appointments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AppointmentService {

    @Inject
    private GestionAppointment ga;

    @Inject
    private EmailService emailService;

    @Inject
    private UserDAO userDAO;

    @Inject
    private AppointmentDAO appointmentDAO;

    @Inject
    private AvailabilityDAO availabilityDAO;

    // ===============================
    // LISTAR
    // ===============================
    @GET
    public Response listar() {
        List<Appointment> listado = ga.listar();
        return Response.ok(listado).build();
    }

    // ===============================
    // OBTENER POR ID
    // ===============================
    @GET
    @Path("{id}")
    public Response getAppointment(@PathParam("id") Long id) {
        try {
            Appointment a = ga.buscar(id);

            if (a == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(new ApiError(
                                404,
                                "No encontrado",
                                "Appointment con ID " + id + " no encontrada"))
                        .build();
            }

            return Response.ok(a).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiError(
                            500,
                            "Error interno",
                            e.getMessage()))
                    .build();
        }
    }

    // ===============================
    // 🔥 HORAS DISPONIBLES
    // ===============================
    @GET
    @Path("available")
    public Response getAvailableHours(
            @QueryParam("programmerId") Long programmerId,
            @QueryParam("date") String dateStr) {

        try {

            LocalDate date = LocalDate.parse(dateStr);
            DayOfWeek day = date.getDayOfWeek();

            String dayName = switch (day) {
                case MONDAY -> "Lunes";
                case TUESDAY -> "Martes";
                case WEDNESDAY -> "Miércoles";
                case THURSDAY -> "Jueves";
                case FRIDAY -> "Viernes";
                case SATURDAY -> "Sábado";
                case SUNDAY -> "Domingo";
            };

            Availability availability = availabilityDAO
                    .findByProgrammerAndDay(programmerId, dayName);


            if (availability == null) {
                return Response.ok(new ArrayList<>()).build();
            }

            LocalTime start = LocalTime.parse(availability.getStartTime());
            LocalTime end = LocalTime.parse(availability.getEndTime());

            // 2️⃣ Generar horas posibles
            List<String> hours = new ArrayList<>();

            LocalTime current = start;

            while (current.isBefore(end)) {
                hours.add(current.toString());
                current = current.plusHours(1);
            }

            // 3️⃣ Obtener ocupadas
            List<String> occupied = appointmentDAO
                    .findHoursByProgrammerAndDate(programmerId, date);

            // 4️⃣ Quitar ocupadas
            hours.removeAll(occupied);

            return Response.ok(hours).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    // ===============================
    // CREAR (ENVÍA CORREO AL PROGRAMADOR)
    // ===============================
    @POST
    public Response crearAppointment(Appointment appointment, @Context UriInfo uriInfo) {
        try {

            ga.guardar(appointment);

            Long programmerId = appointment.getProgrammer().getId();
            User programmer = userDAO.read(programmerId);

            if (programmer == null) {
                throw new Exception("Programador no encontrado");
            }

            emailService.enviarCorreo(
                    programmer.getEmail(),
                    "Nueva asesoría agendada",
                    "Tienes una nueva asesoría programada para el día "
                            + appointment.getDate()
                            + " a las "
                            + appointment.getTime()
            );

            URI location = uriInfo.getAbsolutePathBuilder()
                    .path(String.valueOf(appointment.getId()))
                    .build();

            return Response.created(location)
                    .entity(appointment)
                    .build();

        } catch (Exception e) {
            e.printStackTrace();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiError(
                            500,
                            "Error interno",
                            e.getMessage()))
                    .build();
        }
    }

    // ===============================
    // ACTUALIZAR
    // ===============================
    @PUT
    public Response actualizarAppointment(Appointment appointment) {
        try {

            ga.actualizar(appointment);

            String correoCliente = appointment.getClient().getEmail();
            String estado = appointment.getStatus().getName();

            emailService.enviarCorreo(
                    correoCliente,
                    "Estado de tu asesoría",
                    "Tu asesoría fue " + estado +
                            " para el día " + appointment.getDate() +
                            " a las " + appointment.getTime()
            );

            return Response.ok(appointment).build();

        } catch (Exception e) {
            e.printStackTrace();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiError(
                            500,
                            "Error interno",
                            e.getMessage()))
                    .build();
        }
    }

    // ===============================
    // ELIMINAR
    // ===============================
    @DELETE
    @Path("{id}")
    public Response eliminarAppointment(@PathParam("id") Long id) {
        try {
            ga.eliminar(id);
            return Response.noContent().build();

        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ApiError(
                            404,
                            "No encontrado",
                            e.getMessage()))
                    .build();
        }
    }
}
