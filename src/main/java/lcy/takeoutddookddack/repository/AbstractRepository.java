package lcy.takeoutddookddack.repository;

import jakarta.persistence.Id;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

abstract class AbstractRepository<T> {
    @Autowired
    MongoTemplate template;

    public abstract T saveNew(T t);

    public abstract T findById(ObjectId id);

    public abstract List<T> findAll();

    public abstract T update(String E, T t);

    public abstract void deleteById(ObjectId id);

    public abstract void deleteAll();
}
