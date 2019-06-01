package edu.kpi.hotel.model.service.proxy;

import edu.kpi.hotel.model.entity.User;
import edu.kpi.hotel.model.exception.AccessDeniedException;
import edu.kpi.hotel.model.exception.SignInFailedException;
import edu.kpi.hotel.model.service.api.UserService;

public abstract class UserServiceProxy implements UserService {
    protected final UserService userService;

    UserServiceProxy(UserService userService) {
        this.userService = userService;
    }

    @Override
    public User login(String usernameOrEmail, String password) throws SignInFailedException, AccessDeniedException {
        return userService.login(usernameOrEmail, password);
    }

    @Override
    public User createAccount(String username, String email, String password) throws AccessDeniedException {
        return userService.createAccount(username, email, password);
    }
}
