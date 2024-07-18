package com.grainger.Idea.data;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@Service
public class Creator {
    private static Random random = new Random();
    private IntStream ssnStream = random.ints(123456789, 999999999);
    private Iterator<Integer> ssnIter = ssnStream.iterator();
    private IntStream zipCodeStream = random.ints(60001, 69999);
    private Iterator<Integer> zipCodeIter = zipCodeStream.iterator();
    private LongStream phoneStream = random.longs(6300001234L, 6309999999L);
    private Iterator<Long> phoneIter = phoneStream.iterator();
    private IntStream ageStream = random.ints(16, 85);
    private Iterator<Integer> ageIter = ageStream.iterator();
    private IntStream addresStream = random.ints(1, 9985);
    private Iterator<Integer> addressIter = addresStream.iterator();

    private static final String[] LADY_FIRST = new String[] {
            "Olivia", "Emma", "Charlotte", "Amelia", "Ava", "Sophia", "Isabella", "Mia", "Evelyn", "Harper", "Luna", "Camila", "Gianna", "Elizabeth",
            "Lily", "Nova", "Isla", "Grace", "Violet", "Aurora", "Riley", "Zoey", "Willow", "Emilia", "Stella", "Zoe", "Victoria", "Hannah", "Addison",
            "Leah", "Lucy", "Eliana", "Ivy", "Everly", "Lillian", "Paisley", "Elena", "Naomi", "Maya", "Natalie", "Kinsley", "Delilah", "Claire",
            "Audrey", "Aaliyah", "Ruby", "Brooklyn", "Alice", "Aubrey", "Autumn", "Leilani", "Savannah", "Valentina", "Kennedy", "Madelyn",
            "Josephine", "Bella", "Skylar", "Genesis", "Sophie", "Hailey", "Sadie", "Natalia", "Quinn", "Caroline", "Allison", "Gabriella", "Anna",
            "Serenity", "Nevaeh", "Cora", "Ariana", "Emery", "Lydia", "Jade", "Sarah", "Eva", "Adeline", "Madeline", "Piper", "Rylee", "Athena",
            "Peyton", "Everleigh", "Vivian", "Clara", "Raelynn", "Liliana", "Samantha", "Maria", "Iris", "Ayla", "Eloise", "Lyla", "Eliza", "Hadley",
            "Melody", "Julia", "Parker", "Rose", "Isabelle", "Brielle", "Adalynn", "Arya", "Eden", "Remi", "Mackenzie", "Maeve", "Margaret", "Reagan",
            "Charlie", "Alaia", "Melanie", "Josie", "Elliana", "Cecilia", "Mary", "Daisy", "Alina", "Lucia", "Ximena", "Juniper", "Kaylee", "Magnolia",
            "Summer", "Adalyn", "Sloane", "Amara", "Arianna", "Isabel", "Reese", "Emersyn", "Sienna", "Kehlani", "River", "Freya", "Valerie", "Blakely",
            "Genevieve", "Esther", "Valeria", "Katherine", "Kylie", "Norah", "Amaya", "Bailey", "Ember", "Ryleigh", "Georgia", "Catalina", "Emerson",
            "Alexandra", "Faith", "Jasmine", "Ariella", "Ashley", "Andrea", "Millie", "June", "Khloe", "Callie", "Juliette", "Sage", "Ada", "Anastasia",
            "Olive", "Alani", "Brianna", "Rosalie", "Molly", "Brynlee", "Amy", "Ruth"};
    private static final String[] GUY_FIRST = new String[] {
            "Liam", "Noah", "Oliver", "Elijah", "James", "William", "Benjamin", "Lucas", "Henry", "Theodore", "Jack", "Levi", "Alexander",
            "Jackson", "Mateo", "Daniel", "Michael", "Mason", "Sebastian", "Ethan", "Logan", "Owen", "Samuel", "Jacob", "Asher", "Aiden",
            "John", "Joseph", "Wyatt", "David", "Leo", "Luke", "Julian", "Hudson", "Grayson", "Matthew", "Ezra", "Gabriel", "Carter", "Isaac",
            "Jayden", "Luca", "Anthony", "Dylan", "Lincoln", "Thomas", "Maverick", "Elias", "Josiah", "Charles", "Caleb", "Christopher",
            "Ezekiel", "Miles", "Jaxon", "Isaiah", "Andrew", "Joshua", "Nathan", "Nolan", "Adrian", "Cameron", "Santiago", "Eli", "Aaron",
            "Ryan", "Angel", "Cooper", "Waylon", "Easton", "Kai", "Christian", "Landon", "Colton", "Roman", "Axel", "Brooks", "Jonathan",
            "Robert", "Jameson", "Ian", "Everett", "Greyson", "Wesley", "Jeremiah", "Hunter", "Leonardo", "Jordan", "Jose", "Bennett",
            "Silas", "Nicholas", "Parker", "Beau", "Weston", "Austin", "Connor", "Carson", "Dominic",
            "Xavier" };
    private static final String[] LAST_NAMES = new String[] {
            "Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis", "Rodriguez", "Martinez", "Hernandez", "Lopez",
            "Gonzales", "Wilson", "Anderson", "Thomas", "Taylor", "Moore", "Jackson", "Martin", "Lee", "Perez", "Thompson", "White", "Harris",
            "Sanchez", "Clark", "Ramirez", "Lewis", "Robinson", "Walker", "Young", "Allen", "King", "Wright", "Scott", "Torres", "Nguyen",
            "Hill", "Flores", "Green", "Adams", "Nelson", "Baker", "Hall", "Rivera", "Campbell", "Mitchell", "Carter", "Roberts", "Gomez",
            "Phillips", "Evans", "Turner", "Diaz", "Parker", "Cruz", "Edwards", "Collins", "Reyes", "Stewart", "Morris", "Morales", "Murphy",
            "Cook", "Rogers", "Gutierrez", "Ortiz", "Morgan", "Cooper", "Peterson", "Bailey", "Reed", "Kelly", "Howard", "Ramos", "Kim",
            "Cox", "Ward", "Richardson", "Watson", "Brooks", "Chavez", "Wood", "James", "Bennet", "Gray", "Mendoza", "Ruiz", "Hughes",
            "Price", "Alvarez", "Castillo", "Sanders", "Patel", "Myers", "Long", "Ross", "Foster", "Jimenez" };
    private static final String[] CITIES = new String[] {
            "Clinton", "Franklin ", "Washington", "Madison ", "Arlington", "Centerville", "Lebanon", "Georgetown", "Springfield",
            "Chester", "Fairview", "Greenville", "Bristol", "Dayton", "Dover", "Salem", "Winchester", "Oakland", "Milton",
            "Newport", "Ashland", "Bloomington", "Riverside", "Manchester", "Oxford", "Burlington"
    };

