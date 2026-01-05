package ec.edu.ups.ppw.portafolio.bussines;

import java.util.List;
import ec.edu.ups.ppw.portafolio.dao.UserDAO;
import ec.edu.ups.ppw.portafolio.model.User;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionUsers {

    @Inject
    private UserDAO userDAO;

    public List<User> getUsers() {
        return userDAO.getAll();
    }

    public void guardar(User user) {
        userDAO.insert(user);
    }
}
