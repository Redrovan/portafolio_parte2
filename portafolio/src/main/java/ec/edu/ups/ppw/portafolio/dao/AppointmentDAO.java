package ec.edu.ups.ppw.portafolio.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
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

    // ============================
    // ⏰ HORAS OCUPADAS POR FECHA
    // ============================
    public List<LocalTime> findHoursByProgrammerAndDate(Long programmerId, LocalDate date) {

        TypedQuery<LocalTime> q = em.createQuery(
                "SELECT a.time FROM Appointment a " +
                "WHERE a.programmer.id = :pid " +
                "AND a.date = :date",
                LocalTime.class
        );

        q.setParameter("pid", programmerId);
        q.setParameter("date", date);

        return q.getResultList();
    }

    // ============================
    // 🚫 BLOQUEAR HORA REPETIDA
    // ============================
    public boolean exists(Long programmerId, LocalDate date, LocalTime time) {

        TypedQuery<Long> q = em.createQuery(
                "SELECT COUNT(a) FROM Appointment a " +
                "WHERE a.programmer.id = :pid " +
                "AND a.date = :date " +
                "AND a.time = :time",
                Long.class
        );

        q.setParameter("pid", programmerId);
        q.setParameter("date", date);
        q.setParameter("time", time);

        return q.getSingleResult() > 0;
    }

    // ============================
    // 📊 REPORTE POR ESTADO
    // ============================
    public List<Object[]> countByStatus() {

        return em.createQuery(
                "SELECT a.status.name, COUNT(a) " +
                "FROM Appointment a " +
                "GROUP BY a.status.name",
                Object[].class
        ).getResultList();
    }

    // ============================
    // 📊 REPORTE POR PROGRAMADOR
    // ============================
    public List<Object[]> countByProgrammer() {

        return em.createQuery(
                "SELECT a.programmer.persona.nombre, COUNT(a) " +
                "FROM Appointment a " +
                "GROUP BY a.programmer.persona.nombre",
                Object[].class
        ).getResultList();
    }
}
