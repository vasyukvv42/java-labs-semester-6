package edu.kpi.hotel.model.service.proxy;

import edu.kpi.hotel.model.service.api.AdministratorService;
import edu.kpi.hotel.model.service.impl.AdministratorServiceImpl;

public class AdministratorServiceProxy extends UserServiceProxy implements AdministratorService {
    public AdministratorServiceProxy() {
        super(new AdministratorServiceImpl());
    }
}
