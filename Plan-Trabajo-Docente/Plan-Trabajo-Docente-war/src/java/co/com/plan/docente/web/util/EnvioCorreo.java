/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.plan.docente.web.util;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Jorge Montoya
 */
public class EnvioCorreo {

    public void enviarCorreo(String mailMaster, String password,String para, String asunto, String mensaje) {

        try {
            // Propiedades de la conexión
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.user", mailMaster);
            props.setProperty("mail.smtp.auth", "true");

            // Preparamos la sesion
            Session session = Session.getDefaultInstance(props);

            // Construimos el mensaje
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mailMaster));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(para));
            message.setSubject(asunto);
            message.setText(mensaje);

            // Lo enviamos.
            Transport t = session.getTransport("smtp");
            t.connect(mailMaster, password);
            t.sendMessage(message, message.getAllRecipients());
            // Cierre.
            t.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
