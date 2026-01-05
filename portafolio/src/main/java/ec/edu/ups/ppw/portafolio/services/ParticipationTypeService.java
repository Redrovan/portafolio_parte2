package ec.edu.ups.ppw.portafolio.services;

import java.util.List;

import ec.edu.ups.ppw.portafolio.bussines.GestionParticipationType;
import ec.edu.ups.ppw.portafolio.model.ParticipationType;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("participation-type")
public class ParticipationTypeService {

	@Inject
    private GestionParticipationType gp;

    @GET
    @Produces("application/json")
    public List<ParticipationType> listar() {
        return gp.listar();
    }
}
