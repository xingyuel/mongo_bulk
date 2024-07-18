package com.grainger.Idea.repository;

import com.grainger.Idea.data.Person;
import com.mongodb.bulk.BulkWriteResult;

import java.util.Collection;

public interface PersonDao {
    BulkWriteResult bulkUpsert(Collection<Person> persons);
}
