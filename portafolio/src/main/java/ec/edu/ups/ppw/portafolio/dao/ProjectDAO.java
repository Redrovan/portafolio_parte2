package ec.edu.ups.ppw.portafolio.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.util.List;

import ec.edu.ups.ppw.portafolio.model.Project;

@Stateless
public class ProjectDAO {

    @PersistenceContext
    private EntityManager em;

    public void insert(Project project) {
        em.persist(project);
    }

    public Project read(Long id) {
        return em.find(Project.class, id);
    }

    public void update(Project project) {
        em.merge(project);
    }

    public void delete(Long id) {
        Project p = em.find(Project.class, id);
        if (p != null) {
            em.remove(p);
        }
    }

    public List<Project> getAll() {
        return em.createQuery(
            "SELECT p FROM Project p",
            Project.class
        ).getResultList();
    }

    public List<Project> getByUser(Long userId) {
        return em.createQuery(
            "SELECT p FROM Project p WHERE p.owner.id = :uid",
            Project.class
        ).setParameter("uid", userId)
         .getResultList();
    }
}
