package lcy.takeoutddookddack.repository;

import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

abstract class AbstractRepository<T> {
    @Autowired
    MongoTemplate template;

    public abstract T saveNew(T t);

    public abstract T findById(String id);

    public abstract List<T> findAll();

    public abstract T update(String id, T t);

    public abstract DeleteResult deleteById(String id);

    public abstract void deleteAll();
}
