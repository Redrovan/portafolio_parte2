package ec.edu.ups.ppw.portafolio.bussines;

import ec.edu.ups.ppw.portafolio.dao.*;

import ec.edu.ups.ppw.portafolio.model.*;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

import java.time.LocalDate;
import java.time.LocalTime;
/*
@Singleton
@Startup
public class Demo {

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

    @Inject
    private ParticipationTypeDAO participationTypeDAO;

    @PostConstruct
    public void init() {

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

        Especialidad esp1 = new Especialidad();
        esp1.setNombre("Backend Java");
        esp1.setDescripcion("APIs, JPA, WildFly");
        especialidadDAO.insert(esp1);

        Especialidad esp2 = new Especialidad();
        esp2.setNombre("Frontend Angular");
        esp2.setDescripcion("SPA, UI/UX");
        especialidadDAO.insert(esp2);

        ParticipationType ptBackend = new ParticipationType();
        ptBackend.setName("BACKEND");
        ptBackend.setDescription("Desarrollo backend");
        participationTypeDAO.insert(ptBackend);

        ParticipationType ptFrontend = new ParticipationType();
        ptFrontend.setName("FRONTEND");
        ptFrontend.setDescription("Desarrollo frontend");
        participationTypeDAO.insert(ptFrontend);

        User admin = new User();
        admin.setEmail("admin@ups.edu.ec");
        admin.setPassword("admin123");
        admin.setRole(Role.ADMIN);
        admin.setPersona(p1);
        admin.setActive(true);
        userDAO.insert(admin);

        User user = new User();
        user.setEmail("ana@ups.edu.ec");
        user.setPassword("user123");
        user.setRole(Role.USER);
        user.setPersona(p2);
        user.setActive(true);
        userDAO.insert(user);

        User programmer = new User();
        programmer.setEmail("carlos@ups.edu.ec");
        programmer.setPassword("prog123");
        programmer.setRole(Role.PROGRAMADOR);
        programmer.setPersona(p3);
        programmer.setEspecialidad(esp1);
        programmer.setActive(true);
        userDAO.insert(programmer);

        Project pr1 = new Project();
        pr1.setName("Sistema de Ventas");
        pr1.setDescription("Proyecto académico Java EE");
        pr1.setRepositoryUrl("https://github.com/admin/ventas");
        pr1.setDeployUrl("https://ventas-demo.vercel.app");
        pr1.setTechnologies("Java, JPA, JSF");
        pr1.setSection(ProjectSection.ACADEMIC);
        pr1.setParticipationType(ptBackend);
        pr1.setActive(true);
        pr1.setOwner(admin);
        projectDAO.insert(pr1);

        Project pr2 = new Project();
        pr2.setName("Portafolio Profesional");
        pr2.setDescription("Angular + Firebase");
        pr2.setRepositoryUrl("https://github.com/carlos/portafolio");
        pr2.setDeployUrl("https://portafolio-demo.web.app");
        pr2.setTechnologies("Angular, Firebase");
        pr2.setSection(ProjectSection.WORK);
        pr2.setParticipationType(ptFrontend);
        pr2.setActive(true);
        pr2.setOwner(programmer);
        projectDAO.insert(pr2);

        Availability av1 = new Availability();
        av1.setDay("MONDAY");
        av1.setStartTime("09:00");
        av1.setEndTime("12:00");
        av1.setMode("ONLINE");
        av1.setProgrammer(programmer);
        availabilityDAO.insert(av1);

        Availability av2 = new Availability();
        av2.setDay("WEDNESDAY");
        av2.setStartTime("14:00");
        av2.setEndTime("18:00");
        av2.setMode("PRESENCIAL");
        av2.setProgrammer(programmer);
        availabilityDAO.insert(av2);

        AppointmentStatus pending = new AppointmentStatus();
        pending.setName("PENDING");
        appointmentStatusDAO.insert(pending);

        AppointmentStatus approved = new AppointmentStatus();
        approved.setName("APPROVED");
        appointmentStatusDAO.insert(approved);

        AppointmentStatus rejected = new AppointmentStatus();
        rejected.setName("REJECTED");
        appointmentStatusDAO.insert(rejected);

        Appointment appointment = new Appointment();
        appointment.setClient(user);
        appointment.setProgrammer(programmer);
        appointment.setDate(LocalDate.now().plusDays(1));
        appointment.setTime(LocalTime.of(10, 0));
        appointment.setStatus(pending);
        appointment.setMode("ONLINE");
        appointment.setCreatedAt(LocalDate.now());
        appointmentDAO.insert(appointment);

        System.out.println(" DEMO COMPLETO CARGADO CORRECTAMENTE");
    }
}

*/
