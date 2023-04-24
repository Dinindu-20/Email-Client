/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emailclient;
    
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS-USER
 */
//OfficialFriend class to send birthday mails
public class OfficialFriend implements Receipient, Serializable {
    private String name;
    private String email;
    private String bday;
    

    
    public void writetofile(String name,String email,String bday){
        BufferedWriter writer = null;
        try {
            String outputText = name+"|"+email+"|"+bday;
            writer = new BufferedWriter(new FileWriter("ClientsList.txt"));
            writer.write(outputText);
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(OfficialFriend.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(OfficialFriend.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
       
    public OfficialFriend(String name, String email, String bday) {
        this.name = name;
        this.email = email;
        this.bday = bday;
        
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBday() {
        return bday;
    }

    public void setBday(String bday) {
        this.bday = bday;
    }
    
    @Override
    public void Writemail(String name, String email) {
        
    }

    @Override
    public void Birthdaymail(String name,String email) {
        final String username = "dthari441@gmail.com";
        final String password = "jzbjdxbuyyfvhdbd";
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
            message.setSubject("Happy Birth Day");
            message.setText("Happy Birth Day to you Dear Friend"+","+name+"from Dinindu");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        
    }  
}
