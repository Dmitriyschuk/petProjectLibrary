package ru.schuk.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.schuk.springcourse.models.Book;
import ru.schuk.springcourse.models.Person;


import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Book> index() {
        return jdbcTemplate.query("select book_id, title, author,year from book", new BeanPropertyRowMapper<>(Book.class));
    }


    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE book_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public void deleteBook(int id) {
        jdbcTemplate.update("delete from Book where book_id=?", id);
    }

    public void save(Book book) {
        jdbcTemplate.update("insert into Book(title, author, year) values (?,?,?)", book.getTitle(), book.getAuthor(), book.getYear() );
    }

    public void updateBook(Book book, int id) {
        jdbcTemplate.update("UPDATE book SET title = ?, author = ?, year=?  WHERE book_id = ?", book.getTitle(), book.getAuthor(), book.getYear(), id);
    }

    public Person findPersonByBookId(int book_id) {
        Person person = jdbcTemplate.query("select name, person.year, B.person_id from Person join Book B on Person.person_id = B.person_id where B.book_id=?", new Object[]{book_id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
        return person;
    }

    public void returnBook ( int book_id){
        jdbcTemplate.update("update book set person_id = null where book_id=?", book_id);
    }

    public void reserveBook(int id, int person_id) {
        jdbcTemplate.update("update book set person_id=? where book_id=?", person_id, id);
    }
}
