package ec.edu.ups.ppw.portafolio.services;

import java.util.List;

import ec.edu.ups.ppw.portafolio.bussines.GestionEspecialidad;
import ec.edu.ups.ppw.portafolio.model.Especialidad;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("especialidades")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EspecialidadService {

    @Inject
    private GestionEspecialidad ge;

    @GET
    public Response listar() {
        return Response.ok(ge.listar()).build();
    }

    @POST
    public Response crear(Especialidad e) {
        try {
            ge.guardar(e);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(ex.getMessage())
                    .build();
        }
    }
}
