package ec.edu.ups.ppw.portafolio.api;

import ec.edu.ups.ppw.portafolio.services.ProjectService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("/projects")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProjectResource {

    @Inject
    private ProjectService projectService;

    // 🔒 PRIVADO (Requiere Token)
    @GET
    @Path("/user/{id}")
    public Response getProjectsByUser(@PathParam("id") Long userId) {
        // Se cambió a listarPorUsuario para coincidir con tu ProjectService
        return projectService.listarPorUsuario(userId);
    }

    // 🌍 PUBLICO (Sin Token - Usado por el Portafolio)
    @GET
    @Path("/public/user/{id}")
    public Response getPublicProjects(@PathParam("id") Long userId) {
        try {
            // Se cambió a getPublicProjectsByUser para coincidir con tu ProjectService
            return projectService.getPublicProjectsByUser(userId);
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Proyectos no encontrados")
                    .build();
        }
    }
}