package edu.kpi.hotel.model.service.impl;

import edu.kpi.hotel.model.dao.api.DAOFactory;
import edu.kpi.hotel.model.dao.morphia.MorphiaDAOFactory;
import edu.kpi.hotel.model.entity.User;
import edu.kpi.hotel.model.entity.UserRole;
import edu.kpi.hotel.model.service.api.ClientService;

import java.math.BigDecimal;

public class ClientServiceImpl extends UserServiceImpl implements ClientService {

    private DAOFactory daoFactory = new MorphiaDAOFactory();

    @Override
    public UserRole getUserRole() {
        return UserRole.CLIENT;
    }

    @Override
    public void refillBalance(User user, BigDecimal amount) {
        if (amount == null || amount.compareTo(new BigDecimal(0)) < 0)
            throw new IllegalArgumentException("Amount must be non-negative");

        var newBalance = user.getBalance().add(amount);
        user.setBalance(newBalance);

        var userDAO = daoFactory.getUserDAO();
        userDAO.update(user);
    }
}
