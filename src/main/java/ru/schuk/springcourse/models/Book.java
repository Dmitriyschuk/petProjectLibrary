package ru.schuk.springcourse.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


public class Book {
    private int book_id;

    @NotEmpty(message = "Enter name")
    @Size(min = 2, max=200)
    private String title;
    @NotEmpty(message = "Enter author name")
    @Size(min = 2, max=100)
    private String author;
    @NotEmpty(message = "Enter author year")
    @Size(min = 100, max = 2200)
    private int year;


    public Book( String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public Book() {

    }


    public String getTitle() {return title;}

    public int getBook_id() {
        return book_id;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }



    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }


    @Override
    public String toString() {
        return book_id + ", " + title + ", " + author + ", " + year + ", " ;
    }
}
