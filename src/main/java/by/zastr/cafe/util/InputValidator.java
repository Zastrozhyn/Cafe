package by.zastr.cafe.util;

public interface InputValidator {

	boolean isCorrectEmail(String email);

	boolean isCorrectPassword(String password);

	boolean arePasswordsEqual(String firstPassword, String secondPassword);

	boolean isCorrectName(String userName);


}