package ru.schuk.springcourse.models;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


//Не используется, оставлен для примера
public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        Book book = new Book();

        book.setBook_id(resultSet.getInt("book_id"));
        book.setBook_id(resultSet.getInt("author_id"));
        book.setTitle(resultSet.getString("title"));
        book.setAuthor(resultSet.getString("author"));
        book.setBook_id(resultSet.getInt("year"));

        return book;
    }
}
