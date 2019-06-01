package edu.kpi.hotel.model.util;

import edu.kpi.hotel.model.entity.User;
import edu.kpi.hotel.model.exception.SignInFailedException;
import org.mindrot.jbcrypt.BCrypt;

public class HashUtil {
    public static void verifyPassword(User user, String password)
            throws SignInFailedException {
        if (password == null || !BCrypt.checkpw(password, user.getPasswordHash()))
            throw new SignInFailedException("Invalid password");
    }

    public static String hashPassword(String password) {
        if (password == null)
            throw new NullPointerException("Password cannot be null");

        var salt = BCrypt.gensalt();
        return BCrypt.hashpw(password, salt);
    }
}