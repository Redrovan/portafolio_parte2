package ec.edu.ups.ppw.portafolio.services;

import java.util.List;
import ec.edu.ups.ppw.portafolio.bussines.GestionAvailability;
import ec.edu.ups.ppw.portafolio.model.Availability;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;

@Path("availability")
public class AvailabilityService {

    @Inject
    private GestionAvailability ga;

    @GET
    @Produces("application/json")
    public List<Availability> listar() {
        return ga.listar();
    }

    @POST
    @Consumes("application/json")
    public void crear(Availability a) {
        ga.guardar(a);
    }
}
