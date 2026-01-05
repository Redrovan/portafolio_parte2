package ec.edu.ups.ppw.portafolio.bussines;

import java.util.List;

import ec.edu.ups.ppw.portafolio.dao.ProgrammerProfileDAO;
import ec.edu.ups.ppw.portafolio.model.ProgrammerProfile;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionProgrammerProfile {

    @Inject
    private ProgrammerProfileDAO dao;

    public void guardar(ProgrammerProfile p) {
        dao.insert(p);
    }

    public List<ProgrammerProfile> listar() {
        return dao.getAll();
    }
}

