package com.balita.module.person.service;

import com.balita.module.person.entity.Person;
import com.balita.module.person.repository.PersonRepository;
import java.util.List;

public class PersonService {
    private final PersonRepository personRepository;

    public PersonService() {
        this.personRepository = new PersonRepository();
    }

    public List<Person> getPersons(int nbPersons) {
        return personRepository.getPersons(nbPersons);
    }

    public String generateCsvContent(int nbPersons) {
        List<Person> persons = getPersons(nbPersons);
        StringBuilder sb = new StringBuilder();
        sb.append("First Name,Last Name,Birth Date,Email,Phone,Address,City,State,Zip,Country,Company\n");
        for (Person person : persons) {
            sb.append(person.getFirstName()).append(",")
                    .append(person.getLastName()).append(",")
                    .append(person.getBirthDate()).append(",")
                    .append(person.getEmail()).append(",")
                    .append(person.getPhone()).append(",")
                    .append(person.getAddress()).append(",")
                    .append(person.getCity()).append(",")
                    .append(person.getState()).append(",")
                    .append(person.getZip()).append(",")
                    .append(person.getCountry()).append(",")
                    .append(person.getCompany()).append("\n");
        }
        return sb.toString();
    }
}
