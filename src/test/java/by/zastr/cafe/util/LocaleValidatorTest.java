package by.zastr.cafe.util;

import org.testng.annotations.Test;
import org.testng.Assert;

public class LocaleValidatorTest {

  @Test
  public void isLocaleExistTest() {
	  String englishLocale = "en_US";
	  Assert.assertTrue(LocaleValidator.isLocaleExist(englishLocale));
  }
  
  @Test
  public void isWrongLocaleExistTest() {
	  String franceLocale = "fr_FR";
	  Assert.assertFalse(LocaleValidator.isLocaleExist(franceLocale));
  }
}
