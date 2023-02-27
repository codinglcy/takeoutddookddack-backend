package lcy.takeoutddookddack.repository;

import com.mongodb.client.result.DeleteResult;
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

    public abstract T update(ObjectId id, T t);

    public abstract DeleteResult deleteById(ObjectId id);

    public abstract void deleteAll();
}
