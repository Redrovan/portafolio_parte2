package ec.edu.ups.ppw.portafolio.services;

import java.net.URI;
import java.util.List;

import ec.edu.ups.ppw.portafolio.bussines.GestionProject;
import ec.edu.ups.ppw.portafolio.model.Project;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("projects")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProjectService {

    @Inject
    private GestionProject gp;

    // LISTAR TODO
    @GET
    public Response listar() {
        List<Project> listado = gp.listar();
        return Response.ok(listado).build();
    }

    // LISTAR POR USUARIO (PRIVADO - REQUIERE TOKEN)
    @GET
    @Path("user/{userId}")
    public Response listarPorUsuario(@PathParam("userId") Long userId) {
        try {
            List<Project> lista = gp.listarPorUsuario(userId);
            return Response.ok(lista).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiError(500, "Error interno", e.getMessage()))
                    .build();
        }
    }

    // ============================================================
    // PROYECTOS PUBLICOS POR USUARIO (ACCESO SIN TOKEN)
    // ============================================================
    @GET
    @Path("public/user/{userId}")
    public Response getPublicProjectsByUser(@PathParam("userId") Long userId) {
        try {
            List<Project> lista = gp.listarPorUsuario(userId);
            return Response.ok(lista).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ApiError(404, "No encontrado", e.getMessage()))
                    .build();
        }
    }

    // OBTENER POR ID
    @GET
    @Path("{id}")
    public Response getProject(@PathParam("id") Long id) {
        try {
            Project p = gp.buscar(id);
            if (p == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(new ApiError(404, "No encontrado", "Project con ID " + id + " no encontrado"))
                        .build();
            }
            return Response.ok(p).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiError(500, "Error interno", e.getMessage()))
                    .build();
        }
    }

    // CREAR
    @POST
    public Response crearProject(Project project, @Context UriInfo uriInfo) {
        try {
            gp.guardar(project);
            URI location = uriInfo.getAbsolutePathBuilder()
                    .path(String.valueOf(project.getId()))
                    .build();
            return Response.created(location)
                    .entity(project)
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiError(500, "Error interno", e.getMessage()))
                    .build();
        }
    }

    // ACTUALIZAR
    @PUT
    public Response actualizarProject(Project project) {
        try {
            gp.actualizar(project);
            return Response.ok(project).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiError(500, "Error interno", e.getMessage()))
                    .build();
        }
    }

    // ELIMINAR
    @DELETE
    @Path("{id}")
    public Response eliminarProject(@PathParam("id") Long id) {
        try {
            gp.eliminar(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ApiError(404, "No encontrado", e.getMessage()))
                    .build();
        }
    }
}