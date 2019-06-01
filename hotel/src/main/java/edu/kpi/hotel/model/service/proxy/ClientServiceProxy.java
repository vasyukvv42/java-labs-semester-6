package edu.kpi.hotel.model.service.proxy;

import edu.kpi.hotel.model.entity.User;
import edu.kpi.hotel.model.exception.AccessDeniedException;
import edu.kpi.hotel.model.exception.SignInFailedException;
import edu.kpi.hotel.model.service.api.ClientService;
import edu.kpi.hotel.model.service.impl.ClientServiceImpl;

import java.math.BigDecimal;

public class ClientServiceProxy extends UserServiceProxy implements ClientService {
    private final User user;

    public ClientServiceProxy(User user) {
        super(new ClientServiceImpl());

        this.user = user;
    }

    @Override
    public void refillBalance(User user, BigDecimal amount) throws AccessDeniedException {
        if (this.user == null || !this.user.isClient() || !this.user.equals(this.user))
            throw new AccessDeniedException();

        ((ClientService) userService).refillBalance(user, amount);
    }

    @Override
    public User login(String usernameOrEmail, String password) throws SignInFailedException, AccessDeniedException {
        if (user != null)
            throw new AccessDeniedException();

        return super.login(usernameOrEmail, password);
    }

    @Override
    public User createAccount(String username, String email, String password) throws AccessDeniedException {
        if (user != null)
            throw new AccessDeniedException();

        return super.createAccount(username, email, password);
    }
}
