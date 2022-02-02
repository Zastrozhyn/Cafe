package by.zastr.cafe.util;

/**
 * class LocaleValidator.
 *
 * @author A.Zastrozhyn
 */
public class LocaleValidator {
    private static final String ENGLISH_LOCALE = "en_US";
    private static final String RUSSIAN_LOCALE = "ru_RU";

    private LocaleValidator() {
    }

    /**
     * Checks if is locale exist.
     *
     * @param locale the locale
     * @return boolean is Locale Exist
     */
    public static boolean isLocaleExist(String locale) {
        return locale != null && locale.matches(ENGLISH_LOCALE + "|" + RUSSIAN_LOCALE);
    }

}
