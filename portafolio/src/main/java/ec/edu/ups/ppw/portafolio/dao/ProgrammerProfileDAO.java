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

	public List<ProgrammerProfile> getAll() {
		return em.createQuery("SELECT p FROM ProgrammerProfile p",ProgrammerProfile.class)
				.getResultList();
	}
}
