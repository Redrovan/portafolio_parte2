package ec.edu.ups.ppw.portafolio.services;

import java.util.List;

import ec.edu.ups.ppw.portafolio.bussines.GestionParticipationType;
import ec.edu.ups.ppw.portafolio.model.ParticipationType;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("participation-type")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ParticipationTypeService {

    @Inject
    private GestionParticipationType gp;

    @GET
    public List<ParticipationType> listar() {
        return gp.listar();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") Long id) {
        try {
            return Response.ok(gp.buscar(id)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @POST
    public Response crear(ParticipationType pt) {
        gp.guardar(pt);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    public Response actualizar(ParticipationType pt) {
        try {
            gp.actualizar(pt);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response eliminar(@PathParam("id") Long id) {
        try {
            gp.eliminar(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
