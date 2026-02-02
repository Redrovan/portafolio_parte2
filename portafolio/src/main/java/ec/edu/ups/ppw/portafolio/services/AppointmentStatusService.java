package ec.edu.ups.ppw.portafolio.services;

import java.net.URI;
import java.util.List;

import ec.edu.ups.ppw.portafolio.bussines.GestionAppointmentStatus;
import ec.edu.ups.ppw.portafolio.model.AppointmentStatus;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("appointment-status")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AppointmentStatusService {

    @Inject
    private GestionAppointmentStatus gs;

    // LISTAR
    @GET
    public Response listar() {
        List<AppointmentStatus> listado = gs.listar();
        return Response.ok(listado).build();
    }

    // OBTENER POR ID
    @GET
    @Path("{id}")
    public Response getStatus(@PathParam("id") Long id) {
        try {
            AppointmentStatus s = gs.buscar(id);

            if (s == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(new ApiError(
                                404,
                                "No encontrado",
                                "AppointmentStatus con ID " + id + " no encontrado"))
                        .build();
            }

            return Response.ok(s).build();

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
    public Response crearStatus(AppointmentStatus status, @Context UriInfo uriInfo) {
        try {
            gs.guardar(status);

            URI location = uriInfo.getAbsolutePathBuilder()
                    .path(String.valueOf(status.getId()))
                    .build();

            return Response.created(location)
                    .entity(status)
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
    public Response actualizarStatus(AppointmentStatus status) {
        try {
            gs.actualizar(status);
            return Response.ok(status).build();
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
    public Response eliminarStatus(@PathParam("id") Long id) {
        try {
            gs.eliminar(id);
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
