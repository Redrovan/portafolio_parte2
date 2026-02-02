package ec.edu.ups.ppw.portafolio.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

import ec.edu.ups.ppw.portafolio.model.Especialidad;

@Stateless
public class EspecialidadDAO {

    @PersistenceContext
    private EntityManager em;

    public void insert(Especialidad especialidad) {
        em.persist(especialidad);
    }

    public Especialidad read(Long id) {
        return em.find(Especialidad.class, id);
    }

    public List<Especialidad> getAll() {
        return em.createQuery(
            "SELECT e FROM Especialidad e",
            Especialidad.class
        ).getResultList();
    }
}
