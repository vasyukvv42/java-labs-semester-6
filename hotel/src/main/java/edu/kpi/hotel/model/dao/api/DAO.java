package edu.kpi.hotel.model.dao.api;

import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    Optional<T> get(ObjectId id);
    List<T> getAll();
    void save(T entity);
    void update(T entity);
    void delete(T entity);
}
