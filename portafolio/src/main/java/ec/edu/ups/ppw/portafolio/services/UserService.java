package ec.edu.ups.ppw.portafolio.services;

import java.util.List;
import ec.edu.ups.ppw.portafolio.bussines.GestionUsers;
import ec.edu.ups.ppw.portafolio.model.User;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;

@Path("users")
public class UserService {

    @Inject
    private GestionUsers gu;

    @GET
    @Produces("application/json")
    public List<User> getUsers() {
        return gu.getUsers();
    }

    @POST
    @Consumes("application/json")
    public void create(User user) {
        gu.guardar(user);
    }
}
