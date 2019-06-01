package edu.kpi.hotel.model.dao.api;

public interface DAOFactory {
    HotelDAO getHotelDAO();
    ReservationDAO getReservationDAO();
    RoomRequestDAO getRoomRequestDAO();
    UserDAO getUserDAO();
}
