package ec.edu.ups.ppw.portafolio.services;

import java.net.URI;
import java.util.List;

import ec.edu.ups.ppw.portafolio.bussines.GestionAvailability;
import ec.edu.ups.ppw.portafolio.model.Availability;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("availability")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AvailabilityService {

    @Inject
    private GestionAvailability ga;

    @GET
    public Response getListaAvailability() {
        List<Availability> listado = ga.getAvailabilities();
        return Response.ok(listado).build();
    }

    @GET
    @Path("{id}")
    public Response getAvailability(@PathParam("id") Long id) {
        try {
            Availability a = ga.getAvailability(id);

            if (a == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(new ApiError(
                                404,
                                "No encontrado",
                                "Availability con ID " + id + " no encontrada"))
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

    @POST
    public Response crearAvailability(Availability availability, @Context UriInfo uriInfo) {
        try {
            ga.crearAvailability(availability);

            URI location = uriInfo.getAbsolutePathBuilder()
                    .path(String.valueOf(availability.getId()))
                    .build();

            return Response.created(location)
                    .entity(availability)
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

    @PUT
    public Response actualizarAvailability(Availability availability) {
        try {
            ga.actualizarAvailability(availability);
            return Response.ok(availability).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiError(
                            500,
                            "Error interno",
                            e.getMessage()))
                    .build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response eliminarAvailability(@PathParam("id") Long id) {
        try {
            ga.eliminarAvailability(id);
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
