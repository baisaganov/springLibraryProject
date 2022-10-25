package kz.alisher.library.models;

public class Book {
    private int id;
    private int person_id;
    private String title;
    private String author;
    private int product_year;

    public Book() {
    }

    public Book(int id, int person_id, String title, String author, int product_year) {
        this.id = id;
        this.person_id = person_id;
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

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }
}
