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

    public ParticipationType buscar(Long id) throws Exception {
        ParticipationType pt = dao.read(id);
        if (pt == null) throw new Exception("ParticipationType no existe");
        return pt;
    }

    public void actualizar(ParticipationType s) throws Exception {
        ParticipationType existing = dao.read(s.getId());
        if (existing == null) throw new Exception("ParticipationType no existe");
        dao.update(s);
    }

    public void eliminar(Long id) throws Exception {
        ParticipationType existing = dao.read(id);
        if (existing == null) throw new Exception("ParticipationType no existe");
        dao.delete(id);
    }
}
