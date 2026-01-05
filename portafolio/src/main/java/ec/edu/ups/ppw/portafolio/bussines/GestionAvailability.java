package ec.edu.ups.ppw.portafolio.bussines;

import java.util.List;
import ec.edu.ups.ppw.portafolio.dao.AvailabilityDAO;
import ec.edu.ups.ppw.portafolio.model.Availability;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionAvailability {

    @Inject
    private AvailabilityDAO availabilityDAO;

    public void guardar(Availability a) {
        availabilityDAO.insert(a);
    }

    public List<Availability> listar() {
        return availabilityDAO.getAll();
    }
}
