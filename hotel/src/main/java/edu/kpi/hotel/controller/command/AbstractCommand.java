package edu.kpi.hotel.controller.command;

import edu.kpi.hotel.model.entity.User;
import edu.kpi.hotel.model.service.api.ClientService;
import edu.kpi.hotel.model.service.api.HotelService;
import edu.kpi.hotel.model.service.api.ReservationService;
import edu.kpi.hotel.model.service.api.RoomRequestService;
import edu.kpi.hotel.model.service.proxy.ClientServiceProxy;
import edu.kpi.hotel.model.service.proxy.HotelServiceProxy;
import edu.kpi.hotel.model.service.proxy.ReservationServiceProxy;
import edu.kpi.hotel.model.service.proxy.RoomRequestServiceProxy;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractCommand implements Command {

    protected User getCurrentUser(HttpServletRequest request) {
        var session = request.getSession();
        return (User) session.getAttribute("user");
    }

    protected ClientService getClientService(HttpServletRequest req) {
        var user = getCurrentUser(req);
        return new ClientServiceProxy(user);
    }

    protected HotelService getHotelService(HttpServletRequest req) {
        var user = getCurrentUser(req);
        return new HotelServiceProxy(user);
    }

    protected RoomRequestService getRoomRequestService(HttpServletRequest req) {
        var user = getCurrentUser(req);
        return new RoomRequestServiceProxy(user);
    }

    protected ReservationService getReservationService(HttpServletRequest req) {
        var user = getCurrentUser(req);
        return new ReservationServiceProxy(user);
    }
}
