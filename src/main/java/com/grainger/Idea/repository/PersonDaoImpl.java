package com.grainger.Idea.repository;

import com.grainger.Idea.data.Person;
import com.mongodb.bulk.BulkWriteResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.FindAndReplaceOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
@Slf4j
public class PersonDaoImpl implements PersonDao {
    public static final String ID = "_id";  // in Person.java this is `ssn`

    private final MongoOperations mongoOperations;

    @Autowired
    public PersonDaoImpl(MongoOperations mongoTemplate) {
        this.mongoOperations = mongoTemplate;
    }

    @Override
    @Retryable(retryFor = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 1000, maxDelay = 5000, multiplier = 2))
    public BulkWriteResult bulkUpsert(Collection<Person> persons) {
        if (persons == null || persons.isEmpty())
            return BulkWriteResult.acknowledged(0, 0, 0, 0, List.of(), List.of());

        BulkOperations bulkOperations = mongoOperations.bulkOps(BulkOperations.BulkMode.UNORDERED, Person.class);
        persons.forEach(person -> {
            Query query = new Query().addCriteria(Criteria.where(ID).is(person.getSsn()));
            bulkOperations.replaceOne(query, person, FindAndReplaceOptions.options().upsert());
        });

        return bulkOperations.execute();
    }
}