package ec.edu.ups.ppw.portafolio.bussines;

import java.util.List;
import ec.edu.ups.ppw.portafolio.dao.PersonaDAO;
import ec.edu.ups.ppw.portafolio.model.Persona;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionPersonas {

    @Inject
    private PersonaDAO personaDAO;

    public List<Persona> getPersonas() {
        return personaDAO.getAll();
    }

    public Persona getPersona(String id) throws Exception {
        if (id == null || id.isEmpty() || id.length() != 10) {
            throw new Exception("Cédula inválida");
        }
        Persona p = personaDAO.read(id);
        return p;
    }

    public void crearPersona(Persona persona) throws Exception {
        if (persona.getCedula() == null || persona.getCedula().length() != 10) {
            throw new Exception("Formato de cédula incorrecto");
        }
        personaDAO.insert(persona);
    }

    public void actualizarPersona(Persona persona) throws Exception {
        Persona p = personaDAO.read(persona.getCedula());
        if (p == null) {
            throw new Exception("Persona no existe");
        }
        personaDAO.update(persona);
    }

    public void eliminarPersona(String cedula) throws Exception {
        Persona p = personaDAO.read(cedula);
        if (p == null) {
            throw new Exception("Persona no existe");
        }
        personaDAO.delete(cedula);
    }
}
