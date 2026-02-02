package ec.edu.ups.ppw.portafolio.services;

import java.util.List;

import ec.edu.ups.ppw.portafolio.bussines.GestionUsers;
import ec.edu.ups.ppw.portafolio.model.Role;
import ec.edu.ups.ppw.portafolio.model.User;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserService {

    @Inject
    private GestionUsers gu;

    // ===============================
    // LISTAR TODOS
    // ===============================
    @GET
    public List<User> getUsers() {
        return gu.getUsers();
    }

    // ===============================
    // OBTENER POR ID
    // ===============================
    @GET
    @Path("{id}")
    public Response getUser(@PathParam("id") Long id) {
        try {
            return Response.ok(gu.getUser(id)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Usuario no encontrado")
                    .build();
        }
    }

    // ===============================
    // CREAR USUARIO NORMAL
    // ===============================
    @POST
    public Response create(User user) {
        try {
            gu.guardar(user);
            return Response.status(Response.Status.CREATED)
                    .entity(user)
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    // ===============================
    // ACTUALIZAR POR ID  ✅ (IMPORTANTE)
    // ===============================
    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Long id, User user) {
        try {

            // aseguramos que se actualiza el correcto
            user.setId(id);

            gu.actualizar(user);

            return Response.ok(user).build();

        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    // ===============================
    // ELIMINAR
    // ===============================
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            gu.eliminar(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage())
                    .build();
        }
    }

    // ===============================
    // LISTAR PROGRAMADORES (PÚBLICO)
    // ===============================
    @GET
    @Path("programmers")
    public Response getProgrammers() {
        try {
            return Response.ok(
                    gu.getUsersByRole(Role.PROGRAMMER)
            ).build();
        } catch (Exception e) {
            return Response.serverError()
                    .entity("Error al obtener programadores")
                    .build();
        }
    }

    // ===============================
    // ADMIN → CREAR PROGRAMADOR
    // ===============================
    @POST
    @Path("programmer")
    public Response createProgrammer(User user) {
        try {

            user.setRole(Role.PROGRAMMER);
            user.setActive(true);

            gu.guardar(user);

            return Response.status(Response.Status.CREATED)
                    .entity(user)
                    .build();

        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    // ===============================
    // LISTAR POR ROL
    // ===============================
    @GET
    @Path("role/{role}")
    public Response getByRole(@PathParam("role") String role) {

        try {
            Role r = Role.valueOf(role.toUpperCase());
            return Response.ok(gu.getUsersByRole(r)).build();

        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Rol inválido")
                    .build();
        }
    }

    // ===============================
    // PORTFOLIO PÚBLICO
    // ===============================
    @GET
    @Path("portfolio/{id}")
    public Response getPublicPortfolio(@PathParam("id") Long id) {

        try {
            User u = gu.getUser(id);

            if (u.getRole() != Role.PROGRAMMER) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            return Response.ok(u).build();

        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
