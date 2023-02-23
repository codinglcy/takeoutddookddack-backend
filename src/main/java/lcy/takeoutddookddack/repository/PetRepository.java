package lcy.takeoutddookddack.repository;

import lcy.takeoutddookddack.domain.PetEntity;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class PetRepository {
    @Autowired
    MongoTemplate mongoTemplate;

    public void newPet(PetEntity pet){
        mongoTemplate.save(pet);
    }

    public PetEntity findById(ObjectId id){
        PetEntity findPet = mongoTemplate.findById(id, PetEntity.class);
        return findPet;
    }

    public void updateWithId(ObjectId id, String kind, String name, int age){
        PetEntity findPet = mongoTemplate.findById(id, PetEntity.class);

        Query query = new Query();
        Update update = new Update();

        query.addCriteria(Criteria.where("_id").is(findPet.getId()));
        update.set("kind", kind);
        update.set("name", name);
        update.set("age", age);

        mongoTemplate.updateFirst(query, update, PetEntity.class);
    }

    public void removeAll(){
        mongoTemplate.remove(new Query(),PetEntity.class);
    }
}
