package kz.alisher.library.dao;

import kz.alisher.library.models.Book;
import kz.alisher.library.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;

import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Person> index() {
        return jdbcTemplate.query("SELECT person_id, fio, year_of_birth  FROM person",
                new PersonMapper());
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM person WHERE person_id=?",
                new Object[]{id},
                new PersonMapper()).stream().findAny().orElse(null);
    }
    public Person show(String name) {
        return jdbcTemplate.query("SELECT * FROM person WHERE fio=?",
                new Object[]{name},
                new PersonMapper()).stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person(fio, year_of_birth) VALUES (?,?)",
                person.getFio(),
                person.getYearOfBirth());
    }

    public void update(int id, Person person) {
        jdbcTemplate.update("UPDATE person SET fio=?, year_of_birth=? WHERE person_id=?",
                person.getFio(),
                person.getYearOfBirth(),
                id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE person_id=?", id);
    }

    public List<Book> getTakenBooks(int id) {
        return jdbcTemplate.query("SELECT * from book WHERE person_id=?",
                new Object[]{id},
                new BookMapper());
    }


    public List<Book> getFreeBooks() {
        return jdbcTemplate.query("SELECT * from book WHERE person_id IS NULL",
                new BookMapper());
    }


    public void takeBook(int personId, int bookId) {
        jdbcTemplate.update("UPDATE book SET person_id=? WHERE book_id=?",
                personId,
                bookId);
    }

}
