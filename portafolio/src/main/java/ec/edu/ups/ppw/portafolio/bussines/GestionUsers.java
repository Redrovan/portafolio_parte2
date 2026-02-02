package ec.edu.ups.ppw.portafolio.bussines;

import java.util.List;
import ec.edu.ups.ppw.portafolio.dao.UserDAO;
import ec.edu.ups.ppw.portafolio.model.Role;
import ec.edu.ups.ppw.portafolio.model.User;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionUsers {

    @Inject
    private UserDAO userDAO;

    // LISTAR
    public List<User> getUsers() {
        return userDAO.getAll();
    }

    // OBTENER POR ID
    public User getUser(Long id) throws Exception {
        if (id == null)
            throw new Exception("ID inválido");

        User u = userDAO.read(id);
        if (u == null)
            throw new Exception("Usuario no existe");

        return u;
    }

    // CREAR
    public void guardar(User user) throws Exception {
        if (user.getEmail() == null || user.getRole() == null)
            throw new Exception("Datos incompletos");

        // Validar email único
        if (userDAO.findByEmail(user.getEmail()) != null)
            throw new Exception("Email ya existe");

        userDAO.insert(user);
    }

    // ACTUALIZAR
    public void actualizar(User user) throws Exception {
        User u = userDAO.read(user.getId());
        if (u == null)
            throw new Exception("Usuario no existe");

        userDAO.update(user);
    }

    // ELIMINAR
    public void eliminar(Long id) throws Exception {
        User u = userDAO.read(id);
        if (u == null)
            throw new Exception("Usuario no existe");

        userDAO.delete(id);
    }

    // LISTAR POR ROL
    public List<User> getUsersByRole(Role role) throws Exception {
        if (role == null)
            throw new Exception("Rol inválido");

        return userDAO.findByRole(role);
    }

    // VALIDAR ADMIN
    public void validarAdmin(User user) throws Exception {
        if (user == null || user.getRole() != Role.ADMIN)
            throw new Exception("Acceso solo para ADMIN");
    }

    // BUSCAR POR EMAIL (para login)
    public User findByEmail(String email) {
        return userDAO.findByEmail(email);
    }
}
