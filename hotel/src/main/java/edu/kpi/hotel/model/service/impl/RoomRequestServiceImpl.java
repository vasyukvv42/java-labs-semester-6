package edu.kpi.hotel.model.service.impl;

import edu.kpi.hotel.model.dao.api.DAOFactory;
import edu.kpi.hotel.model.dao.morphia.MorphiaDAOFactory;
import edu.kpi.hotel.model.entity.*;
import edu.kpi.hotel.model.exception.AccessDeniedException;
import edu.kpi.hotel.model.service.api.RoomRequestService;
import edu.kpi.hotel.model.util.DateUtil;
import org.bson.types.ObjectId;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class RoomRequestServiceImpl implements RoomRequestService {

    private DAOFactory daoFactory = new MorphiaDAOFactory();

    @Override
    public void createRoomRequest(User user,
                                  Hotel hotel,
                                  Integer people,
                                  BigDecimal maxCost,
                                  Date reserveFrom,
                                  Date reserveTo) {

        if (reserveFrom == null || reserveTo == null || reserveFrom.after(reserveTo))
            throw new IllegalArgumentException("Invalid reservation date");

        var expiryDate = DateUtil.getExpiryDate(reserveFrom);

        if (expiryDate.before(new Date()))
            throw new IllegalArgumentException("Invalid reservation date");

        var request = new RoomRequest();
        request.setUser(user);
        request.setHotel(hotel);
        request.setPeople(people);
        request.setMaxCost(maxCost);
        request.setReserveFrom(reserveFrom);
        request.setReserveTo(reserveTo);
        request.setExpiryDate(expiryDate);

        var roomRequestDAO = daoFactory.getRoomRequestDAO();
        roomRequestDAO.save(request);
    }

    @Override
    public void acceptRoomRequest(RoomRequest request) {
        var roomRequestDAO = daoFactory.getRoomRequestDAO();
        request.setStatus(RoomRequestStatus.ACCEPTED);
        roomRequestDAO.update(request);
    }

    @Override
    public void declineRoomRequest(RoomRequest request) {
        var roomRequestDAO = daoFactory.getRoomRequestDAO();
        request.setStatus(RoomRequestStatus.DECLINED);
        roomRequestDAO.update(request);
    }

    @Override
    public List<RoomRequest> getPendingRoomRequests() {
        var roomRequestDAO = daoFactory.getRoomRequestDAO();
        return roomRequestDAO.getPendingRoomRequests();
    }

    @Override
    public List<RoomRequest> getRoomRequestsByUser(User user) {
        var roomRequestDAO = daoFactory.getRoomRequestDAO();
        return roomRequestDAO.getRoomRequestsByUser(user);
    }

    @Override
    public Optional<RoomRequest> getRoomRequestById(ObjectId id) {
        var roomRequestDAO = daoFactory.getRoomRequestDAO();
        return roomRequestDAO.get(id);
    }


}
