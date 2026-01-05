package ec.edu.ups.ppw.portafolio.services;

import java.net.URI;
import java.util.List;

import ec.edu.ups.ppw.portafolio.bussines.GestionPersonas;
import ec.edu.ups.ppw.portafolio.model.Persona;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("persona")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonaService {

    @Inject
    private GestionPersonas gp;

    @GET
    public Response getListaPersona() {
        List<Persona> listado = gp.getPersonas();
        return Response.ok(listado).build();
    }

    @GET
    @Path("{id}")
    public Response getPersona(@PathParam("id") String cedula) {
        try {
            Persona p = gp.getPersona(cedula);

            if (p == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(new ApiError(
                                404,
                                "No encontrado",
                                "Persona con ID " + cedula + " no encontrada"))
                        .build();
            }

            return Response.ok(p).build();

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
    public Response crearPersona(Persona persona, @Context UriInfo uriInfo) {
        try {
            gp.crearPersona(persona);

            URI location = uriInfo.getAbsolutePathBuilder()
                    .path(persona.getCedula())
                    .build();

            return Response.created(location)
                    .entity(persona)
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
    public Response actualizarPersona(Persona persona) {
        try {
            gp.actualizarPersona(persona);
            return Response.ok(persona).build();
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
    public Response eliminarPersona(@PathParam("id") String cedula) {
        try {
            gp.eliminarPersona(cedula);
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
