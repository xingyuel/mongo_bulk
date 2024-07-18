package com.grainger.Idea.data;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.IntStream;

import static com.mongodb.assertions.Assertions.fail;

@SpringBootTest
@Slf4j
public class CreatorTest {
    private static final int SIZE = 10000;
    private Creator creator;

    @Autowired
    public CreatorTest(Creator creator) {
        this.creator = creator;
    }

    @Test
    void verifyPeopleGeneratedAtDifferentTimeAreSame() {
        List<Person> people1 =  creator.getPeople(SIZE);
        List<Person> people2 =  creator.getPeople(SIZE);

        IntStream.range(0, SIZE).forEach(i -> {
            if (i < 10)
                log.info("i = ", i);

            Person person1 = people1.get(i);
            Person person2 = people2.get(i);
            if (!person1.equals(person2)) {
                log.info("person1:\n", person1.toString());
                log.info("person2:\n", person2.toString());
                fail();
            }

            person1.setIsDeleted(Boolean.TRUE);
            if (person1.equals(person2)) {
                log.info("person1:\n", person1.toString());
                log.info("person2:\n", person2.toString());
                fail();
            }
        });
    }
}
