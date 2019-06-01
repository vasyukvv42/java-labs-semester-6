package edu.kpi.hotel.model.dao.api;

import edu.kpi.hotel.model.entity.User;

import java.util.Optional;

public interface UserDAO extends DAO<User> {
    Optional<User> getByUsernameOrEmail(String usernameOrEmail);
}
