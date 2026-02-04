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

    // ============================
    // LISTAR TODO
    // ============================
    @GET
    public Response listar() {
        return Response.ok(ga.getAll()).build();
    }

    // ============================
    // LISTAR POR PROGRAMADOR 🔥
    // ============================
    @GET
    @Path("programmer/{id}")
    public Response listarPorProgramador(@PathParam("id") Long id) {

        try {
            List<Availability> list = ga.getByProgrammer(id);
            return Response.ok(list).build();

        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    // ============================
    // OBTENER POR ID
    // ============================
    @GET
    @Path("{id}")
    public Response get(@PathParam("id") Long id) {

        try {
            Availability a = ga.getById(id);

            if (a == null)
                return Response.status(Response.Status.NOT_FOUND).build();

            return Response.ok(a).build();

        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    // ============================
    // CREAR
    // ============================
    @POST
    public Response crear(Availability a, @Context UriInfo uriInfo) {

        try {
            ga.crear(a);

            URI uri = uriInfo.getAbsolutePathBuilder()
                    .path(String.valueOf(a.getId()))
                    .build();

            return Response.created(uri).entity(a).build();

        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    // ============================
    // ACTUALIZAR
    // ============================
    @PUT
    public Response actualizar(Availability a) {

        try {
            ga.actualizar(a);
            return Response.ok(a).build();

        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    // ============================
    // ELIMINAR
    // ============================
    @DELETE
    @Path("{id}")
    public Response eliminar(@PathParam("id") Long id) {

        try {
            ga.eliminar(id);
            return Response.noContent().build();

        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage())
                    .build();
        }
    }
}
