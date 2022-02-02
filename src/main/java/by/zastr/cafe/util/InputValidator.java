package by.zastr.cafe.util;

/**
 * interface InputValidator.
 *
 * @author A.Zastrozhyn
 */
public interface InputValidator {

	/**
	 * Checks if is correct email.
	 *
	 * @param email the email
	 * @return true, if is correct email
	 */
	boolean isCorrectEmail(String email);

	/**
	 * Checks if is correct password.
	 *
	 * @param password the password
	 * @return true, if is correct password
	 */
	boolean isCorrectPassword(String password);

	/**
	 * Are passwords equal.
	 *
	 * @param firstPassword the first password
	 * @param secondPassword the second password
	 * @return true, if successful
	 */
	boolean arePasswordsEqual(String firstPassword, String secondPassword);

	/**
	 * Checks if is correct name.
	 *
	 * @param userName the user name
	 * @return true, if is correct name
	 */
	boolean isCorrectName(String userName);


}