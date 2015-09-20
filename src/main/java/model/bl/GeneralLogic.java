package model.bl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import service.PersonService;
import model.dao.Person;

@Component
public class GeneralLogic {
	
	 @Autowired private PersonService personSvc;
	
	public static Timestamp addTimeStamo(){
	
		Date date = new Date();
		Timestamp ts =  new Timestamp(date.getTime());
		
		return ts;		
	}
	
   
	public void sendEmail(String subject, String textMessage){
		
		// Recipient's email ID needs to be mentioned.
	      String to = "ali_lotfdar@yahoo.com";

	      // Sender's email ID needs to be mentioned
	      String from = "ali_lotfdar@yahoo.com";

	      // Assuming you are sending email from localhost
	      String host = "localhost";
		
		Properties properties = System.getProperties();
		Session session = Session.getDefaultInstance(properties);
		 try{
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

	         // Set Subject: header field
	         message.setSubject(subject);

	         // Now set the actual message
	         message.setText(textMessage);

	         // Send message
	         Transport.send(message);
	         System.out.println("Sent message successfully....");
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	}

//	public Person checkLoginValidation(String username, String password) {
//		Person person = new Person();
//		if ( username.equals("") || password.equals("")){
//			return null;
//		}else{
//			Person p =personSvc.findPersonByUsernameAndPassword(username,password);
//			if ( p == null ){
//				return null;
//			}else{
//				return p;
//			}
//		}
//	}
}
