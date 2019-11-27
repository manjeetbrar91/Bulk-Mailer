/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bulkmailer;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Date;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail {

    public static SendMail getInstance() {
        return new SendMail(Util.loadSettings());
    }

    Properties p;
    static String MAILFROM = "Geek Tech <info@geektechllc.com>";
    static String HOST = "smtpout.secureserver.net";
    static String USERNAME = "info@geektechllc.com";  // Replace with your SMTP username.
    static String PASSWORD = "G33k@401";  // Replace with your SMTP password.

    public SendMail(Settings settings) {

        p = new Properties();
        p.put("mail.smtp.auth", settings.getAuth_required().trim());
        p.put("mail.transport.protocol", settings.getProtocol().trim());
        p.put("mail.smtp.host", settings.getHost().trim());
        p.put("mail.smtp.port", settings.getPort().trim());
        p.put("mail.smtp.starttls.enable", settings.getEnable_starttls().trim());
        p.put("mail.smtp.starttls.required", settings.getRequired_starttls().trim());

        USERNAME = settings.getUsername();
        PASSWORD = settings.getPassword();
        MAILFROM = settings.getMail_from();

    }

    public static void main(String[] args) {
        System.out.printf("asdg %s ;asdas;%s", "A", "BB");

        String msg = "<html>\n"
                + "    <head>\n"
                + "        \n"
                + "    </head>\n"
                + "    <body>\n"
                + "        <div style=\"   color: red; padding: 20px;background: #0f0\"><img src=\"http://hd.wallpaperswide.com/thumbs/green_nature-t2.jpg\"/></div>\n"
                + "    </body>\n"
                + "</html>";
//         msg = String.format(EmailTemplates.TEMPLATE_EMAILVER, "DevelopTech", "google.com");
        String sendMultiPartMail = new SendMail(Util.loadSettings()).sendMultiPartMail("manjeetbrar91@gmail.com", "Test", msg);
        System.out.println("sendMultiPartMail" + sendMultiPartMail);
    }

    public int sendMail(String email, String subject, String message) {
        try {

//--[ Set up the default parameters
//--[ Create the session and create a new mail message
            Authenticator auth = new SMTPAuthenticator(USERNAME, PASSWORD);
            Session mailSession = Session.getInstance(p, auth);
            Message msg = new MimeMessage(mailSession);

//--[ Set the FROM, TO, DATE and SUBJECT fields
            msg.setFrom(new InternetAddress(MAILFROM));

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));

            msg.setSentDate(new Date());
            msg.setSubject(subject);

//--[ Create the body of the mail
            msg.setText(message);

//--[ Ask the Transport class to send our mail message
            Transport trans = mailSession.getTransport();

            trans.connect();
            Address[] ad = msg.getAllRecipients();
            for (Address ad2 : ad) {
                try {
                    Address[] ad1 = new Address[1];
                    ad1[0] = ad2;
                    trans.sendMessage(msg, ad1);
                    //   result = "sent";
                } catch (MessagingException e) {
                }
            }

            return 1;
        } catch (MessagingException E) {
            System.out.println("Something gone wrong while sending mail!" + E);
            return 0;
        }

    }

    public String sendMultiPartMail(String email, String subject, String message) {
        try {

//            if (!ConnectDB.envoinment.toString().equalsIgnoreCase(ConnectDB.Envoinment.PRODUCTION.toString())) {
//                return "failed";
//            }
//--[ Set up the default parameters
            Authenticator auth = new SMTPAuthenticator(USERNAME, PASSWORD);
            Session mailSession = Session.getInstance(p, auth);
            Message msg = new MimeMessage(mailSession);

//--[ Set the FROM, TO, DATE and SUBJECT fields
            msg.setFrom(new InternetAddress(MAILFROM));

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));

            msg.setSentDate(new Date());
            msg.setSubject(subject);

//--[ Create the body of the mail
//            msg.setText(message);
            String htmlPart = message;

            Multipart mp = new MimeMultipart("related");
            MimeBodyPart htmlBodyPart = new MimeBodyPart();
            htmlBodyPart.setContent(htmlPart, "text/html");
            mp.addBodyPart(htmlBodyPart);
            //Getting data of images
            msg.setContent(mp);

//--[ Ask the Transport class to send our mail message
            Transport trans = mailSession.getTransport();

            trans.connect();
            Address[] ad = msg.getAllRecipients();
            for (Address ad2 : ad) {
                try {
                    Address[] ad1 = new Address[1];
                    ad1[0] = ad2;
                    trans.sendMessage(msg, ad1);
                    //   result = "sent";
                } catch (MessagingException e) {
                }
            }

            return "SENT";
        } catch (MessagingException E) {
            System.out.println("Something gone wrong while sending mail!" + E);
            return E.toString();
        }

    }
}
