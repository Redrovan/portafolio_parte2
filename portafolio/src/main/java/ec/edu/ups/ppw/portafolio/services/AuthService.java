package ec.edu.ups.ppw.portafolio.services;

import ec.edu.ups.ppw.portafolio.bussines.GestionAuth;
import ec.edu.ups.ppw.portafolio.dto.LoginDTO;
import ec.edu.ups.ppw.portafolio.dto.UserWithToken;
import ec.edu.ups.ppw.portafolio.model.User;
import ec.edu.ups.ppw.portafolio.security.JWTUtil;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthService {

    @Inject
    private GestionAuth gestionAuth;

    @POST
    @Path("/login")
    public Response login(LoginDTO dto) {
        try {
            User user = gestionAuth.loginUser(dto.getEmail(), dto.getPassword());

            String token = JWTUtil.generateToken(
                    user.getEmail(),
                    user.getRole().name()
            );

            UserWithToken response = new UserWithToken();
            response.id = user.getId();
            response.email = user.getEmail();
            response.role = user.getRole();
            response.token = token;

            return Response.ok(response).build();

        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Credenciales incorrectas")
                    .build();
        }
    }
}
