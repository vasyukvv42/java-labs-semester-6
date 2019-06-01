package edu.kpi.hotel.model.service.proxy;

import edu.kpi.hotel.model.entity.Hotel;
import edu.kpi.hotel.model.entity.RoomRequest;
import edu.kpi.hotel.model.entity.User;
import edu.kpi.hotel.model.exception.AccessDeniedException;
import edu.kpi.hotel.model.service.api.RoomRequestService;
import edu.kpi.hotel.model.service.impl.RoomRequestServiceImpl;
import org.bson.types.ObjectId;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class RoomRequestServiceProxy implements RoomRequestService {
    private final User user;
    private final RoomRequestService roomRequestService = new RoomRequestServiceImpl();

    public RoomRequestServiceProxy(User user) {
        this.user = user;
    }

    @Override
    public void createRoomRequest(User user,
                                  Hotel hotel,
                                  Integer people,
                                  BigDecimal maxCost,
                                  Date reserveFrom,
                                  Date reserveTo) throws AccessDeniedException {
        if (user == null || !this.user.isClient() || !user.equals(this.user))
            throw new AccessDeniedException();

        roomRequestService.createRoomRequest(user, hotel, people, maxCost, reserveFrom, reserveTo);
    }

    @Override
    public void acceptRoomRequest(RoomRequest request) throws AccessDeniedException {
        if (user == null || !user.isAdministrator())
            throw new AccessDeniedException();

        roomRequestService.acceptRoomRequest(request);
    }

    @Override
    public void declineRoomRequest(RoomRequest request) throws AccessDeniedException {
        if (user == null || !user.isAdministrator())
            throw new AccessDeniedException();

        roomRequestService.declineRoomRequest(request);
    }

    @Override
    public List<RoomRequest> getPendingRoomRequests() throws AccessDeniedException {
        if (user == null || !user.isAdministrator())
            throw new AccessDeniedException();

        return roomRequestService.getPendingRoomRequests();
    }

    @Override
    public List<RoomRequest> getRoomRequestsByUser(User user) throws AccessDeniedException {
        if (this.user == null || !this.user.isClient())
            throw new AccessDeniedException();

        return roomRequestService.getRoomRequestsByUser(user);
    }

    @Override
    public Optional<RoomRequest> getRoomRequestById(ObjectId id) throws AccessDeniedException {
        if (user == null || !user.isAdministrator())
            throw new AccessDeniedException();

        return roomRequestService.getRoomRequestById(id);
    }


}
