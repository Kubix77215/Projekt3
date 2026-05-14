package service;

public class Book {
    public String title;
    public String author;
    public int year;
    public int value;

    public Book(String title, String author, int year, int value) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.value = value;
    }

    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public int getYear() {
        return year;
    }
    public int getValue() {
        return value;
    }
}
