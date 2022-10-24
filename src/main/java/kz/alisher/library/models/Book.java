package kz.alisher.library.models;

public class Book {
    private int id;
    private String title;
    private String author;
    private int product_year;

    public Book() {
    }

    public Book(int id, String title, String author, int product_year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.product_year = product_year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getProduct_year() {
        return product_year;
    }

    public void setProduct_year(int product_year) {
        this.product_year = product_year;
    }
}
