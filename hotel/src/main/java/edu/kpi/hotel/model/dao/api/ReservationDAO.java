package edu.kpi.hotel.model.dao.api;

import edu.kpi.hotel.model.entity.Hotel;
import edu.kpi.hotel.model.entity.Reservation;
import edu.kpi.hotel.model.entity.Room;
import edu.kpi.hotel.model.entity.User;

import java.util.Date;
import java.util.List;

public interface ReservationDAO extends DAO<Reservation> {
    List<Reservation> getReservationsByUser(User user);

    Room getRoomByReservation(Reservation reservation);

    List<Reservation> getReservationsInRange(Hotel hotel, Integer roomNumber, Date from, Date to);
}
