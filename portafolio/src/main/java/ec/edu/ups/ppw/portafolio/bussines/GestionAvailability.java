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

    public List<Availability> getAvailabilities() {
        return availabilityDAO.getAll();
    }

    public Availability getAvailability(Long id) throws Exception {
        if (id == null || id <= 0) {
            throw new Exception("Parametro Vacio o inválido");
        }
        return availabilityDAO.read(id);
    }

    public void crearAvailability(Availability availability) throws Exception {
        if (availability == null) {
            throw new Exception("Availability vacía");
        }
        if (availability.getProgrammer() == null) {
            throw new Exception("El programador es obligatorio");
        }
        availabilityDAO.insert(availability);
    }

    public void actualizarAvailability(Availability availability) throws Exception {
        if (availability == null || availability.getId() == null) {
            throw new Exception("Availability inválida");
        }
        Availability a = availabilityDAO.read(availability.getId());
        if (a == null) {
            throw new Exception("Availability no existe");
        }
        availabilityDAO.update(availability);
    }

    public void eliminarAvailability(Long id) throws Exception {
        Availability a = availabilityDAO.read(id);
        if (a == null) {
            throw new Exception("Availability no existe");
        }
        availabilityDAO.delete(id);
    }
}
