package ec.edu.ups.ppw.portafolio.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

import ec.edu.ups.ppw.portafolio.model.Availability;

@Stateless
public class AvailabilityDAO {

    @PersistenceContext
    private EntityManager em;

    public void insert(Availability a) {
        em.persist(a);
    }

    public void update(Availability a) {
        em.merge(a);
    }

    public Availability read(Long id) {
        return em.find(Availability.class, id);
    }

    public void delete(Long id) {
        Availability a = em.find(Availability.class, id);
        if (a != null) em.remove(a);
    }

    public List<Availability> getAll() {
        return em.createQuery(
            "SELECT a FROM Availability a",
            Availability.class
        ).getResultList();
    }

    // ===============================
    // 🔥 POR PROGRAMADOR
    // ===============================
    public List<Availability> getByProgrammer(Long programmerId) {

        TypedQuery<Availability> q = em.createQuery(
            "SELECT a FROM Availability a WHERE a.programmer.id = :pid",
            Availability.class
        );

        q.setParameter("pid", programmerId);

        return q.getResultList();
    }

    // ===============================
    // 🔥 POR PROGRAMADOR + DÍA (PARA HORAS DISPONIBLES)
    // ===============================
    public Availability findByProgrammerAndDay(Long programmerId, String day) {

        TypedQuery<Availability> q = em.createQuery(
            "SELECT a FROM Availability a " +
            "WHERE a.programmer.id = :pid " +
            "AND a.day = :day",
            Availability.class
        );

        q.setParameter("pid", programmerId);
        q.setParameter("day", day);

        List<Availability> list = q.getResultList();

        return list.isEmpty() ? null : list.get(0);
    }
}
