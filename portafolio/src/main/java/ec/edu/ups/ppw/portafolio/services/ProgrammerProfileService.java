package ec.edu.ups.ppw.portafolio.services;

import ec.edu.ups.ppw.portafolio.bussines.GestionProgrammerProfile;
import ec.edu.ups.ppw.portafolio.dto.ProgrammerProfileDTO;
import ec.edu.ups.ppw.portafolio.model.ProgrammerProfile;
import ec.edu.ups.ppw.portafolio.model.User;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.container.ContainerRequestContext;
import java.util.List;

@Path("programmer-profile")
@Produces("application/json")
@Consumes("application/json")
public class ProgrammerProfileService {

    @Inject
    private GestionProgrammerProfile gp;

    @Inject
    private ec.edu.ups.ppw.portafolio.bussines.GestionUsers gu;

    @GET
    public List<ProgrammerProfile> listar() {
        return gp.listar();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") Long id) {
        ProgrammerProfile p = gp.buscar(id);
        if (p == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Perfil no existe").build();
        }
        return Response.ok(p).build();
    }

    @GET
    @Path("by-user/{userId}")
    public Response getByUserId(@PathParam("userId") Long userId) {
        ProgrammerProfile p = gp.buscarPorUserId(userId);
        if (p == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Perfil no existe").build();
        }
        return Response.ok(p).build();
    }

    // CREAR (Solo ADMIN)
    @POST
    public Response crear(ProgrammerProfileDTO dto, @Context ContainerRequestContext ctx) {

        try {
            String role = (String) ctx.getProperty("role");
            if (!"ADMIN".equals(role)) {
                return Response.status(Response.Status.FORBIDDEN).entity("No autorizado").build();
            }

            User user = gu.getUser(dto.getUserId());
            if (user == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Usuario no existe").build();
            }

            ProgrammerProfile p = new ProgrammerProfile();
            p.setBio(dto.getBio());
            p.setExperienceYears(dto.getExperienceYears());
            p.setPhotoUrl(dto.getPhotoUrl());
            p.setSocialLinks(dto.getSocialLinks());
            p.setUser(user);

            gp.guardar(p);
            return Response.status(Response.Status.CREATED).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }


    // ACTUALIZAR (ADMIN o PROGRAMADOR dueño del perfil)
    @PUT
    public Response actualizar(ProgrammerProfileDTO dto, @Context ContainerRequestContext ctx) {

        String role = (String) ctx.getProperty("role");
        ProgrammerProfile existing = gp.buscar(dto.getId());
        if (existing == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Perfil no existe").build();
        }

        if (!"ADMIN".equals(role) && !existing.getUser().getId().equals(dto.getUserId())) {
            return Response.status(Response.Status.FORBIDDEN).entity("No autorizado").build();
        }

        existing.setBio(dto.getBio());
        existing.setExperienceYears(dto.getExperienceYears());
        existing.setPhotoUrl(dto.getPhotoUrl());
        existing.setSocialLinks(dto.getSocialLinks());

        try {
            gp.actualizar(existing);
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }

        return Response.ok().build();
    }
}
