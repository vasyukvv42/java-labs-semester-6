package edu.kpi.hotel.model.dao.api;

import edu.kpi.hotel.model.entity.RoomRequest;
import edu.kpi.hotel.model.entity.User;

import java.util.List;

public interface RoomRequestDAO extends DAO<RoomRequest> {
    List<RoomRequest> getPendingRoomRequests();
    List<RoomRequest> getRoomRequestsByUser(User user);
}
