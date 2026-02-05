package ec.edu.ups.ppw.portafolio.services;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

import ec.edu.ups.ppw.portafolio.dao.AppointmentDAO;
import ec.edu.ups.ppw.portafolio.model.Appointment;

import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

@Singleton
@Startup
public class ReminderJob {

    @Inject
    private AppointmentDAO appointmentDAO;

    @Inject
    private WhatsAppService whatsappService;

    @Inject
    private EmailService emailService;

    //  Cada 5 minutos
    @Schedule(minute = "*/5", hour = "*", persistent = false)
    public void enviarRecordatorios() {

        System.out.println("⏳ Buscando asesorías próximas...");

        LocalDate hoy = LocalDate.now();
        LocalTime ahora = LocalTime.now();

        // ventana de 30 minutos
        LocalTime en30Min = ahora.plusMinutes(30);

        List<Appointment> proximas =
                appointmentDAO.findAppointmentsStartingSoon(
                        hoy,
                        ahora,
                        en30Min
                );

        for (Appointment a : proximas) {

            String fechaBonita = a.getDate()
                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            String mensaje = "⏰ Recordatorio PortafolioPro\n\n"
                    + "Tu asesoría empieza pronto 🚀\n\n"
                    + "📅 Fecha: " + fechaBonita + "\n"
                    + "⏰ Hora: " + a.getTime() + "\n"
                    + "💻 Modalidad: " + a.getMode() + "\n\n"
                    + "¡Te esperamos!";

            //  WhatsApp fijo sandbox
            whatsappService.enviarWhatsApp(
                    "+593982544829",
                    mensaje
            );

            //  Email
            emailService.enviarCorreo(
                    a.getClient().getEmail(),
                    "⏰ Recordatorio de asesoría",
                    mensaje
            );

            System.out.println(" Recordatorio enviado -> cita " + a.getId());
        }
    }
}
