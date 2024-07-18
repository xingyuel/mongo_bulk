package com.grainger.Idea.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.Objects;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "people")
@Getter
@Setter
@NoArgsConstructor
public class Person {
    @Id
    private Integer ssn;
    private Date timestamp;
    private String first;
    private String last;
    private String address;
    private String city;
    private String state = "IL";
    private Integer zipCode;
    private Long phoneNumber;
    private Integer age;
    private String sex;
    private String email;
    private Boolean isDeleted;

    @Override
    public String toString() {
        return "Person{" +
                "ssl=" + ssn +
                ", timestamp='" + timestamp + '\'' +
                ", first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode=" + zipCode +
                ", phoneNumber=" + phoneNumber +
                ", email=" + email +
                ", age=" + age +
                ", sex =" + sex  +
                ", isDeleted =" + isDeleted  +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;
        return ssn.equals(person.ssn) && first.equals(person.first) && last.equals(person.last) &&
                address.equals(person.address) && city.equals(person.city) && state.equals(person.state) &&
                zipCode.equals(person.zipCode) && phoneNumber.equals(person.phoneNumber) &&
                age.equals(person.age) && sex.equals(person.sex) && email.equals(person.email) &&
                isDeleted.equals(person.isDeleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ssn, first, last, address, city, state, zipCode, phoneNumber, age, sex, email, isDeleted);
    }
}
