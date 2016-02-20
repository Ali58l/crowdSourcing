package crowdsource;

import static org.junit.Assert.*;
import model.bl.GeneralLogic;
import model.dao.Person;

import org.junit.Test;
import org.springframework.ui.Model;

import controller.LoginController;

public class LoginTest {

	@Test
	public void loginUserTest() {
		
		GeneralLogic gl = new GeneralLogic();
		Person person = new Person();
		person.setWorker(false);
		String result = gl.isLoginWorker(person);
		
		assertSame(result,"/page/options");
	}
	
	@Test
	public void loginWorkerTest() {
		
		GeneralLogic gl = new GeneralLogic();
		Person person = new Person();
		person.setWorker(true);
		String result = gl.isLoginWorker(person);
		
		assertSame(result,"/page/optionsWorker");
	}
	
	@Test
	public void sessionValidation() {
		
		GeneralLogic gl = new GeneralLogic();
		Person person = new Person();
		person = null;
		boolean result =gl.sessionValidationWorker( person);
		assertSame(result,false);	
	}
	
	@Test
	public void sessionValidationUsername() {
		
		GeneralLogic gl = new GeneralLogic();
		Person person = new Person();
		person.setUsername("");
		boolean result =gl.sessionValidationWorker( person);
		assertSame(result,false);	
	}
	
	@Test
	public void sessionValidationWorker() {
		
		GeneralLogic gl = new GeneralLogic();
		Person person = new Person();
		person.setUsername("");
		person.setWorker(false);
		boolean result =gl.sessionValidationWorker( person);
		assertSame(result,false);	
	}

	@Test
	public void sessionValidationUser() {
		
		GeneralLogic gl = new GeneralLogic();
		Person person = new Person();
		person.setUsername("");
		person.setWorker(true);
		
		boolean result =gl.sessionValidationWorker( person);
		assertSame(result,false);	
	}


}
