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
    
    public Persona getPersona(String id) throws Exception{
    	if(id.isEmpty()|| id.length()!= 10)
    		throw new Exception("Parametro Vacio");
    	Persona p= personaDAO.read(id);
    	return p;
    }
    public void crearPersona(Persona persona)throws Exception {
    	personaDAO.crearPersona(persona);
    }
    
    public void actualizarPersona(Persona persona) throws Exception {
        Persona p = personaDAO.read(persona.getCedula());
        if (p == null)
            throw new Exception("Persona no existe");

        personaDAO.update(persona);
    }
    
    public void eliminarPersona(String cedula) throws Exception {
        Persona p = personaDAO.read(cedula);
        if (p == null)
            throw new Exception("Persona no existe");

        personaDAO.delete(cedula);
    }


}
