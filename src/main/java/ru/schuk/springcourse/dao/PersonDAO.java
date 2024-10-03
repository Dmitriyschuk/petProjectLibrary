package ru.schuk.springcourse.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.schuk.springcourse.models.Book;
import ru.schuk.springcourse.models.Person;

import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }


    public Person show(String name){
        return jdbcTemplate.query("SELECT * FROM Person WHERE name =?", new Object[]{name}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }
    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE person_id =?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
//        Person person = null;
//        List<Person> list = jdbcTemplate.query("SELECT * FROM Person WHERE person_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class));
//        for (Person i : list
//        ) {
//            if (i.getPerson_id() == id) {
//                person = i;
//            }
//        }
//        return person;
    }

    public void save(Person person) {
        jdbcTemplate.update("insert into Person (name, year) values (?, ?)", person.getName(), person.getYear());
    }

    public void deletePerson(int id) {
        jdbcTemplate.update("delete from Person where person_id=?", id);
    }

    public void updatePerson(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET name = ?, year = ? WHERE person_id = ?", updatedPerson.getName(), updatedPerson.getYear(), id);
    }


    public List<Book> findBookByPersonId(int person_id) {
        List<Book> list = jdbcTemplate.query("select book_id, title, author, B.year from Person join Book B on Person.person_id = B.person_id where B.person_id=?", new Object[]{person_id}, new BeanPropertyRowMapper<>(Book.class));
        for (Book i : list
        ) {
            System.out.println(i);
        }
        return list;
    }

}
