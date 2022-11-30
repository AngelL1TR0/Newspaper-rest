package org.iesfm.newspaper.service.exceptions;

public class ArticleNotFoundExceptions extends Exception {
    private final int id;
    private String author;

    public ArticleNotFoundExceptions(int id, String author) {
        this.id = id;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }
}
