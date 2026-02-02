package ec.edu.ups.ppw.portafolio.dao;

import java.util.List;

import ec.edu.ups.ppw.portafolio.model.ProgrammerProfile;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

@Stateless
public class ProgrammerProfileDAO {

    @PersistenceContext
    private EntityManager em;

    public void insert(ProgrammerProfile p) {
        em.persist(p);
    }

    public ProgrammerProfile read(Long id) {
        return em.find(ProgrammerProfile.class, id);
    }

    public ProgrammerProfile update(ProgrammerProfile p) {
        return em.merge(p);
    }

    public List<ProgrammerProfile> getAll() {
        return em.createQuery("SELECT p FROM ProgrammerProfile p", ProgrammerProfile.class)
                .getResultList();
    }

    public ProgrammerProfile findByUserId(Long userId) {
        try {
            return em.createQuery("SELECT p FROM ProgrammerProfile p WHERE p.user.id = :uid", ProgrammerProfile.class)
                    .setParameter("uid", userId)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
