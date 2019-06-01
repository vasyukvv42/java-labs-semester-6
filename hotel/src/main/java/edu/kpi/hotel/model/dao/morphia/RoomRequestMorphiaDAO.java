package edu.kpi.hotel.model.dao.morphia;

import dev.morphia.Datastore;
import edu.kpi.hotel.model.dao.api.RoomRequestDAO;
import edu.kpi.hotel.model.entity.RoomRequest;
import edu.kpi.hotel.model.entity.RoomRequestStatus;
import edu.kpi.hotel.model.entity.User;

import java.util.List;

public class RoomRequestMorphiaDAO extends MorphiaDAO<RoomRequest> implements RoomRequestDAO {

    public RoomRequestMorphiaDAO(Datastore datastore) {
        super(datastore, RoomRequest.class);
    }

    @Override
    public List<RoomRequest> getPendingRoomRequests() {
        var query = datastore.createQuery(entityClass)
                .filter("status", RoomRequestStatus.PENDING);
        return query.find().toList();
    }

    @Override
    public List<RoomRequest> getRoomRequestsByUser(User user) {
        var query = datastore.createQuery(entityClass)
                .filter("user", user);
        return query.find().toList();
    }
}
