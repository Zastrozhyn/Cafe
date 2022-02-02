package by.zastr.cafe.util.impl;

import java.time.LocalTime;

import by.zastr.cafe.util.InputValidator;

/**
 * class InputValidator.
 *
 * @author A.Zastrozhyn
 */
public final class InputValidatorImpl implements InputValidator {
    private static InputValidatorImpl instance = new InputValidatorImpl();
    private static final String CHECK_EMAIL = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
    private static final String CHECK_PASSWORD = "[\\d\\D]{4,25}";
    private static final int MAX_LENGTH_NAME = 25;
    private static final int MIN_LENGTH_NAME = 2;
    private static final int MAX_LENGTH_DESCRIPTION = 255;
    private static final String CHECK_PRICE = "^[^-]\\d*.?\\d+$";
    private static final String CHECK_WEIGHT = "\\d{1,5}";
    private static final LocalTime OPEN_TIME = LocalTime.of(8, 30);
    private static final LocalTime CLOSE_TIME = LocalTime.of(20, 30);
    
    private InputValidatorImpl() {
	}
  
    /**
     * Gets the single instance of InputValidatorImpl.
     *
     * @return InputValidator
     */
    public static InputValidatorImpl getInstance(){
        return instance;
    }


    /**
     * Checks if is correct email.
     *
     * @param email the email
     * @return boolean is Correct Email
     */
	@Override
	public boolean isCorrectEmail(String email) {
        return email.matches(CHECK_EMAIL);
    }
    
    /**
     * Checks if is correct password.
     *
     * @param password the password
     * @return boolean is Correct Password
     */
    @Override
	public boolean isCorrectPassword(String password) {
        return password.matches(CHECK_PASSWORD);
    }
    
    /**
     * Are passwords equal.
     *
     * @param firstPassword the first password
     * @param secondPassword the second password
     * @return boolean are Passwords Equal
     */
    @Override
	public boolean arePasswordsEqual(String firstPassword, String secondPassword) {
        return firstPassword.equals(secondPassword);
    }
    
    /**
     * Checks if is correct name.
     *
     * @param userName the user name
     * @return boolean is Correct Name
     */
    @Override
	public boolean isCorrectName(String userName) {
        return userName.length() <= MAX_LENGTH_NAME && userName.length() > MIN_LENGTH_NAME;
    }
    
    /**
     * Checks if is correct price.
     *
     * @param price the price
     * @return boolean is Correct Price
     */
    public boolean isCorrectPrice(String price) {
		return price.matches(CHECK_PRICE);
    }
	
    /**
     * Checks if is correct description.
     *
     * @param description the description
     * @return  boolean is Correct Description
     */
	public boolean isCorrectDescription(String description) {
        return description.length() <= MAX_LENGTH_DESCRIPTION;
    }
	
    /**
     * Checks if is correct weight.
     *
     * @param weight the weight
     * @return boolean is Correct Weight
     */
	public boolean isCorrectWeight(String weight) {
		return weight.matches(CHECK_WEIGHT);
    }
	
    /**
     * Checks if is correct time.
     *
     * @param time the time
     * @return boolean is Correct Time
     */
	public boolean isCorrectTime(LocalTime time) {
		return time.isAfter(LocalTime.now()) && time.isAfter(OPEN_TIME) && time.isBefore(CLOSE_TIME);
    }
    
}
