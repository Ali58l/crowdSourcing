package model.bl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import service.PersonService;
import service.TaskService;
import model.dao.Person;
import model.dao.Proposals;
import model.dao.Skills;

@Component
@Scope("session")
public class GeneralLogic {
	
	 @Autowired private PersonService personSvc;
	 @Autowired private TaskService taskSvc;
	
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

	 public String isLoginWorker(Person person) {
			if ( person.isWorker() == false)
			  {
				  return ("/page/options");  
			  }
			  else
			  {
				  return ("/page/optionsWorker");
			  }
		}
	 
	 public boolean sessionValidationWorker(Person person) {
			if (person != null && !person.getUsername().equals("") && person.isWorker())
			  {
				  return true;
			  }
			  else
			  {
				  return false;
			  }
		}
	 
	 public boolean sessionValidationUser(Person person) {
			if (person != null && !person.getUsername().equals("") && !person.isWorker())
			{
				  return true;
			}
			else
			{
				  return false;
			}
		}


	public boolean checkCredibilityAssignment(double appreciation) {
		
		boolean result = false;
		if ( appreciation > 0 )
		{
			result = true;
		}
		
		
		return result;
	}


	public boolean checkIfMaxWorkersAreHired(long numberOfAcceptedWorkers,int maxNeededWorker) {
		boolean result = false;
		if ( numberOfAcceptedWorkers >=  maxNeededWorker )
		 {
			  result = true;
		 }
		 		
		return result;
	}


	public boolean checkCredibilityAssignedOfUser(
			long numCredebilityareAssiged) {
	
		boolean result = false;
		if ( numCredebilityareAssiged < 1)
		{
			result = true;
		}
		
		return result;
	}


	public boolean checkIfSkillAlreadyAvailable(long numSkillAlreadyAvailableForWorker) {
		boolean result = false;
		if ( numSkillAlreadyAvailableForWorker > 0 )
		{
			result = true;
		}
		return result;
	}


	public boolean checkIfUserNameExist(long numUserNameExistance) {
		 boolean result = false;
		if ( numUserNameExistance > 0 )
		{
			result = true;
		}
		return result;
	}
}


