package crowdsource;

import static org.junit.Assert.*;
import model.bl.GeneralLogic;
import model.dao.Person;
import model.dao.TaskWorker;

import org.junit.Test;
import org.springframework.ui.Model;

import controller.LoginController;

public class LogicTest {

	
	@Test
	public void checkPaymentTest() {
		
		GeneralLogic gl = new GeneralLogic();
		double payment = 2;
		boolean result = gl.checkPayment(payment);
		
		assertSame(result,true);
	}
	
	@Test
	public void checkNotPaymentTest() {
		
		GeneralLogic gl = new GeneralLogic();
		double payment = 0;
		boolean result = gl.checkPayment(payment);
		
		assertSame(result,false);
	}
	

	@Test
	public void checkCredibilityAsignmentTest() {
		
		GeneralLogic gl = new GeneralLogic();
		double appreciation = 0.5;
		boolean result = gl.checkCredibilityAssignment(appreciation);
		
		assertSame(result,true);
	}
	
	@Test
	public void checkCredibilityNotAsignmentTest() {
		
		GeneralLogic gl = new GeneralLogic();
		double appreciation = 0;
		boolean result = gl.checkCredibilityAssignment(appreciation);
		
		assertSame(result,false);
	}
	
	@Test
	public void checkMaxWorkersAreHiredTest() {
		
		GeneralLogic gl = new GeneralLogic();
		int maxNeededWorker = 3;
		long numberOfAcceptedWorkers = 4;
		boolean result = gl.checkIfMaxWorkersAreHired(numberOfAcceptedWorkers, maxNeededWorker);
		
		assertSame(result,true);
	}
	
	@Test
	public void checkMaxWorkersNotHiredTest() {
		
		GeneralLogic gl = new GeneralLogic();
		int maxNeededWorker = 4;
		long numberOfAcceptedWorkers = 3;
		boolean result = gl.checkIfMaxWorkersAreHired(numberOfAcceptedWorkers, maxNeededWorker);
		
		assertSame(result,false);
	}
	
	@Test
	public void checkCredibilityAssignedOfUserTest() {
		
		GeneralLogic gl = new GeneralLogic();
		long numNotAssignCredebiltyToWorker = 0;
		boolean result = gl.checkCredibilityAssignedOfUser(numNotAssignCredebiltyToWorker);
		
		assertSame(result,true);
	}
	
	@Test
	public void checkCredibilityNotAssignedOfUserTest() {
		
		GeneralLogic gl = new GeneralLogic();
		long numNotAssignCredebiltyToWorker = 1;
		boolean result = gl.checkCredibilityAssignedOfUser(numNotAssignCredebiltyToWorker);
		
		assertSame(result,false);
	}
	
	@Test
	public void checkIfSkillAlreadyAvailableTest() {
		
		GeneralLogic gl = new GeneralLogic();
		long numSkillAlreadyAvailableForWorker = 1;
		boolean result = gl.checkIfSkillAlreadyAvailable( numSkillAlreadyAvailableForWorker);
		
		assertSame(result,true);
	}
	
	@Test
	public void checkIfSkillAlreadyNotAvailableTest() {
		
		GeneralLogic gl = new GeneralLogic();
		long numSkillAlreadyAvailableForWorker = 0;
		boolean result = gl.checkIfSkillAlreadyAvailable( numSkillAlreadyAvailableForWorker);
		
		assertSame(result,false);
	}
	
	@Test
	public void numUserNameExistanceTest() {
		
		GeneralLogic gl = new GeneralLogic();
		long numUserNameExistance = 0;
		boolean result = gl.checkIfUserNameExist( numUserNameExistance);
		
		assertSame(result,false);
	}
	
	@Test
	public void numUserNameExistanceBTest() {
		
		GeneralLogic gl = new GeneralLogic();
		long numUserNameExistance = 1;
		boolean result = gl.checkIfUserNameExist( numUserNameExistance);
		
		assertSame(result, true);
	}
		
}
