package edu.kpi.hotel;

import edu.kpi.hotel.model.service.impl.AdministratorServiceImpl;

public class Main {
    public static void main(String[] args) {
        var adminService = new AdministratorServiceImpl();
        adminService.createAccount("admin", "sereda@medo.kk", "123");
    }
}
