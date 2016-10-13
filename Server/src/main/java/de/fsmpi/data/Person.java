package de.fsmpi.data;

import javax.persistence.*;

/**
 * Created by Marcel Fraas on 26.08.16.
 */

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long id;

    @Column(name = "Person")
    private String personName;

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public long getId() {
        return id;
    }

    public Person(String personName) {
        this.personName = personName;
    }

    public Person(){};      // why JPA


    @Override
    public String toString() {
        return "Person{" +
                "personName='" + personName + '\'' +
                '}';
    }
}
