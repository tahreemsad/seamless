package com.seamless.ers.qa.cucumber.Utilities.SendEmail;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendFileEmail
{
	
   public static void main(String args[]) throws Exception
   {
	   FolderZiper folderZip = new FolderZiper();
	   folderZip.zip();
	   
	   DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	   Date date = new Date();
	   
	   final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

      // Sender's email ID needs to be mentioned
      final String from = "zain.jamshaid@seamless.se";

      // Assuming you are sending email from localhost
      
      // Get system properties
      Properties properties = System.getProperties();

      // Setup mail server
      properties.setProperty("mail.smtp.ssl.enable", "false");
      
      properties.setProperty("mail.smtp.host", "smtp.googlemail.com");
      properties.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
      properties.setProperty("mail.smtp.socketFactory.fallback", "false");
      properties.setProperty("mail.smtp.port", "993");
      properties.setProperty("mail.smtp.socketFactory.port", "465");
      properties.put("mail.smtp.auth", "true");
      properties.put("mail.debug", "false");
      properties.put("mail.store.protocol", "pop3");
      properties.put("mail.transport.protocol", "smtp");
      final String password = "vkyyyeeyycnvkzlg";
      
      try{
    	  
    	  Session session = Session.getDefaultInstance(properties, 
                  new Authenticator(){
                     protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, password);
                     }});
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.addRecipients(Message.RecipientType.TO, 
                 InternetAddress.parse("dev-iron@seamless.se, irfan.malik@seamless.se"));

         // Set Subject: header field
         message.setSubject("Cucumber Test Report | "+ date+ " | CASHIN");

         // Create the message part 
         BodyPart messageBodyPart = new MimeBodyPart();

         // Fill the message
         messageBodyPart.setText("Download the attached folder, unzip it and run the 'index.html' file to see the Cucumber test report");
         
         // Create a multipar message
         Multipart multipart = new MimeMultipart();
         
         // Set text message part
         multipart.addBodyPart(messageBodyPart);

         messageBodyPart = new MimeBodyPart();
         String filename = folderZip.path;
         System.out.println("Zipping the file: "+filename);
         DataSource source = new FileDataSource(filename);
         messageBodyPart.setDataHandler(new DataHandler(source));
         messageBodyPart.setFileName(filename);
         multipart.addBodyPart(messageBodyPart);

         // Send the complete message parts
         message.setContent(multipart);

         // Send message
        // Transport.send(message);
         folderZip.DeleteFolder();
         System.out.println("Sent message successfully....");
      }catch (MessagingException mex) {
         mex.printStackTrace();
      }
   }
}