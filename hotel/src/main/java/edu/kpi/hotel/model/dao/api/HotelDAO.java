package edu.kpi.hotel.model.dao.api;

import edu.kpi.hotel.model.entity.Hotel;
import edu.kpi.hotel.model.entity.Room;
import edu.kpi.hotel.model.entity.RoomRequest;

import java.util.Map;

public interface HotelDAO extends DAO<Hotel> {
    Map<Integer, Room> getSuitableRooms(RoomRequest request);
}
