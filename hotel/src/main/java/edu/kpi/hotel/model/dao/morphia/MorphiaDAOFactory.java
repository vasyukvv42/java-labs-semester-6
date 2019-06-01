package edu.kpi.hotel.model.dao.morphia;

import dev.morphia.Datastore;
import edu.kpi.hotel.model.dao.api.*;

public class MorphiaDAOFactory implements DAOFactory {
    private HotelDAO hotelDAO;
    private ReservationDAO reservationDAO;
    private RoomRequestDAO requestDAO;
    private UserDAO userDAO;

    private Datastore datastore;

    public MorphiaDAOFactory() {
        this.datastore = MongoConnection.getInstance().getDatastore();
    }

    public MorphiaDAOFactory(Datastore datastore) {
        this.datastore = datastore;
    }

    @Override
    public HotelDAO getHotelDAO() {
        if (hotelDAO == null)
            hotelDAO = new HotelMorphiaDAO(datastore);

        return hotelDAO;
    }

    @Override
    public ReservationDAO getReservationDAO() {
        if (reservationDAO == null)
            reservationDAO = new ReservationMorphiaDAO(datastore);

        return reservationDAO;
    }

    @Override
    public RoomRequestDAO getRoomRequestDAO() {
        if (requestDAO == null)
            requestDAO = new RoomRequestMorphiaDAO(datastore);

        return requestDAO;
    }

    @Override
    public UserDAO getUserDAO() {
        if (userDAO == null)
            userDAO = new UserMorphiaDAO(datastore);

        return userDAO;
    }
}
