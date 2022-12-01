package org.iesfm.newspaper.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.iesfm.newspaper.entity.Article;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {

    @NotBlank
    @Positive
    private int id;
    @NotBlank
    private String title;
    @NotBlank
    private String text;
    @NotBlank
    private String author;
    @NotNull
    @Positive
    private int sectionId;

    public static ArticleDto toDto(Article article) {
        return new ArticleDto(
                article.getId(),
                article.getTitle(),
                article.getText(),
                article.getAuthor(),
                article.getSectionId()
        );
    }

    public static Article toEntity(ArticleDto article) {
        return new Article(
                article.getId(),
                article.getTitle(),
                article.getText(),
                article.getAuthor(),
                article.getSectionId()
        );
    }
}
