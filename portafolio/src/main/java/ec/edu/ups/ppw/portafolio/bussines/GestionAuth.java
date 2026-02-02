package ec.edu.ups.ppw.portafolio.bussines;

import ec.edu.ups.ppw.portafolio.dao.UserDAO;
import ec.edu.ups.ppw.portafolio.model.User;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionAuth {

    @Inject
    private UserDAO userDAO;

    public User loginUser(String email, String password) throws Exception {
        User u = userDAO.findByEmail(email);

        if (u == null) {
            throw new Exception("Usuario no existe");
        }

        if (!u.getPassword().equals(password)) {
            throw new Exception("Contraseña incorrecta");
        }

        return u;
    }
}
