package ec.edu.ups.ppw.portafolio.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

import ec.edu.ups.ppw.portafolio.model.Availability;
import ec.edu.ups.ppw.portafolio.model.User;

@Stateless
public class AvailabilityDAO {

    @PersistenceContext
    private EntityManager em;

    public void insert(Availability availability) {
        em.persist(availability);
    }

    public Availability findById(Long id) {
        return em.find(Availability.class, id);
    }

    public List<Availability> findByProgrammer(User programmer) {
        return em.createQuery(
                "SELECT a FROM Availability a WHERE a.programmer = :programmer",
                Availability.class)
                .setParameter("programmer", programmer)
                .getResultList();
    }

    public void update(Availability availability) {
        em.merge(availability);
    }

    public void delete(Long id) {
        Availability availability = findById(id);
        if (availability != null) {
            em.remove(availability);
        }
    }
    
    public List<Availability> getAll() {
        return em.createQuery("SELECT a FROM Availability a", Availability.class)
                 .getResultList();
    }
}
