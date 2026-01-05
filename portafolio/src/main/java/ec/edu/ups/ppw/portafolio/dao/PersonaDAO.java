package ec.edu.ups.ppw.portafolio.dao;

import java.util.List;

import ec.edu.ups.ppw.portafolio.model.Persona;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Stateless
public class PersonaDAO {

    @PersistenceContext
    private EntityManager em;

    public void insert(Persona persona) {
        em.persist(persona);
    }

    public void update(Persona persona) {
        em.merge(persona);
    }

    public Persona read(String pk) {
        return em.find(Persona.class, pk);
    }

    public void delete(String pk) {
        Persona persona = em.find(Persona.class, pk);
        if (persona != null) {
            em.remove(persona);
        }
    }

    public List<Persona> getAll() {
        String jpql = "SELECT p FROM Persona p";
        TypedQuery<Persona> q = em.createQuery(jpql, Persona.class);
        return q.getResultList();
    }
    
    public void crearPersona (Persona persona) throws Exception{
    	if(persona.getCedula().length()!=10)
    		throw new Exception("Formato de cedula incorrecto");
    	
    	em.persist(persona);
    }
}
