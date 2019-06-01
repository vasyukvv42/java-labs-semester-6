package edu.kpi.hotel.model.service.impl;

import edu.kpi.hotel.model.entity.UserRole;
import edu.kpi.hotel.model.service.api.AdministratorService;

public class AdministratorServiceImpl extends UserServiceImpl implements AdministratorService {
    @Override
    public UserRole getUserRole() {
        return UserRole.ADMINISTRATOR;
    }
}
