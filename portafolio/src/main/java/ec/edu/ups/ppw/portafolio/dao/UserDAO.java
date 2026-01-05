package ec.edu.ups.ppw.portafolio.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.util.List;

import ec.edu.ups.ppw.portafolio.model.User;

@Stateless
public class UserDAO {

    @PersistenceContext
    private EntityManager em;

    public void insert(User user) {
        em.persist(user);
    }

    public User read(Long id) {
        return em.find(User.class, id);
    }

    public List<User> getAll() {
        return em.createQuery(
            "SELECT u FROM User u", User.class
        ).getResultList();
    }
}
