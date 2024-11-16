package com.balita.module.person.repository;

import com.balita.module.person.entity.Person;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class PersonRepository {

    private final Faker faker = new Faker();

    public List<Person> getPersons(int nbPersons) {
        List<Person> persons = new ArrayList<>();

        for (int i = 0; i < nbPersons; i++) {
            persons.add(Person.builder()
                    .firstName(faker.name().firstName())
                    .lastName(faker.name().lastName())
                    .birthDate(faker.date().birthday())
                    .email(faker.internet().emailAddress())
                    .phone(faker.phoneNumber().phoneNumber())
                    .address(faker.address().streetAddress())
                    .city(faker.address().city())
                    .state(faker.address().state())
                    .zip(faker.address().zipCode())
                    .country(faker.address().country())
                    .company(faker.company().name())
                    .build());
        }

        return persons;
    }
}