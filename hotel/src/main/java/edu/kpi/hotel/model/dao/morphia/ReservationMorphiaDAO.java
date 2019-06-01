package edu.kpi.hotel.model.dao.morphia;

import dev.morphia.Datastore;
import edu.kpi.hotel.model.dao.api.ReservationDAO;
import edu.kpi.hotel.model.entity.Hotel;
import edu.kpi.hotel.model.entity.Reservation;
import edu.kpi.hotel.model.entity.Room;
import edu.kpi.hotel.model.entity.User;

import java.util.Date;
import java.util.List;

public class ReservationMorphiaDAO extends MorphiaDAO<Reservation> implements ReservationDAO {
    public ReservationMorphiaDAO(Datastore datastore) {
        super(datastore, Reservation.class);
    }

    @Override
    public List<Reservation> getReservationsByUser(User user) {
        var query = datastore.createQuery(entityClass)
                .filter("user", user);
        return query.find().toList();
    }

    @Override
    public Room getRoomByReservation(Reservation reservation) {
        var hotel = reservation.getHotel();
        var rooms = hotel.getRooms();

        return rooms.get(reservation.getRoomNumber());
    }

    @Override
    public List<Reservation> getReservationsInRange(Hotel hotel, Integer roomNumber, Date from, Date to) {
        var query = datastore.createQuery(entityClass)
                .filter("hotel", hotel)
                .filter("roomNumber", roomNumber);

        query.or(
                query.and(
                        query.criteria("reservedFrom").lessThanOrEq(from),
                        query.criteria("reservedTo").greaterThanOrEq(from)
                ),
                query.and(
                        query.criteria("reservedFrom").lessThanOrEq(to),
                        query.criteria("reservedTo").greaterThanOrEq(to)
                ),
                query.and(
                        query.criteria("reservedFrom").greaterThan(from),
                        query.criteria("reservedTo").lessThan(to)
                )
        );

        return query.find().toList();
    }
}
