package ec.edu.ups.ppw.portafolio.services;

import java.net.URI;
import java.util.List;

import ec.edu.ups.ppw.portafolio.bussines.GestionAppointment;
import ec.edu.ups.ppw.portafolio.model.Appointment;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("appointments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AppointmentService {

    @Inject
    private GestionAppointment ga;

    // LISTAR
    @GET
    public Response listar() {
        List<Appointment> listado = ga.listar();
        return Response.ok(listado).build();
    }

    // OBTENER POR ID
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

    // CREAR
    @POST
    public Response crearAppointment(Appointment appointment, @Context UriInfo uriInfo) {
        try {
            ga.guardar(appointment);

            URI location = uriInfo.getAbsolutePathBuilder()
                    .path(String.valueOf(appointment.getId()))
                    .build();

            return Response.created(location)
                    .entity(appointment)
                    .build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiError(
                            500,
                            "Error interno",
                            e.getMessage()))
                    .build();
        }
    }

    // ACTUALIZAR
    @PUT
    public Response actualizarAppointment(Appointment appointment) {
        try {
            ga.actualizar(appointment);
            return Response.ok(appointment).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiError(
                            500,
                            "Error interno",
                            e.getMessage()))
                    .build();
        }
    }

    // ELIMINAR
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
