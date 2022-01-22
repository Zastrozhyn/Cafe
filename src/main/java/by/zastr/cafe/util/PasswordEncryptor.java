package by.zastr.cafe.util;

import org.mindrot.jbcrypt.BCrypt;

public final class PasswordEncryptor {
	private static final String SALT = BCrypt.gensalt();
	
    public static String encrypt(String password) {
        return BCrypt.hashpw(password, SALT);
    }

    private PasswordEncryptor() {}

}
