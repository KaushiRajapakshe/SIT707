package web.service;
import static org.junit.Assert.*;
import org.junit.Test;

public class LoginServiceUnitTest {

    @Test
    public void testValidateUsernameInLogin() {
        assertTrue(LoginService.isValidateUsername("user_1"));
        assertTrue(LoginService.isValidateUsername("abc_123"));
    }

    @Test
    public void testInvalidateUsernameInLogin() {
        assertFalse(LoginService.isValidateUsername("ab"));           // short user name
        assertFalse(LoginService.isValidateUsername("username_123")); // long user name
        assertFalse(LoginService.isValidateUsername("user name"));    // having space
        assertFalse(LoginService.isValidateUsername(""));             // empty value pass
        assertFalse(LoginService.isValidateUsername(null));           // null value pass
    }

    @Test
    public void testValidatePasswordInLogin() {
        assertTrue(LoginService.isValidatePassword("secure_pass"));
        assertTrue(LoginService.isValidatePassword("Password_"));
    }

    @Test
    public void testInvalidatePasswordInLogin() {
        assertFalse(LoginService.isValidatePassword("short"));   // < 6
        assertFalse(LoginService.isValidatePassword("123456"));  // numbers only
        assertFalse(LoginService.isValidatePassword(null));      // null
    }

    @Test
    public void testValidateDOBInLogin() {
        assertTrue(LoginService.isValidDateOfDOB("2010-10-11"));
    }

    @Test
    public void testInvalidateDOBInLogin() {
        assertFalse(LoginService.isValidDateOfDOB("11-10-2010"));  // Incorrect format
        assertFalse(LoginService.isValidDateOfDOB("2010/10/11"));  // Incorrect format
        assertFalse(LoginService.isValidDateOfDOB("abc"));         // invalid text value
        assertFalse(LoginService.isValidDateOfDOB(""));          // null value
    }

    @Test
    public void testLoginSuccessMessage() {
        assertTrue(LoginService.login("kaushi", "kaushi_pass", "1995-12-11"));
    }

    @Test
    public void testLoginFailureWithIncorrectUsername() {
        assertFalse(LoginService.login("Incorrectuser1", "kaushi_pass", "1995-12-11"));
    }

    @Test
    public void testLoginFailureWithIncorrectPassword() {
        assertFalse(LoginService.login("kaushi", "Incorrectpass2", "1995-12-11"));
    }

    @Test
    public void testLoginFailureWithIncorrectDOB() {
        assertFalse(LoginService.login("kaushi", "kaushi_pass", "1990/01/01"));
    }
}