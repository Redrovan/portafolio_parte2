package ec.edu.ups.ppw.portafolio.bussines;

import java.util.List;
import ec.edu.ups.ppw.portafolio.dao.AppointmentDAO;
import ec.edu.ups.ppw.portafolio.model.Appointment;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionAppointment {

    @Inject
    private AppointmentDAO appointmentDAO;

    // LISTAR
    public List<Appointment> listar() {
        return appointmentDAO.getAll();
    }
    
    // CREAR
    public void guardar(Appointment a) throws Exception {
        appointmentDAO.insert(a);
    }

    public Appointment buscar(Long id) throws Exception {
        if (id == null) {
            throw new Exception("Parametro Vacio");
        }
        Appointment a = appointmentDAO.read(id);
        return a;
    }

    public void actualizar(Appointment appointment) throws Exception {
        Appointment a = appointmentDAO.read(appointment.getId());
        if (a == null) {
            throw new Exception("Appointment no existe");
        }
        appointmentDAO.update(appointment);
    }

    public void eliminar(Long id) throws Exception {
        Appointment a = appointmentDAO.read(id);
        if (a == null) {
            throw new Exception("Appointment no existe");
        }
        appointmentDAO.delete(id);
    }

}