    private int ladyIndex, guyIndex, lastIndex, cityIndex;

    public List<Person> getPeople(int size) {
        random.setSeed(7L);
        cityIndex = 0;
        guyIndex = 0;
        ladyIndex = 0;
        List<Person> people = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            Person person = getNextPerson();
            people.add(person);
        }

        return people;
    }

    private Person getNextPerson() {
        Person person;
        if (random.nextBoolean())
            person = getNextGuy();
        else
            person = getNextLady();

        person.setSsn(ssnIter.next());
        if (lastIndex >= LAST_NAMES.length)
            lastIndex = 0;
        person.setTimestamp(new Date(System.currentTimeMillis()));
        person.setLast(LAST_NAMES[lastIndex++]);
        person.setAddress(getAddress());
        if (cityIndex >= CITIES.length)
            cityIndex = 0;
        person.setCity(CITIES[cityIndex++]);
        person.setZipCode(zipCodeIter.next());
        person.setPhoneNumber(phoneIter.next());
        person.setAge(ageIter.next());
        person.setEmail(person.getFirst() + "." + person.getLast() + "@grainger.com");
        person.setIsDeleted(Boolean.FALSE);

        return person;
    }

    private String getAddress() {
        return "" + addressIter.next() + " main street";
    }

    private Person getNextGuy() {
        Person person = new Person();
        if (guyIndex >= GUY_FIRST.length)
            guyIndex = 0;
        person.setSex("M");
        person.setFirst(GUY_FIRST[guyIndex++]);

        return person;
    }

    private Person getNextLady() {
        Person person = new Person();
        if (ladyIndex >= LADY_FIRST.length)
            ladyIndex = 0;
        person.setSex("F");
        person.setFirst(LADY_FIRST[ladyIndex++]);

        return person;
    }
}