/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emailclient;


import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ASUS-USER
 */
//Official class to birthday mails
public class Official implements Receipient,Serializable {
    private String name;
    private String email;
    private String designation;

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

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Official(String name, String email, String designation) {
        this.name = name;
        this.email = email;
        this.designation = designation;
    }

    @Override
    public void Writemail(String name,String email) {
        
        System.out.println("Writemail");
    }

    @Override
    public void Birthdaymail(String name,String email) {
   
}

    
    public void writetofile(String email, String name, String designation) {
        BufferedWriter writer = null;
        try {
            String outputText = name+"|"+email+"|"+designation;
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
   
}
