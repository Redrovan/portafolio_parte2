package ec.edu.ups.ppw.portafolio.bussines;

import java.util.List;

import ec.edu.ups.ppw.portafolio.dao.ParticipationTypeDAO;
import ec.edu.ups.ppw.portafolio.model.ParticipationType;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless

public class GestionParticipationType {

	 @Inject
	    private ParticipationTypeDAO dao;

	    public void guardar(ParticipationType s) {
	        dao.insert(s);
	    }

	    public List<ParticipationType> listar() {
	        return dao.getAll();
	    }
}
