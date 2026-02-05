package ec.edu.ups.ppw.portafolio.services;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

@OpenAPIDefinition(
    info = @Info(
        title = "Portafolio API",
        version = "1.0",
        description = "Documentación de servicios REST del sistema Portafolio"
    ),
    servers = {
        @Server(url = "http://localhost:8080/portafolio")
    }
)
public class OpenApiConfig {
}
