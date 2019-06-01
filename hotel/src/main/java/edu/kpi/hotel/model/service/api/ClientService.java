package edu.kpi.hotel.model.service.api;

import edu.kpi.hotel.model.entity.User;
import edu.kpi.hotel.model.exception.AccessDeniedException;

import java.math.BigDecimal;

public interface ClientService extends UserService {
    void refillBalance(User user, BigDecimal amount) throws AccessDeniedException;
}
