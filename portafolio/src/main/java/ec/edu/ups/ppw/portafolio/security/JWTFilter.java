package ec.edu.ups.ppw.portafolio.security;

import io.jsonwebtoken.Claims;
import jakarta.ws.rs.container.*;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;

//@Provider
//@PreMatching
public class JWTFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String path = requestContext.getUriInfo().getPath();
        String method = requestContext.getMethod();

        if (method.equalsIgnoreCase("OPTIONS")) return;

        // Rutas públicas permitidas sin Token
        if (path.contains("auth/login") || 
            path.contains("users/portfolio") || 
            path.contains("projects/public") ||
            path.contains("users/programmers")) {
            return;
        }

        String authHeader = requestContext.getHeaderString("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Token requerido").build());
            return;
        }

        try {
            String token = authHeader.substring("Bearer ".length());
            Claims claims = JWTUtil.validateToken(token);
            requestContext.setProperty("userRole", claims.get("role"));
        } catch (Exception e) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Token inválido").build());
        }
    }
}