package edu.kpi.hotel.model.service.api;

import edu.kpi.hotel.model.entity.Hotel;
import edu.kpi.hotel.model.entity.RoomRequest;
import edu.kpi.hotel.model.entity.User;
import edu.kpi.hotel.model.exception.AccessDeniedException;
import org.bson.types.ObjectId;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface RoomRequestService {
    void createRoomRequest(User user,
                           Hotel hotel,
                           Integer people,
                           BigDecimal maxCost,
                           Date reserveFrom,
                           Date reserveTo) throws AccessDeniedException;

    void acceptRoomRequest(RoomRequest request) throws AccessDeniedException;

    void declineRoomRequest(RoomRequest request) throws AccessDeniedException;

    List<RoomRequest> getPendingRoomRequests() throws AccessDeniedException;
    List<RoomRequest> getRoomRequestsByUser(User user) throws AccessDeniedException;

    Optional<RoomRequest> getRoomRequestById(ObjectId id) throws AccessDeniedException;
}
