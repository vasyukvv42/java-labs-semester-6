package edu.kpi.hotel.model.dao.morphia;

import dev.morphia.Datastore;
import edu.kpi.hotel.model.dao.api.HotelDAO;
import edu.kpi.hotel.model.entity.Hotel;
import edu.kpi.hotel.model.entity.Room;
import edu.kpi.hotel.model.entity.RoomRequest;

import java.util.Map;
import java.util.stream.Collectors;

public class HotelMorphiaDAO extends MorphiaDAO<Hotel> implements HotelDAO {

    public HotelMorphiaDAO(Datastore datastore) {
        super(datastore, Hotel.class);
    }

    @Override
    public Map<Integer, Room> getSuitableRooms(RoomRequest request) {
        var hotel = request.getHotel();
        var reservationDAO = new MorphiaDAOFactory().getReservationDAO();

        return hotel.getRooms().entrySet().stream()
                .filter((entry) -> {
                    var room = entry.getValue();
                    var roomNumber = entry.getKey();
                    var conflictingReservations = reservationDAO.getReservationsInRange(
                                    hotel,
                                    roomNumber,
                                    request.getReserveFrom(),
                                    request.getReserveTo()
                    );

                    return room.getCost().compareTo(request.getMaxCost()) <= 0
                            && room.getMaxPeople().compareTo(request.getPeople()) >= 0
                            && conflictingReservations.isEmpty();
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
