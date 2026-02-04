package ec.edu.ups.ppw.portafolio.bussines;

import java.time.LocalDate;
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

        if (a == null) throw new Exception("Appointment inválida");

        if (a.getClient() == null || a.getProgrammer() == null)
            throw new Exception("Cliente y Programador obligatorios");

        if (a.getDate() == null || a.getTime() == null)
            throw new Exception("Fecha y hora obligatorias");

        if (a.getStatus() == null)
            throw new Exception("Estado obligatorio");

        // ❗ SOLO BLOQUEA ESA HORA
        boolean ocupado = appointmentDAO.exists(
                a.getProgrammer().getId(),
                a.getDate(),
                a.getTime()
        );

        if (ocupado) {
            throw new Exception("Horario ya ocupado");
        }

        a.setCreatedAt(LocalDate.now());

        appointmentDAO.insert(a);
    }


    public Appointment buscar(Long id) throws Exception {
        if (id == null || id <= 0) {
            throw new Exception("Parametro Vacio o inválido");
        }
        Appointment a = appointmentDAO.read(id);
        return a;
    }

    public void actualizar(Appointment appointment) throws Exception {
        if (appointment == null || appointment.getId() == null) {
            throw new Exception("Appointment inválida");
        }

        Appointment a = appointmentDAO.read(appointment.getId());
        if (a == null) {
            throw new Exception("Appointment no existe");
        }
        appointmentDAO.update(appointment);
    }

    public void eliminar(Long id) throws Exception {
        if (id == null || id <= 0) {
            throw new Exception("Parametro Vacio o inválido");
        }

        Appointment a = appointmentDAO.read(id);
        if (a == null) {
            throw new Exception("Appointment no existe");
        }
        appointmentDAO.delete(id);
    }
}
