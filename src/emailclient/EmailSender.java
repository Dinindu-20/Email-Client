/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package emailclient;

import java.io.Serializable;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author ASUS-USER
 */
public class EmailSender implements Serializable {
    private String date;
    private String email;
    private String subject;
    private String content;
    final String username = "dthari441@gmail.com";
    final String password = "jzbjdxbuyyfvhdbd";

    

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Properties getProp() {
        return prop;
    }

    public void setProp(Properties prop) {
        this.prop = prop;
    }
    
    public EmailSender (String date,String email, String subject, String content) {
        this.date = date;
        this.email = email;
        this.subject = subject;
        this.content = content;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    Properties prop = new Properties();
    public void writeemail(String email,String subject,String Content){
        
        Properties prop = new Properties();
        
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(prop,
            new javax.mail.Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
                    }
                });

        try {
                        

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
            Message.RecipientType.TO,
            InternetAddress.parse(email)
            );
            message.setSubject(subject);
            message.setText(content);

            Transport.send(message);

            System.out.println("Email has been sent");

        } catch (MessagingException e) {
        }
}    
    
}
