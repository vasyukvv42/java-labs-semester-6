package edu.kpi.hotel.model.dao.morphia;

import dev.morphia.Datastore;
import edu.kpi.hotel.model.dao.api.UserDAO;
import edu.kpi.hotel.model.entity.User;

import java.util.Optional;

public class UserMorphiaDAO extends MorphiaDAO<User> implements UserDAO {

    public UserMorphiaDAO(Datastore datastore) {
        super(datastore, User.class);
    }

    @Override
    public Optional<User> getByUsernameOrEmail(String usernameOrEmail) {
        var query = datastore.createQuery(entityClass);
        query.or(
                query.criteria("username").equal(usernameOrEmail),
                query.criteria("email").equal(usernameOrEmail)
        );

        return Optional.ofNullable(query.find().tryNext());
    }
}
