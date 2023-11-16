package org.cashdash.utils;

import org.mindrot.jbcrypt.BCrypt;

public class Bcrypt {
    public static String hashPassword(String password) {
        // Hash a password with BCrypt
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        return hashedPassword;
    }

    public static boolean checkPassword(String password, String hashed) {
        // Check if the entered password matches the hashed password
        return BCrypt.checkpw(password, hashed);
    }
}
