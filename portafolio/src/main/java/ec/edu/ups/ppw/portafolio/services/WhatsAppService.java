package ec.edu.ups.ppw.portafolio.services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class WhatsAppService {

   
    static {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    //  Enviar WhatsApp
    public void enviarWhatsApp(String numero, String mensaje) {

        Message.creator(
                new PhoneNumber("whatsapp:" + numero),
                new PhoneNumber("whatsapp:+14155238886"), // Sandbox Twilio
                mensaje
        ).create();
    }
    
}
