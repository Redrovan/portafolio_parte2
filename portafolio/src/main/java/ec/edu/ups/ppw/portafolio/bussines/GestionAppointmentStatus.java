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
        if (id == null) {
            throw new Exception("Parametro Vacio");
        }
        return dao.read(id);
    }

    public void guardar(AppointmentStatus s) throws Exception {
        dao.insert(s);
    }

    public void actualizar(AppointmentStatus s) throws Exception {
        AppointmentStatus existing = dao.read(s.getId());
        if (existing == null) {
            throw new Exception("AppointmentStatus no existe");
        }
        dao.update(s);
    }

    public void eliminar(Long id) throws Exception {
        AppointmentStatus existing = dao.read(id);
        if (existing == null) {
            throw new Exception("AppointmentStatus no existe");
        }
        dao.delete(id);
    }
}
