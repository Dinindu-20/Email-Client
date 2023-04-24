/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emailclient;


import java.util.Scanner;
import java.io.*;
import java.util.*;
//import java.lang.String;
//import static java.lang.System.exit;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author ASUS-USER
 */
public class Emailclient {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        LocalDate dateObj = LocalDate.now();
                      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                      String date = dateObj.format(formatter);
        // TODO code application logic here
        while(true){
            Scanner scanner = new Scanner(System.in);
                System.out.println("Enter option type: \n"
                      + "1 - Adding a new recipient\n"
                      + "2 - Sending an email\n"
                      + "3 - Printing out all the recipients who have birthdays\n"
                      + "4 - Printing out details of all the emails sent\n"
                      + "5 - Printing out the number of recipient objects in the application\n"
                      + "6 - Exit from the Emailclient\n");
               
                int option = scanner.nextInt();
                
                
                //while (option != 6){

                switch(option){
                      case 1:
                          // input format - Official: nimal,nimal@gmail.com,ceo
                          // Use a single input to get all the details of a recipient
                          Scanner sc= new Scanner(System.in);
                          System.out.println("Enter the Details in one line separated by comma:\n");
                          System.out.println("If it is Official-(Official:name,email,position)\n");
                          System.out.println("If it is Office friend-(OF:name,email,Birthday(YYYY/MM/DD))\n");
                          System.out.println("If it is friend-(Personal:name,nickname,email,Birthday(YYYY/MM/DD))\n");
                          try{
                          String details=sc.nextLine();
                          
                          List<String> info= Pattern.compile(":")
                            .splitAsStream(details)
                            .collect(Collectors.toList());
                          String name,nickname,email,designation,bday,kindofrec;
                          kindofrec=info.get(0);
                          
                          List<String> infos= Pattern.compile(",")
                            .splitAsStream(info.get(1))
                            .collect(Collectors.toList());
                          
                          //Getting Official's details
                          if (kindofrec.equals("Official")){
                            name=infos.get(0);
                            email=infos.get(1);
                            designation=infos.get(2);

                            Official objname= new Official(name,email,designation);
                            objname.writetofile(objname.getEmail(),objname.getName(), objname.getDesignation());
                            try{
                            FileOutputStream file = new FileOutputStream ("Official.ser");
                            ObjectOutputStream os = new ObjectOutputStream(file);
                            os.writeObject(objname);
                            os.close();
                            file.close();
                            }
                            catch(IOException ex){
                                System.out.println("IoException found");
                                
                            }

                          }
                          //Getting Personal's details
                          else if ((kindofrec).equals("Personal")){
                            name=infos.get(0);
                            nickname=infos.get(1);
                            email=infos.get(2);
                            bday=infos.get(3);
                            
                            Friend objname = new Friend(name,nickname,email,bday);
                            objname.writetofile(email,name,bday,nickname);
                            
                            try{
                            FileOutputStream file = new FileOutputStream ("Friends.ser");
                            ObjectOutputStream os = new ObjectOutputStream(file);
                            os.writeObject(objname);
                            os.close();
                            file.close();
                            }
                            catch(IOException ex){
                                System.out.println("IoException found");
                                
                            }
                            

                          }
                          //Getting Office Friend's details
                          else if (kindofrec.equals("OF")){
                            name=infos.get(0);
                            email=infos.get(1);
                            bday=infos.get(2);
                            
                            try{
                            OfficialFriend officefriend = new OfficialFriend(name,email,bday);
                            officefriend.writetofile(name,email,bday);
                            FileOutputStream file = new FileOutputStream ("Officefriends.ser");
                            ObjectOutputStream os = new ObjectOutputStream(file);
                            os.writeObject(officefriend);
                            os.close();
                            file.close();
                            }
                            catch(IOException ex){
                                System.out.println("IoException found");
                                
                            }
                            
                            

                          }
                          System.out.println(info+" was added to receipient");
                          }catch(Exception e){
                              System.out.println("INPUT IS NOT Correct");
                              
                          }
                          break;
                          
                          
                          
                          
                      case 2:
                          // input format - email, subject, content
                          Scanner sc1= new Scanner(System.in);
                          System.out.println("Enter the Details in one line separated by comma,(email, subject, content)\n");
                          String details1=sc1.nextLine();
                          List<String> info1= Pattern.compile(",")
                            .splitAsStream(details1)
                            .collect(Collectors.toList());
                          String email1,subject,content;
                          email1=info1.get(0);

                          subject=info1.get(1);
                          content=info1.get(2);
                          int i=3;

                          while(i<info1.size()){
                              content+=info1.get(i);
                              i++;
                          }
                          EmailSender emailsender = new EmailSender(date,email1,subject,content);
                          emailsender.writeemail(emailsender.getEmail(),emailsender.getSubject(),emailsender.getContent());
                          FileOutputStream file = new FileOutputStream("Emails.ser");
                          ObjectOutputStream os = new ObjectOutputStream(file);
                          os.writeObject(emailsender);
                          os.close();
                          
 
                          // code to send an email
                          break;
                          
                      case 3:
                          // input format - yyyy/MM/dd (ex: 2018/09/17)

                          Scanner sc2= new Scanner(System.in);
                          System.out.println("Enter the Details in one line separated by '/',yyyy/MM/dd\n");
                          String details2=sc2.nextLine();
                          
                          String[] curdate=details2.split("/");
                          String MD=curdate[1]+"/"+curdate[2];
                          
                          //code to get today birthday list
                          try{

                              BufferedReader reader = new BufferedReader(new FileReader("ClientsList.txt"));
                              String line = null;
                              String birthday="";
                              boolean have=true;
                              String nameis="";
                              int names=0;
                              while ((line = reader.readLine()) != null){
                                  
                                  String[] outline= line.split("");
                                  int num=0,y=0;
                                  for (int j=0;j<line.length();j++){
                                      
                                       if (outline[j].equals("|")){
                                          num++;                                                                               
                                      }
                                      if (num<1){
                                          nameis+=outline[j];
                                      }
                                     
                                      if (num == 2){
                                          
                                          if (have & y>5){
                                              birthday=outline[j];
                                          }
                                          else if (y>5){
                                              birthday+=outline[j];    
                                          }
                                         have=false;
                                         y++; 
                                      }
                                  }

                                    if(birthday.equals(MD)){
                                        System.out.println(nameis +" has a birthday on given date");
                                        names+=1;
                                        
                                    }
                              }
                            reader.close();
                            //If there are no friends in bday
                            if (names==0){
                                System.out.println("No Birthday guys found");
                            }

                          }
                            catch(IOException ex){
                                System.out.println("IOException Found");

                          }


                          // code to print recipients who have birthdays on the given date
                          break;
                          
                      case 4:
                          // input format - yyyy/MM/dd (ex: 2018/09/17)
                          Scanner sc3= new Scanner(System.in);
                          System.out.println("Enter the Details in one line separated by '/',yyyy/MM/dd\n");
                          String details3=sc3.nextLine();
                          FileInputStream fileStream1 = new FileInputStream ("Emails.ser");
                          ObjectInputStream os2 = new ObjectInputStream(fileStream1);
                          while(true) {
                    
                            try{
                               Object three =os2.readObject();
                               EmailSender emailsend =(EmailSender) three;

                               
                               if (emailsend.getDate().equals(details3)){
                                   System.out.println("Emails send on that day to "+emailsend.getEmail());
                                   System.out.println(" Day is "+emailsend.getDate());
                                   System.out.println(" Subject is "+emailsend.getSubject());
                                   System.out.println(" Content is "+emailsend.getContent());
                                   

                           }
                       }
                           catch(EOFException e){
                               break;
                       }
                            catch(IOException ex){
                                
                            }
                          }
                          os2.close();
                          fileStream1.close();
         
                          // code to print the details of all the emails sent on the input date
                          break;
                      case 5:
                          // code to print the number of recipient objects in the application
                          try{

                              BufferedReader reader = new BufferedReader(new FileReader("ClientsList.txt"));
                              String line = null;
                              int numofobj=0;

                              while ((line = reader.readLine()) != null){
                                    numofobj++;

                          }
                              reader.close();
                              System.out.println("Number Of Receipients In Application Is"+" "+numofobj);
                          }
                            catch(IOException ex){
                                ex.printStackTrace();

                          }
 
                          break;
                      case 6:
                          System.out.println("Exited from Emailclient");
                          break;
                  
                }
                String today=date.substring(5,date.length());
              
                    FileInputStream fileS = new FileInputStream ("Friends.ser");
                    ObjectInputStream os1 = new ObjectInputStream(fileS);

                    //Sending birthday emails whose birthday today
                   while(true){
                        try{
                        
                        Object one =os1.readObject();
                        Friend friendA =(Friend) one;
                        String bday=friendA.getBday().substring(5,friendA.getBday().length());
                        
                        
                    
                        if (bday.equals(today)){
                            friendA.Birthdaymail(friendA.getName(),friendA.getEmail());
                            System.out.println("Today is "+friendA.getName()+"\'s Birth day. Birthday Email was sent.");
                        
                        }
                        
                    }
                        catch(EOFException | ClassCastException e){
                            break;
   
                    }
                        catch (IOException ex){
                            
                        }
                   
                }
                    os1.close();
                    fileS.close();
    
                //cheking Official Friends
                FileInputStream file = new FileInputStream ("Officefriends.ser");
                ObjectInputStream os0 = new ObjectInputStream(file);
                
                while(true) {
                    
                     try{
                        Object two =os0.readObject();
                        OfficialFriend Officefriend =(OfficialFriend) two;
                        String bday=Officefriend.getBday().substring(5,Officefriend.getBday().length());
                        //System.out.println("in1");
                        if (bday.equals(today)){
                            System.out.println(Officefriend.getName());
                            Officefriend.Birthdaymail(Officefriend.getName(),Officefriend.getEmail());
                            System.out.println("Today is "+Officefriend.getName()+"\'s Birth day. Birthday Email was sent.");
                        
                    }
                }
                    catch(EOFException i){
                        break;
                    
                }
                     catch(IOException ix){
                         
                     }
                    
                }
                 os0.close();
                 file.close();
             
}
    }

}