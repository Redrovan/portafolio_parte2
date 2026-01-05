package ec.edu.ups.ppw.portafolio.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.util.List;

import ec.edu.ups.ppw.portafolio.model.ParticipationType;

@Stateless
public class ParticipationTypeDAO {

    @PersistenceContext
    private EntityManager em;

    public void insert(ParticipationType pt) {
        em.persist(pt);
    }

    public ParticipationType read(Long id) {
        return em.find(ParticipationType.class, id);
    }

    public List<ParticipationType> getAll() {
        return em.createQuery(
            "SELECT p FROM ParticipationType p", ParticipationType.class
        ).getResultList();
    }
}
