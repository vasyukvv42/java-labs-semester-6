package edu.kpi.hotel.model.service.api;

import edu.kpi.hotel.model.entity.User;
import edu.kpi.hotel.model.exception.AccessDeniedException;
import edu.kpi.hotel.model.exception.SignInFailedException;

public interface UserService {
    User login(String usernameOrEmail, String password)
            throws SignInFailedException, AccessDeniedException;

    User createAccount(String username, String email, String password) throws AccessDeniedException;
}
