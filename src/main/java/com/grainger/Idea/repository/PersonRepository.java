package com.grainger.Idea.repository;

import com.grainger.Idea.data.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import java.util.Date;

public interface PersonRepository extends PersonDao, MongoRepository<Person, Integer> {
    @Query("{ '_id' : ?0 }")
    Person findBySsn(Integer ssn);

    @Query("{ '_id' : { $in: ?0 } }")
    @Update("{ $set : { 'isDeleted' : true, 'timestamp' : ?1 } }")
    void updateSpecificFieldsByIds(Iterable<Integer> ids, Date date);
}
