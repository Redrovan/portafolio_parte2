package ec.edu.ups.ppw.portafolio.bussines;

import ec.edu.ups.ppw.portafolio.dao.*;
import ec.edu.ups.ppw.portafolio.model.*;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

import java.time.LocalDate;
import java.time.LocalTime;

@Singleton
@Startup
public class Demo {

    // ========= DAOs =========
    @Inject
    private PersonaDAO personaDAO;

    @Inject
    private UserDAO userDAO;

    @Inject
    private ProjectDAO projectDAO;

    @Inject
    private EspecialidadDAO especialidadDAO;

    @Inject
    private AvailabilityDAO availabilityDAO;

    @Inject
    private AppointmentStatusDAO appointmentStatusDAO;

    @Inject
    private AppointmentDAO appointmentDAO;

    @PostConstruct
    public void init() {

        // ==================================================
        // PERSONAS
        // ==================================================
        Persona p1 = new Persona();
        p1.setCedula("0101010101");
        p1.setNombre("Juan Perez");
        p1.setDireccion("Cuenca");
        personaDAO.insert(p1);

        Persona p2 = new Persona();
        p2.setCedula("0202020202");
        p2.setNombre("Ana Torres");
        p2.setDireccion("Quito");
        personaDAO.insert(p2);

        Persona p3 = new Persona();
        p3.setCedula("0303030303");
        p3.setNombre("Carlos Gómez");
        p3.setDireccion("Guayaquil");
        personaDAO.insert(p3);

        // ==================================================
        // ESPECIALIDADES
        // ==================================================
        Especialidad esp1 = new Especialidad();
        esp1.setNombre("Backend Java");
        esp1.setDescripcion("APIs, JPA, WildFly");
        especialidadDAO.insert(esp1);

        Especialidad esp2 = new Especialidad();
        esp2.setNombre("Frontend Angular");
        esp2.setDescripcion("SPA, UI/UX");
        especialidadDAO.insert(esp2);

        // ==================================================
        // USUARIOS
        // ==================================================
        User user1 = new User();
        user1.setEmail("juan@ups.edu.ec");
        user1.setRole("user");
        user1.setPersona(p1);
        userDAO.insert(user1);

        User user2 = new User();
        user2.setEmail("ana@ups.edu.ec");
        user2.setRole("user");
        user2.setPersona(p2);
        userDAO.insert(user2);

        User programmer = new User();
        programmer.setEmail("carlos@ups.edu.ec");
        programmer.setRole("programmer");
        programmer.setPersona(p3);
        programmer.setEspecialidad(esp1);
        userDAO.insert(programmer);

        // ==================================================
        // PROYECTOS
        // ==================================================
        Project pr1 = new Project();
        pr1.setName("Sistema de Ventas");
        pr1.setDescription("Proyecto académico Java EE");
        pr1.setRepositoryUrl("https://github.com/juan/ventas");
        pr1.setOwner(user1);
        projectDAO.insert(pr1);

        Project pr2 = new Project();
        pr2.setName("Portafolio Profesional");
        pr2.setDescription("Angular + Firebase");
        pr2.setRepositoryUrl("https://github.com/carlos/portafolio");
        pr2.setOwner(programmer);
        projectDAO.insert(pr2);

        // ==================================================
        // DISPONIBILIDAD
        // ==================================================
        Availability av1 = new Availability();
        av1.setDay("MONDAY");
        av1.setStartTime("09:00");
        av1.setEndTime("12:00");
        av1.setProgrammer(programmer);
        availabilityDAO.insert(av1);

        Availability av2 = new Availability();
        av2.setDay("WEDNESDAY");
        av2.setStartTime("14:00");
        av2.setEndTime("18:00");
        av2.setProgrammer(programmer);
        availabilityDAO.insert(av2);

        // ==================================================
        // ESTADOS DE CITA
        // ==================================================
        AppointmentStatus pending = new AppointmentStatus();
        pending.setName("PENDING");
        appointmentStatusDAO.insert(pending);

        AppointmentStatus approved = new AppointmentStatus();
        approved.setName("APPROVED");
        appointmentStatusDAO.insert(approved);

        AppointmentStatus rejected = new AppointmentStatus();
        rejected.setName("REJECTED");
        appointmentStatusDAO.insert(rejected);

        // ==================================================
        // CITA (APPOINTMENT)
        // ==================================================
        Appointment appointment = new Appointment();
        appointment.setClient(user1);
        appointment.setProgrammer(programmer);
        appointment.setDate(LocalDate.now().plusDays(1));
        appointment.setTime(LocalTime.of(10, 0));
        appointment.setStatus(pending);
        appointmentDAO.insert(appointment);

        System.out.println("DEMO COMPLETO CARGADO CORRECTAMENTE");
    }
}
