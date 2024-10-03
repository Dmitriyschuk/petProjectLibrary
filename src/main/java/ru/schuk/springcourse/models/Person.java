package ru.schuk.springcourse.models;


import ru.schuk.springcourse.util.PersonValidator;

import javax.validation.constraints.*;

/**
 * @author Schuk
 */
public class Person {
    private int person_id;


    //hibernate vallidator
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 100, message = "Name should be between 2 and 100 characters")
    private String name;

    @Min(value = 1900, message = "Year of birth should be greater than 1900")
    private int year;


    public Person( String name, int year) {
        this.name = name;
        this.year = year;
    }

    public Person() {

    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(int year) {this.year = year;}

    public int getYear() {return year;}


    @Override
    public String toString() {
        return "Person{" +
                "person_id=" + person_id +
                ", name='" + name + '\'' +
                ", year=" + year +
                '}';
    }
}
