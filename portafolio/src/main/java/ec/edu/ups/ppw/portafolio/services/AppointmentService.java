package ec.edu.ups.ppw.portafolio.services;

import java.net.URI;
import java.time.*;
import java.time.format.DateTimeFormatter;
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

    //  WhatsApp
    @Inject
    private WhatsAppService whatsappService;

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
        return Response.ok(ga.listar()).build();
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
                return Response.status(404).build();
            }

            return Response.ok(a).build();

        } catch (Exception e) {
            return Response.serverError()
                    .entity("Error al buscar la asesoría")
                    .build();
        }
    }

    // ===============================
    // HORAS DISPONIBLES
    // ===============================
    @GET
    @Path("available")
    public Response getAvailableHours(
            @QueryParam("programmerId") Long programmerId,
            @QueryParam("date") String dateStr) {

        try {

            LocalDate date = LocalDate.parse(dateStr);

            String dayName = switch (date.getDayOfWeek()) {
                case MONDAY -> "Lunes";
                case TUESDAY -> "Martes";
                case WEDNESDAY -> "Miércoles";
                case THURSDAY -> "Jueves";
                case FRIDAY -> "Viernes";
                case SATURDAY -> "Sábado";
                case SUNDAY -> "Domingo";
            };

            Availability availability =
                    availabilityDAO.findByProgrammerAndDay(programmerId, dayName);

            if (availability == null)
                return Response.ok(List.of()).build();

            LocalTime start = LocalTime.parse(availability.getStartTime());
            LocalTime end = LocalTime.parse(availability.getEndTime());

            List<String> hours = new ArrayList<>();
            LocalTime current = start;

            while (current.isBefore(end)) {
                hours.add(current.toString());
                current = current.plusHours(1);
            }

            List<LocalTime> occupied =
                    appointmentDAO.findHoursByProgrammerAndDate(programmerId, date);

            hours.removeIf(h -> occupied.contains(LocalTime.parse(h)));

            return Response.ok(hours).build();

        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    // ===============================
    // CREAR CITA + EMAIL + WHATSAPP
    // ===============================
    @POST
    public Response crearAppointment(Appointment appointment,
                                     @Context UriInfo uriInfo) {

        try {

            ga.guardar(appointment);

            User programmer = userDAO.read(
                    appointment.getProgrammer().getId()
            );

            User client = userDAO.read(
                    appointment.getClient().getId()
            );

            String fechaBonita = appointment.getDate()
                    .format(DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy"));

            // ================= EMAIL =================

            String mensajeEmail = """
Hola %s 👋

Tienes una nueva asesoría agendada en PortafolioPro 🚀

📌 Cliente: %s
👨‍💻 Programador: %s
📅 Fecha: %s
⏰ Hora: %s
💻 Modalidad: %s

📝 Descripción:
%s

---------------------------------
Ingresa a tu panel para ver más detalles.

Equipo PortafolioPro 💙
""".formatted(
                    programmer.getPersona().getNombre(),
                    client.getPersona().getNombre(),
                    programmer.getPersona().getNombre(),
                    fechaBonita,
                    appointment.getTime(),
                    appointment.getMode(),
                    appointment.getComment()
            );

            emailService.enviarCorreo(
                    programmer.getEmail(),
                    "📢 Nueva asesoría agendada",
                    mensajeEmail
            );

            // ================= WHATSAPP =================
            // 👉 SIEMPRE al número sandbox

            String mensajeWhats = "Hola 👋\n\n"
                    + "Nueva asesoría creada en PortafolioPro ✅\n\n"
                    + "📅 Fecha: " + fechaBonita + "\n"
                    + "⏰ Hora: " + appointment.getTime() + "\n"
                    + "👨‍💻 Programador: " + programmer.getPersona().getNombre() + "\n"
                    + "💻 Modalidad: " + appointment.getMode() + "\n\n"
                    + "📌 Cliente: " + client.getPersona().getNombre() + "\n\n"
                    + "🚀 PortafolioPro";

            String sandboxNumber = "+593982544829";

            System.out.println("📲 Enviando WhatsApp SIEMPRE a: " + sandboxNumber);

            whatsappService.enviarWhatsApp(
                    sandboxNumber,
                    mensajeWhats
            );

            // ================= RESPONSE =================

            URI location = uriInfo.getAbsolutePathBuilder()
                    .path(String.valueOf(appointment.getId()))
                    .build();

            return Response.created(location)
                    .entity(appointment)
                    .build();

        } catch (Exception e) {

            e.printStackTrace();

            return Response.status(500)
                    .entity(e.getMessage())
                    .build();
        }
    }

    // ===============================
    // ACTUALIZAR + EMAIL
    // ===============================
    @PUT
    public Response actualizarAppointment(Appointment appointment) {

        try {

            ga.actualizar(appointment);

            User client = userDAO.read(
                    appointment.getClient().getId()
            );

            String fechaBonita = appointment.getDate()
                    .format(DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy"));

            String estado = appointment.getStatus().getName();

            String mensajeEstado = """
Hola %s 👋

Tu asesoría ha sido actualizada en PortafolioPro.

📅 Fecha: %s
⏰ Hora: %s
💻 Modalidad: %s

📌 Nuevo estado: %s

Equipo PortafolioPro 🚀
""".formatted(
                    client.getPersona().getNombre(),
                    fechaBonita,
                    appointment.getTime(),
                    appointment.getMode(),
                    estado
            );

            emailService.enviarCorreo(
                    client.getEmail(),
                    "📬 Estado de tu asesoría",
                    mensajeEstado
            );

            return Response.ok(appointment).build();

        } catch (Exception e) {
            return Response.serverError().build();
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
            return Response.status(404).build();
        }
    }

    // ===============================
    // REPORTES
    // ===============================
    @GET
    @Path("report/status")
    public Response reportByStatus() {

        List<Object[]> data = appointmentDAO.countByStatus();
        List<Map<String,Object>> result = new ArrayList<>();

        for (Object[] row : data) {
            Map<String,Object> map = new HashMap<>();
            map.put("status", row[0]);
            map.put("total", row[1]);
            result.add(map);
        }

        return Response.ok(result).build();
    }

    @GET
    @Path("report/programmer")
    public Response reportByProgrammer() {

        List<Object[]> data = appointmentDAO.countByProgrammer();
        List<Map<String,Object>> result = new ArrayList<>();

        for (Object[] row : data) {
            Map<String,Object> map = new HashMap<>();
            map.put("programmer", row[0]);
            map.put("total", row[1]);
            result.add(map);
        }

        return Response.ok(result).build();
    }

    // ===============================
    // TEST WHATSAPP
    // ===============================
    @GET
    @Path("test-whatsapp")
    public String testWhatsapp() {

        whatsappService.enviarWhatsApp(
                "+593982544829",
                "🔥 Prueba directa desde backend Java"
        );

        return "enviado";
    }

}
