package web.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Business logic to handle login functions.
 * 
 * @author Ahsan.
 */
public class LoginService {

	/**
	 * Static method returns true for successful login, false otherwise.
	 * @param username
	 * @param password
	 * @param dob
	 * @return
	 */
	public static boolean login(String username, String password, String dob) {
		
		if (username == null || password == null || dob == null) return false;
		System.out.print(username+ password + dob);
		
		// validate the date of birth
		boolean isDOB = isValidDateOfDOB(dob) && "1995-12-11".equals(dob);
		
		// validate the user name
		boolean isValidateUN = isValidateUsername(username) && "kaushi".equals(username);
		
		// validate the password
		boolean isValidatePass = isValidatePassword(password) && "kaushi_pass".equals(password);
		
		// Match a fixed user name, password, Date of Birth
		if (isValidateUN && isValidatePass && isDOB) {
			return true;
		}
		return false;
	}
	
	/**
	 * validate the date string format (yyyy-mm-dd) return true successful login, false otherwise.
	 * @param dob
	 * @return
	 */
    public static boolean isValidDateOfDOB(String dob) {
        DateTimeFormatter dobFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            LocalDate.parse(dob, dobFormatter);
            return true;
        } catch (DateTimeParseException ex) {
            return false;
        }
    }

	/**
	 * validate the user name
	 * @param username
	 * @return
	 */
    public static boolean isValidateUsername(String username) {
        // User name must not be null value and need to be between 3 to 10 characters, and no spaces
        return username != null && username.matches("^[a-zA-Z0-9_]{3,10}$");
    }
    
    /**
     * validate the password
     * @param password
     * @return
     */
    public static boolean isValidatePassword(String password) {
        // Password must be at least 8 characters
        return password != null && password.matches("^[A-Za-z_]{6,}$");
    }
	
	
}
