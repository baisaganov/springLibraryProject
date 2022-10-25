package kz.alisher.library.dao;

import kz.alisher.library.models.Book;
import kz.alisher.library.models.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {

        Book book = new Book();

        book.setId(resultSet.getInt("book_id"));
        book.setTitle(resultSet.getString("title"));
        book.setAuthor(resultSet.getString("author"));
        book.setProduct_year(resultSet.getInt("product_date"));

        return book;
    }
}
