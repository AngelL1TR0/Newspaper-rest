package org.iesfm.newspaper.service.exceptions;

public class ArticleExistException extends Exception{

    private final int id;
    private final String author;

    public ArticleExistException(int id, String author) {
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
