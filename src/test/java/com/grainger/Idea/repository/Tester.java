package com.grainger.Idea.repository;

import com.grainger.Idea.data.Creator;
import com.grainger.Idea.data.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Slf4j
public class Tester {
    private static final int SIZE = 10000; // for local
    private PersonRepository personRepository;

    private Creator creator;

    private List<Person> persons;


    private Long lastModified;
    private Long startTime;
    private Boolean isDeleted = false;
    @Autowired
    Tester(PersonRepository personRepository, Creator creator) {
        this.personRepository = personRepository;
        this.creator = creator;
    }

    @BeforeEach
    void init() {
        persons = creator.getPeople(SIZE);

        Person person = personRepository.findBySsn(persons.get(0).getSsn());
        if (person != null)
            isDeleted = !person.getIsDeleted();

        lastModified = System.currentTimeMillis();
        persons.forEach(p -> {
            p.setIsDeleted(isDeleted);
            p.setTimestamp(new Date(lastModified));
        });

        startTime = System.currentTimeMillis();
    }

    @AfterEach
    void verify() {
        long timeSpan = System.currentTimeMillis() - startTime;
        Person person = personRepository.findBySsn(persons.get(0).getSsn());
        assertEquals(isDeleted, person.getIsDeleted());
        assertEquals(SIZE, personRepository.count());
        assertEquals(lastModified, person.getTimestamp().getTime());

        log.info("\n\ntotal time used: {}\n\n", timeSpan);
    }
    @Test   /* If necessary, debug this method to see it will loop through the List. */
    void testSaveAll() {
        personRepository.saveAll(persons);
    }

    @Test
    void testOneByOne() {
        persons.forEach(product -> personRepository.save(product));
    }

    @Test
    void testBulkUpsert() {
        personRepository.bulkUpsert(persons);
    }

    @Test
    void testUpdateSpecificFieldsByIds() {
        List<Integer> ssnNumbers = new ArrayList<>();
        persons.forEach(person -> ssnNumbers.add(person.getSsn()));
        personRepository.updateSpecificFieldsByIds(ssnNumbers, new Date(lastModified));

        // Because updateSpecificFieldsByIds() unconditionally sets `isDeleted` to true, without this line verify() may fail when this test is run.
        isDeleted = true;
    }
}
