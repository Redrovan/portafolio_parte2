package ec.edu.ups.ppw.portafolio.services;

import ec.edu.ups.ppw.portafolio.bussines.GestionProgrammerProfile;
import ec.edu.ups.ppw.portafolio.model.ProgrammerProfile;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.*;
import java.util.List;


@Path("programmer-profile")
public class ProgrammerProfileService {

    @Inject
    private GestionProgrammerProfile gp;

    @GET
    @Produces("application/json")
    public List<ProgrammerProfile> listar() {
        return gp.listar();
    }
}

