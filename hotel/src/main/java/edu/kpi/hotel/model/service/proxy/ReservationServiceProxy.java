package edu.kpi.hotel.model.service.proxy;

import edu.kpi.hotel.model.entity.Hotel;
import edu.kpi.hotel.model.entity.Reservation;
import edu.kpi.hotel.model.entity.User;
import edu.kpi.hotel.model.exception.AccessDeniedException;
import edu.kpi.hotel.model.exception.NotEnoughBalanceException;
import edu.kpi.hotel.model.exception.ReservationConflictException;
import edu.kpi.hotel.model.service.api.ReservationService;
import edu.kpi.hotel.model.service.impl.ReservationServiceImpl;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ReservationServiceProxy implements ReservationService {
    private final ReservationService reservationService = new ReservationServiceImpl();
    private final User user;

    public ReservationServiceProxy(User user) {
        this.user = user;
    }

    @Override
    public void createReservation(User user, Hotel hotel, Integer roomNumber, Date reservedFrom, Date reservedTo)
            throws ReservationConflictException, AccessDeniedException {
        if (this.user == null || !this.user.isAdministrator())
            throw new AccessDeniedException();

        reservationService.createReservation(user, hotel, roomNumber, reservedFrom, reservedTo);
    }

    @Override
    public void payInvoice(User user, Reservation reservation)
            throws NotEnoughBalanceException, AccessDeniedException {
        if (this.user == null || !this.user.isClient() || !user.equals(this.user) || !user.equals(reservation.getUser()))
            throw new AccessDeniedException();

        reservationService.payInvoice(user, reservation);
    }

    @Override
    public List<Reservation> getReservationsByUser(User user) throws AccessDeniedException {
        if (this.user == null || !this.user.isClient() || !user.equals(this.user))
            throw new AccessDeniedException();

        return reservationService.getReservationsByUser(user);
    }

    @Override
    public Optional<Reservation> getReservationById(ObjectId id) throws AccessDeniedException {
        if (user == null || !user.isClient())
            throw new AccessDeniedException();

        var reservation = reservationService.getReservationById(id);

        if (reservation.isPresent() && !user.equals(reservation.get().getUser()))
            throw new AccessDeniedException();

        return reservation;
    }
}
