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
    
    public ProgrammerProfile buscar(Long id) {
        return dao.read(id);
    }

    public ProgrammerProfile buscarPorUserId(Long userId) {
        return dao.findByUserId(userId);
    }

    public void actualizar(ProgrammerProfile p) throws Exception {
        ProgrammerProfile existing = dao.read(p.getId());
        if (existing == null)
            throw new Exception("ProgrammerProfile no existe");

        dao.update(p);
    }
}
