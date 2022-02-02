package by.zastr.cafe.util.impl;

import org.testng.annotations.Test;
import org.testng.Assert;

public class InputValidatorImplTest {
	InputValidatorImpl validator = InputValidatorImpl.getInstance();

  @Test
  public void isCorrectWrongEmailTest() {
	String wrongEmail = "qwerty12345";  
	Assert.assertFalse(validator.isCorrectEmail(wrongEmail));  
  }
  
  @Test
  public void isCorrectEmailTest() {
	String email = "qwerty12345@mail.ru";  
	Assert.assertTrue(validator.isCorrectEmail(email));  
  }

  @Test
  public void isCorrectWrongNameTest() {
	String wrongName = "Q"; 
	Assert.assertFalse(validator.isCorrectName(wrongName)); 
  }
  
  @Test
  public void isCorrectNameTest() {
	String name = "Qwerty"; 
	Assert.assertTrue(validator.isCorrectName(name)); 
  }

  @Test
  public void isCorrectPasswordTest() {
	String password = "asdfg_1";
	Assert.assertTrue(validator.isCorrectPassword(password)); 
  }
  
  @Test
  public void isCorrectWrongPasswordTest() {
	String password = "g_1";
	Assert.assertFalse(validator.isCorrectPassword(password)); 
  }

  @Test
  public void isCorrectPriceTest() {
	String price = "5.0";
	Assert.assertTrue(validator.isCorrectPrice(price));
  }
  
  @Test
  public void isCorrectWrongPriceTest() {
	String price = "-5.0";
	Assert.assertFalse(validator.isCorrectPrice(price));
  }

  @Test
  public void isCorrectWeightTest() {
	String weight = "200";
	Assert.assertTrue(validator.isCorrectWeight(weight));
  }
  
  @Test
  public void isCorrectWrongWeightTest() {
	String weight = "-200";
	Assert.assertFalse(validator.isCorrectWeight(weight));
  }
  
}
