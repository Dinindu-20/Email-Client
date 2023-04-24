
package emailclient;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author ASUS-USER
 */
//interface for write and Birthday mails
public interface Receipient {
   void Writemail(String name,String email);
   //void writetofile(String email,String name,String bday);
   void Birthdaymail(String name,String email);
}
