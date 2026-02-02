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

    public void insert(Availability availability) {
        em.persist(availability);
    }

    public void update(Availability availability) {
        em.merge(availability);
    }

    public Availability read(Long pk) {
        return em.find(Availability.class, pk);
    }

    public void delete(Long pk) {
        Availability availability = em.find(Availability.class, pk);
        if (availability != null) {
            em.remove(availability);
        }
    }

    public List<Availability> getAll() {
        return em.createQuery(
                "SELECT a FROM Availability a",
                Availability.class
        ).getResultList();
    }

    // 🔥 BUSCAR POR PROGRAMADOR Y DÍA
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
