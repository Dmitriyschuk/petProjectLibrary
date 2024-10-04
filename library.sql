--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;



SET default_tablespace = '';

SET default_with_oids = false;


---
--- drop tables
---


DROP TABLE IF EXISTS Person;
DROP TABLE IF EXISTS Book;




CREATE TABLE Person (
                        person_id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                        name varchar(100) NOT NULL UNIQUE,
                        year int NOT NULL
);

CREATE TABLE Book (
                      book_id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                      title varchar(100) NOT NULL,
                      author varchar(100) NOT NULL,
                      year int NOT NULL,
                      person_id int REFERENCES Person(person_id) ON DELETE SET NULL
);




insert into Person (name, year) values ('Антон Николаевич', 1950);
insert into Person (name, year) values ('Михаил Сергееви', 1989);
insert into Person (name, year) values ('Антон Ярославович', 1999);
insert into Person (name, year) values ('Дмитрий Валерьевич', 1986);
insert into Person (name, year) values ('Василий Сергеевич', 1934);	
insert into Person (name, year) values ('Виталий Владимирович', 1978);
insert into Person (name, year) values ('Александ Сергеевич', 1901);		



insert into Book(person_id,title, author, year) values (null,'Мастер и Маргарита', 'Михаил Булгаков',1953);
insert into Book(person_id,title, author, year) values (null,'Вокруг света за восемьдесят дней', 'Жюль Верн',1872);
insert into Book(person_id,title, author, year) values (null,'451 по фаренгейту', 'Рэй Бредбери',1953);
insert into Book(person_id,title, author, year) values (null, 'Воровка книг','Маркус Зусак', 2005);



