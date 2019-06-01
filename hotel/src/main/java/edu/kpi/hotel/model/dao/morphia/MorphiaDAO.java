package edu.kpi.hotel.model.dao.morphia;

import dev.morphia.Datastore;
import edu.kpi.hotel.model.dao.api.DAO;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public abstract class MorphiaDAO<T> implements DAO<T> {

    Datastore datastore;
    Class<T> entityClass;

    public MorphiaDAO(Datastore datastore, Class<T> entityClass) {
        this.datastore = datastore;
        this.entityClass = entityClass;
    }

    @Override
    public Optional<T> get(ObjectId id) {
        var query = datastore.createQuery(entityClass)
                .filter("_id", id);
        return Optional.ofNullable(query.find().tryNext());
    }

    @Override
    public List<T> getAll() {
        var query = datastore.createQuery(entityClass);
        return query.find().toList();
    }

    @Override
    public void save(T entity) {
        datastore.save(entity);
    }

    @Override
    public void update(T entity) {
        datastore.save(entity);
    }

    @Override
    public void delete(T entity) {
        datastore.delete(entity);
    }
}
