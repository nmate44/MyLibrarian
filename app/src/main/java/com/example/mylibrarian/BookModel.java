package com.example.mylibrarian;

public class BookModel {
    private int id;
    private String title;
    private String author;
    private String genre;
    private String description;
    private String isbn;
    private float advancement;
    private String img;

    public BookModel() {
    }

    public BookModel(int id, String title, String author, String description, String genre, String isbn, float advancement, String img) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.description = description;
        this.isbn = isbn;
        this.advancement = advancement;
        this.img = img;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public float getAdvancement() {
        return advancement;
    }

    public void setAdvancement(Float advancement) {
        this.advancement = advancement;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
