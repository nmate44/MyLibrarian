package com.example.mylibrarian;

public class BookModel {
    private String id;
    private String title;
    private String author;
    private String published;
    private String genre;
    private String description;
    private Integer pages;
    private Integer pagesRead = 0;
    private String isbn;
    private String img;

    public BookModel() {
    }

    public BookModel(String id, String title, String author, String published, String genre, String description, String isbn, Integer pages, Integer pagesRead, String img) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.published = published;
        this.description = description;
        this.isbn = isbn;
        this.pages = pages;
        this.pagesRead = pagesRead;
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
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

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getPagesRead() {
        return pagesRead;
    }

    public void setPagesRead(Integer pagesRead) {
        this.pagesRead = pagesRead;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer calculateCompletion() {
        float result = ((float)this.pagesRead/(float)this.pages)*100;
        return Math.round(result);
    }
}
