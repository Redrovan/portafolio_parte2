package ec.edu.ups.ppw.portafolio.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;
import ec.edu.ups.ppw.portafolio.model.AppointmentStatus;

@Stateless
public class AppointmentStatusDAO {

    @PersistenceContext
    private EntityManager em;

    public void insert(AppointmentStatus s) {
        em.persist(s);
    }

    public void update(AppointmentStatus s) {
        em.merge(s);
    }

    public AppointmentStatus read(Long pk) {
        return em.find(AppointmentStatus.class, pk);
    }

    public void delete(Long pk) {
        AppointmentStatus s = em.find(AppointmentStatus.class, pk);
        if (s != null) {
            em.remove(s);
        }
    }

    public List<AppointmentStatus> getAll() {
        String jpql = "SELECT s FROM AppointmentStatus s";
        TypedQuery<AppointmentStatus> q = em.createQuery(jpql, AppointmentStatus.class);
        return q.getResultList();
    }
}
