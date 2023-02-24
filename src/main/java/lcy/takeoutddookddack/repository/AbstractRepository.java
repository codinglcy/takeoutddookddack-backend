package lcy.takeoutddookddack.repository;

import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

abstract class AbstractRepository<T> {
    @Autowired
    MongoTemplate template;

    public abstract T saveNew(T t);

    public abstract T findById(Id id);

    public abstract List<T> findAll();

    public abstract T update(String E, T t);

    public abstract void deleteById(Id id);

    public abstract void deleteAll();
}
