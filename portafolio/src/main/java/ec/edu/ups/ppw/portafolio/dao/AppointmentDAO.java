package ec.edu.ups.ppw.portafolio.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.List;

import ec.edu.ups.ppw.portafolio.model.Appointment;

@Stateless
public class AppointmentDAO {

    @PersistenceContext
    private EntityManager em;

    public void insert(Appointment appointment) {
        em.persist(appointment);
    }

    public void update(Appointment appointment) {
        em.merge(appointment);
    }

    public Appointment read(Long pk) {
        return em.find(Appointment.class, pk);
    }

    public void delete(Long pk) {
        Appointment appointment = em.find(Appointment.class, pk);
        if (appointment != null) {
            em.remove(appointment);
        }
    }

    public List<Appointment> getAll() {
        return em.createQuery(
                "SELECT a FROM Appointment a",
                Appointment.class
        ).getResultList();
    }

    // 🔥 HORAS OCUPADAS POR PROGRAMADOR Y FECHA
    public List<String> findHoursByProgrammerAndDate(Long programmerId, LocalDate date) {

        TypedQuery<String> q = em.createQuery(
                "SELECT a.time FROM Appointment a " +
                "WHERE a.programmer.id = :pid " +
                "AND a.date = :date",
                String.class
        );

        q.setParameter("pid", programmerId);
        q.setParameter("date", date);

        return q.getResultList();
    }
}
