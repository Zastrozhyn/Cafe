package by.zastr.cafe.util.impl;

import java.time.LocalTime;

import by.zastr.cafe.util.InputValidator;

/**
 * class InputValidator
 * @author A.Zastrozhyn
 *
 */
public final class InputValidatorImpl implements InputValidator {
    private static InputValidatorImpl instance = new InputValidatorImpl();
    private static final String CHECK_EMAIL = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
    private static final String CHECK_PASSWORD = "[\\d\\D]{1,25}";
    private static final int MAX_LENGTH_NAME = 25;
    private static final int MAX_LENGTH_DESCRIPTION = 255;
    private static final String CHECK_PRICE = "^[^-]\\d*.?\\d+$";
    private static final String CHECK_WEIGHT = "\\d{1,5}";
    private static final LocalTime OPEN_TIME = LocalTime.of(8, 30);
    private static final LocalTime CLOSE_TIME = LocalTime.of(20, 30);
    
    private InputValidatorImpl() {
	}
  
    /**
     * 
     * @return InputValidator
     */
    public static InputValidatorImpl getInstance(){
        return instance;
    }


    /**
     * 
     * @return boolean is Correct Email
     * @param email
     */
	@Override
	public boolean isCorrectEmail(String email) {
        return email.matches(CHECK_EMAIL);
    }
    
    /**
     * 
     * @return boolean is Correct Password
     * @param password
     */
    @Override
	public boolean isCorrectPassword(String password) {
        return password.matches(CHECK_PASSWORD);
    }
    
    /**
     * 
     * @return boolean are Passwords Equal
     * @param first Password
     * @param second Password
     */
    @Override
	public boolean arePasswordsEqual(String firstPassword, String secondPassword) {
        return firstPassword.equals(secondPassword);
    }
    
    /**
     * 
     * @return boolean is Correct Name
     * @param userName
     */
    @Override
	public boolean isCorrectName(String userName) {
        return userName.length() <= MAX_LENGTH_NAME;
    }
    
    /**
     * 
     * @return boolean is Correct Price
     * @param price
     */
    public boolean isCorrectPrice(String price) {
		return price.matches(CHECK_PRICE);
    }
	
    /**
     * 
     * @return  boolean is Correct Description
     * @param description
     */
	public boolean isCorrectDescription(String description) {
        return description.length() <= MAX_LENGTH_DESCRIPTION;
    }
	
    /**
     * 
     * @return boolean is Correct Weight
     * @param weight
     */
	public boolean isCorrectWeight(String weight) {
		return weight.matches(CHECK_WEIGHT);
    }
	
    /**
     * 
     * @return boolean is Correct Time
     * @param time
     */
	public boolean isCorrectTime(LocalTime time) {
		return time.isAfter(LocalTime.now()) && time.isAfter(OPEN_TIME) && time.isBefore(CLOSE_TIME);
    }
    
}
