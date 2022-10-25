package kz.alisher.library.dao;

import kz.alisher.library.models.Book;
import kz.alisher.library.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index(){
        return jdbcTemplate.query("SELECT * FROM book", new BookMapper());
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO book(title, author, product_date) VALUES (?,?,?)",
                book.getTitle(),
                book.getAuthor(),
                book.getProduct_year());
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM BOOK WHERE book_id=?",
                new Object[]{id}, new BookMapper()).stream().findAny().orElse(null);
    }

    public void update(int id, Book book) {
        jdbcTemplate.update("UPDATE book SET title=?, author=?, product_date=? WHERE book_id=?",
                book.getTitle(),
                book.getAuthor(),
                book.getProduct_year(),
                id);
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM book WHERE book_id=?", id);
    }

    public void removePersonBook(int book_id) {
        jdbcTemplate.update("UPDATE book SET person_id = NULL where book_id=?",
                book_id);
    }

    public Person getPerson(int id) {
        return jdbcTemplate.query("select person.person_id," +
                        "person.fio, " +
                        "person.year_of_birth from person " +
                        "JOIN book b on person.person_id = b.person_id " +
                        "where b.book_id=?",
                new Object[]{id},
                new PersonMapper()).stream().findAny().orElse(null);
    }

    public List<Person> getFreePeople() {
        return jdbcTemplate.query("select person.person_id," +
                "person.fio, " +
                "person.year_of_birth from person " +
                "JOIN book b on person.person_id = b.person_id ",
                new PersonMapper());
    }

    public void addUser2Book(int book_id, int person_id) {
        jdbcTemplate.update("UPDATE book SET person_id=? WHERE book_id=?",
                person_id,
                book_id);
    }
}
