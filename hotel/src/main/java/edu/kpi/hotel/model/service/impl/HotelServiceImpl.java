package edu.kpi.hotel.model.service.impl;

import edu.kpi.hotel.model.dao.api.DAOFactory;
import edu.kpi.hotel.model.dao.morphia.MorphiaDAOFactory;
import edu.kpi.hotel.model.entity.Hotel;
import edu.kpi.hotel.model.entity.Room;
import edu.kpi.hotel.model.entity.RoomRequest;
import edu.kpi.hotel.model.service.api.HotelService;
import org.bson.types.ObjectId;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class HotelServiceImpl implements HotelService {

    private DAOFactory daoFactory = new MorphiaDAOFactory();

    @Override
    public void createHotel(String name, String country, String city, String address, Integer rating) {
        var hotel = new Hotel();
        hotel.setName(name);
        hotel.setCountry(country);
        hotel.setCity(city);
        hotel.setAddress(address);
        hotel.setRating(rating);

        var hotelDAO = daoFactory.getHotelDAO();
        hotelDAO.save(hotel);
    }

    @Override
    public void createRoom(Hotel hotel, Integer number, Integer maxPeople, BigDecimal cost) {
        var room = new Room();
        room.setMaxPeople(maxPeople);
        room.setCost(cost);
        hotel.getRooms().put(number, room);

        var hotelDAO = daoFactory.getHotelDAO();
        hotelDAO.save(hotel);
    }

    @Override
    public List<Hotel> getHotels() {
        var hotelDAO = daoFactory.getHotelDAO();
        return hotelDAO.getAll();
    }

    @Override
    public Optional<Hotel> getHotelById(ObjectId id) {
        var hotelDAO = daoFactory.getHotelDAO();
        return hotelDAO.get(id);
    }

    @Override
    public Map<Integer, Room> getSuitableRooms(RoomRequest request) {
        var hotelDAO = daoFactory.getHotelDAO();
        return hotelDAO.getSuitableRooms(request);
    }
}
