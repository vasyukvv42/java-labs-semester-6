package edu.kpi.hotel.model.service.api;

import edu.kpi.hotel.model.entity.Hotel;
import edu.kpi.hotel.model.entity.Room;
import edu.kpi.hotel.model.entity.RoomRequest;
import edu.kpi.hotel.model.exception.AccessDeniedException;
import org.bson.types.ObjectId;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface HotelService {
    void createHotel(String name, String country, String city, String address, Integer rating)
            throws AccessDeniedException;

    void createRoom(Hotel hotel, Integer number, Integer maxPeople, BigDecimal cost) throws AccessDeniedException;

    List<Hotel> getHotels() throws AccessDeniedException;
    Optional<Hotel> getHotelById(ObjectId id) throws AccessDeniedException;
    Map<Integer, Room> getSuitableRooms(RoomRequest request) throws AccessDeniedException;
}
