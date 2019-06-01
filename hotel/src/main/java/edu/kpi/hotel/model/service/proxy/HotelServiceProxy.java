package edu.kpi.hotel.model.service.proxy;

import edu.kpi.hotel.model.entity.Hotel;
import edu.kpi.hotel.model.entity.Room;
import edu.kpi.hotel.model.entity.RoomRequest;
import edu.kpi.hotel.model.entity.User;
import edu.kpi.hotel.model.exception.AccessDeniedException;
import edu.kpi.hotel.model.service.api.HotelService;
import edu.kpi.hotel.model.service.impl.HotelServiceImpl;
import org.bson.types.ObjectId;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class HotelServiceProxy implements HotelService {

    private final HotelService hotelService = new HotelServiceImpl();
    private final User user;

    public HotelServiceProxy(User user) {
        this.user = user;
    }

    @Override
    public void createHotel(String name, String country, String city, String address, Integer rating)
            throws AccessDeniedException {
        if (user == null || !user.isAdministrator())
            throw new AccessDeniedException();

        hotelService.createHotel(name, country, city, address, rating);
    }

    @Override
    public void createRoom(Hotel hotel, Integer number, Integer maxPeople, BigDecimal cost) throws AccessDeniedException {
        if (user == null || !user.isAdministrator())
            throw new AccessDeniedException();

        hotelService.createRoom(hotel, number, maxPeople, cost);
    }

    @Override
    public List<Hotel> getHotels() throws AccessDeniedException {
//        if (user == null)
//            throw new AccessDeniedException();

        return hotelService.getHotels();
    }

    @Override
    public Optional<Hotel> getHotelById(ObjectId id) throws AccessDeniedException {
        if (user == null)
            throw new AccessDeniedException();

        return hotelService.getHotelById(id);
    }

    @Override
    public Map<Integer, Room> getSuitableRooms(RoomRequest request) throws AccessDeniedException {
        if (user == null || !user.isAdministrator())
            throw new AccessDeniedException();

        return hotelService.getSuitableRooms(request);
    }
}
