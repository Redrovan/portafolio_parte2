package ec.edu.ups.ppw.portafolio.dao;

import java.util.List;
import ec.edu.ups.ppw.portafolio.model.Role;
import ec.edu.ups.ppw.portafolio.model.User;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Stateless
public class UserDAO {

    @PersistenceContext
    private EntityManager em;

    // INSERTAR
    public void insert(User user) {
        em.persist(user);
    }

    // ACTUALIZAR
    public void update(User user) {
        em.merge(user);
    }

    // LEER POR ID
    public User read(Long id) {
        return em.find(User.class, id);
    }

    // ELIMINAR
    public void delete(Long id) {
        User user = read(id);
        if (user != null) {
            em.remove(user);
        }
    }

    // LISTAR TODOS
    public List<User> getAll() {
        TypedQuery<User> q = em.createQuery("SELECT u FROM User u", User.class);
        return q.getResultList();
    }

    // LISTAR POR ROL
    public List<User> findByRole(Role role) {
        TypedQuery<User> q = em.createQuery("SELECT u FROM User u WHERE u.role = :role", User.class);
        q.setParameter("role", role);
        return q.getResultList();
    }

    // BUSCAR POR EMAIL (para login)
    public User findByEmail(String email) {
        TypedQuery<User> q = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
        q.setParameter("email", email);
        return q.getResultStream().findFirst().orElse(null);
    }
}
