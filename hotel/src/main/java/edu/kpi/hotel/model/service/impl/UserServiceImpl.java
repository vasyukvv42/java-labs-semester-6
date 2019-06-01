package edu.kpi.hotel.model.service.impl;

import edu.kpi.hotel.model.dao.api.DAOFactory;
import edu.kpi.hotel.model.dao.morphia.MorphiaDAOFactory;
import edu.kpi.hotel.model.entity.User;
import edu.kpi.hotel.model.entity.UserRole;
import edu.kpi.hotel.model.exception.SignInFailedException;
import edu.kpi.hotel.model.service.api.UserService;
import edu.kpi.hotel.model.util.HashUtil;

public abstract class UserServiceImpl implements UserService {

    private DAOFactory daoFactory = new MorphiaDAOFactory();

    public abstract UserRole getUserRole();

    @Override
    public User login(String usernameOrEmail, String password)
            throws SignInFailedException {
        var userDAO = daoFactory.getUserDAO();
        var user = userDAO.getByUsernameOrEmail(usernameOrEmail);

        if (user.isPresent())
            HashUtil.verifyPassword(user.get(), password);
        else
            throw new SignInFailedException("Specified user does not exist");

        return user.get();
    }

    @Override
    public User createAccount(String username, String email, String password) {
        var user = new User();

        user.setRole(getUserRole());
        user.setUsername(username);
        user.setEmail(email);

        var passwordHash = HashUtil.hashPassword(password);
        user.setPasswordHash(passwordHash);

        var userDAO = daoFactory.getUserDAO();
        userDAO.save(user);

        return user;
    }

}
