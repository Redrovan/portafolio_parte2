package ec.edu.ups.ppw.portafolio.bussines;

import java.util.List;

import ec.edu.ups.ppw.portafolio.dao.EspecialidadDAO;
import ec.edu.ups.ppw.portafolio.model.Especialidad;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionEspecialidad {

    @Inject
    private EspecialidadDAO dao;

    public void guardar(Especialidad e) {
        dao.insert(e);
    }

    public List<Especialidad> listar() {
        return dao.getAll();
    }
}
