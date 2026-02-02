package ec.edu.ups.ppw.portafolio.bussines;

import java.util.List;
import ec.edu.ups.ppw.portafolio.dao.AppointmentStatusDAO;
import ec.edu.ups.ppw.portafolio.model.AppointmentStatus;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionAppointmentStatus {

    @Inject
    private AppointmentStatusDAO dao;

    public List<AppointmentStatus> listar() {
        return dao.getAll();
    }

    public AppointmentStatus buscar(Long id) throws Exception {
        if (id == null || id <= 0) {
            throw new Exception("Parametro Vacio o inválido");
        }
        return dao.read(id);
    }

    public void guardar(AppointmentStatus s) throws Exception {
        if (s == null || s.getName() == null || s.getName().isEmpty()) {
            throw new Exception("El nombre del estado es obligatorio");
        }
        dao.insert(s);
    }

    public void actualizar(AppointmentStatus s) throws Exception {
        if (s == null || s.getId() == null) {
            throw new Exception("AppointmentStatus inválido");
        }

        AppointmentStatus existing = dao.read(s.getId());
        if (existing == null) {
            throw new Exception("AppointmentStatus no existe");
        }
        dao.update(s);
    }

    public void eliminar(Long id) throws Exception {
        if (id == null || id <= 0) {
            throw new Exception("Parametro Vacio o inválido");
        }

        AppointmentStatus existing = dao.read(id);
        if (existing == null) {
            throw new Exception("AppointmentStatus no existe");
        }
        dao.delete(id);
    }
}
