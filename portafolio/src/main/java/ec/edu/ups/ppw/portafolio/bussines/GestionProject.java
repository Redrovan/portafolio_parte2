package ec.edu.ups.ppw.portafolio.bussines;

import java.util.List;

import ec.edu.ups.ppw.portafolio.dao.ProjectDAO;
import ec.edu.ups.ppw.portafolio.model.Project;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionProject {

    @Inject
    private ProjectDAO dao;

    public List<Project> listar() {
        return dao.getAll();
    }

    public List<Project> listarPorUsuario(Long userId) throws Exception {
        if (userId == null || userId <= 0) {
            throw new Exception("ID de usuario inválido");
        }
        return dao.getByUser(userId);
    }

    public Project buscar(Long id) throws Exception {
        if (id == null || id <= 0) {
            throw new Exception("ID inválido");
        }
        return dao.read(id);
    }

    public void guardar(Project p) throws Exception {
        if (p == null) throw new Exception("Proyecto inválido");
        if (p.getName() == null || p.getName().isEmpty())
            throw new Exception("Nombre del proyecto es obligatorio");
        if (p.getSection() == null)
            throw new Exception("Sección del proyecto es obligatoria");
        if (p.getOwner() == null)
            throw new Exception("El proyecto debe tener un dueño");

        dao.insert(p);
    }

    public void actualizar(Project p) throws Exception {
        if (p == null || p.getId() == null)
            throw new Exception("Proyecto inválido");

        Project existing = dao.read(p.getId());
        if (existing == null)
            throw new Exception("Proyecto no existe");

        dao.update(p);
    }

    public void eliminar(Long id) throws Exception {
        if (id == null || id <= 0)
            throw new Exception("ID inválido");

        Project existing = dao.read(id);
        if (existing == null)
            throw new Exception("Proyecto no existe");

        dao.delete(id);
    }
}
