package ec.edu.ups.ppw.portafolio.services;

import jakarta.ejb.Stateless;
import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.util.Properties;

@Stateless
public class EmailService {

    private static final String FROM = "robinsonredrovan@gmail.com";
    private static final String PASSWORD = "hriwiycjentgttkd"; // sin espacios

    public void enviarCorreo(String destino, String asunto, String mensaje) {

        try {

            Properties props = new Properties();

            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            // importante para Gmail + WildFly
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

            props.put("mail.debug", "true");

            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(FROM, PASSWORD);
                }
            });

            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(FROM));
            msg.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(destino)
            );
            msg.setSubject(asunto);
            msg.setText(mensaje);

            Transport.send(msg);

            System.out.println(" Correo enviado a: " + destino);

        } catch (Exception e) {
            System.out.println(" ERROR AL ENVIAR CORREO:");
            e.printStackTrace();
        }
    }
}
