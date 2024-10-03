package ru.schuk.springcourse.test;

import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.schuk.springcourse.dao.PersonDAO;
import ru.schuk.springcourse.models.Book;

import java.util.List;
import java.util.Objects;

public class main {
    public static void main(String[] args) {

        final Environment environment = null;
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://192.168.10.128:55432/project1");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);


        PersonDAO personDAO = new PersonDAO(jdbcTemplate);

//        List<Book> list = personDAO.findBookByPersonId(5);
//
//        for (Book i:list
//             ) {
//            System.out.println(i.getAuthor());
//            System.out.println(i.getYear());
//            System.out.println(i.getTitle());
//        }

    }
}
