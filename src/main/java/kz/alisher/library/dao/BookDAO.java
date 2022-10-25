package kz.alisher.library.dao;

import kz.alisher.library.models.Book;
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
}
