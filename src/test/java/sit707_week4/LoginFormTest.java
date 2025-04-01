package sit707_week4;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests functions in LoginForm.
 * 
 * @author Ahsan Habib
 */
public class LoginFormTest {

	@Test
	public void testStudentIdentity() {
		String studentId = "s223681886";
		Assert.assertNotNull("Student ID is null", studentId);
	}

	@Test
	public void testStudentName() {
		String studentName = "s223681886";
		Assert.assertNotNull("Student name is null", studentName);
	}

	@Test
	public void testFailEmptyUsernameAndEmptyPasswordAndDontCareValCode() {
		LoginStatus status = LoginForm.login(null, null);
		Assert.assertTrue(status.isLoginSuccess() == false);
	}

	/*
	 * Write more test functions below.
	 */
	@Test
	public void testFailEmptyUsernameAndWrongPasswordAndDontCareValCode() {
		LoginStatus status = LoginForm.login(null, "xyz");
		Assert.assertTrue(status.isLoginSuccess() == false);
	}

	@Test
	public void testFailWrongUsernameAndWrongPasswordAndDontCareValCode() {
		LoginStatus status = LoginForm.login("abc", "xyz");
		Assert.assertTrue(status.isLoginSuccess() == false);
	}

	@Test
	public void testFailCorrectUsernameAndEmptyPasswordAndDontCareValCode() {
		LoginStatus status = LoginForm.login("ahsan", null);
		Assert.assertTrue(status.isLoginSuccess() == false);
	}

	@Test
	public void testFailCorrectUsernameAndWrongPasswordAndDontCareValCode() {
		LoginStatus status = LoginForm.login("ahsan", "xyz");
		Assert.assertTrue(status.isLoginSuccess() == false);
	}

	@Test
	public void testFailEmptyUsernameAndCorrectPasswordAndDontCareValCode() {
		LoginStatus status = LoginForm.login(null, "ahsan_pass");
		Assert.assertTrue(status.isLoginSuccess() == false);
	}

	@Test
	public void testFailWrongUsernameAndCorrectPasswordAndDontCareValCode() {
		LoginStatus status = LoginForm.login("abc", "ahsan_pass");
		Assert.assertTrue(status.isLoginSuccess() == false);
	}

	@Test
	public void testPassCorrectUsernameAndCorrectPasswordAndDontCareValCode() {
		LoginStatus status = LoginForm.login("ahsan", "ahsan_pass");
		Assert.assertTrue(status.isLoginSuccess() == true);
	}

	@Test
	public void testFailCorrectUsernameAndCorrectPasswordAndEmptyCareValCode() {
		LoginStatus status = LoginForm.login("ahsan", "ahsan_pass");
		if (status.isLoginSuccess()) {
			Assert.assertTrue(LoginForm.validateCode(null) == false);
		}
	}

	@Test
	public void testFailCorrectUsernameAndCorrectPasswordAndWrongCareValCode() {
		LoginStatus status = LoginForm.login("ahsan", "ahsan_pass");
		if (status.isLoginSuccess()) {
			Assert.assertTrue(LoginForm.validateCode("abcd") == false);
		}
	}

	@Test
	public void testPassCorrectUsernameAndCorrectPasswordAndCorrectCareValCode() {
		LoginStatus status = LoginForm.login("ahsan", "ahsan_pass");
		if (status.isLoginSuccess()) {
			Assert.assertTrue(LoginForm.validateCode("123456") == true);
		}
	}
}
