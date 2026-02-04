package ec.edu.ups.ppw.portafolio.bussines;

import java.util.List;

import ec.edu.ups.ppw.portafolio.dao.AvailabilityDAO;
import ec.edu.ups.ppw.portafolio.model.Availability;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionAvailability {

    @Inject
    private AvailabilityDAO dao;

    public List<Availability> getAll() {
        return dao.getAll();
    }

    public List<Availability> getByProgrammer(Long id) throws Exception {

        if (id == null || id <= 0)
            throw new Exception("ID inválido");

        return dao.getByProgrammer(id);
    }

    public Availability getById(Long id) throws Exception {

        if (id == null || id <= 0)
            throw new Exception("ID inválido");

        return dao.read(id);
    }

    public void crear(Availability a) throws Exception {

        if (a == null)
            throw new Exception("Availability vacía");

        if (a.getProgrammer() == null)
            throw new Exception("Programador obligatorio");

        dao.insert(a);
    }

    public void actualizar(Availability a) throws Exception {

        if (a == null || a.getId() == null)
            throw new Exception("Availability inválida");

        Availability old = dao.read(a.getId());

        if (old == null)
            throw new Exception("Availability no existe");

        dao.update(a);
    }

    public void eliminar(Long id) throws Exception {

        Availability a = dao.read(id);

        if (a == null)
            throw new Exception("Availability no existe");

        dao.delete(id);
    }
    
    public void crearAvailability(Availability availability) throws Exception {

        if (availability == null)
            throw new Exception("Availability vacía");

        if (availability.getProgrammer() == null)
            throw new Exception("Programador obligatorio");

        Availability exist = dao.findByProgrammerAndDay(
            availability.getProgrammer().getId(),
            availability.getDay()
        );

        if (exist != null)
            throw new Exception("Ya existe disponibilidad para ese día");

        dao.insert(availability);
    }

}
