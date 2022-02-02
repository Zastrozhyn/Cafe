package by.zastr.cafe.util;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Class PasswordEncryptor.
 *
 * @author A.Zastrozhyn
 */
public final class PasswordEncryptor {
	private static final String SALT = BCrypt.gensalt();
	
	/**
	 * Encrypt.
	 *
	 * @param password the password
	 * @return encrypted password
	 */
    public static String encrypt(String password) {
        return BCrypt.hashpw(password, SALT);
    }

    private PasswordEncryptor() {}

}
