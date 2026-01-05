package ec.edu.ups.ppw.portafolio.services;

import java.util.List;
import ec.edu.ups.ppw.portafolio.bussines.GestionEspecialidad;
import ec.edu.ups.ppw.portafolio.model.Especialidad;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;

@Path("especialidades")
public class EspecialidadService {

    @Inject
    private GestionEspecialidad ge;

    @GET
    @Produces("application/json")
    public List<Especialidad> listar() {
        return ge.listar();
    }

    @POST
    @Consumes("application/json")
    public void crear(Especialidad e) {
        ge.guardar(e);
    }
}

