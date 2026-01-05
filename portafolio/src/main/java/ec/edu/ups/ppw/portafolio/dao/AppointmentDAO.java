package ec.edu.ups.ppw.portafolio.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

import ec.edu.ups.ppw.portafolio.model.Appointment;

@Stateless
public class AppointmentDAO {

    @PersistenceContext
    private EntityManager em;

    // INSERTAR
    public void insert(Appointment appointment) {
        em.persist(appointment);
    }

    // ACTUALIZAR
    public void update(Appointment appointment) {
        em.merge(appointment);
    }

    // LEER POR ID
    public Appointment read(Long pk) {
        return em.find(Appointment.class, pk);
    }

    public void delete(Long pk) {
        Appointment appointment = em.find(Appointment.class, pk);
        if (appointment != null) {
            em.remove(appointment);
        }
    }


    // LISTAR
    public List<Appointment> getAll() {
        String jpql = "SELECT a FROM Appointment a";
        TypedQuery<Appointment> q = em.createQuery(jpql, Appointment.class);
        return q.getResultList();
    }
}
