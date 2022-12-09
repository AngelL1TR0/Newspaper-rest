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
public class ArticleSectionDto {
    @NotNull
    @Positive
    private int id;
    @NotBlank
    private String title;
    @NotBlank
    private String text;
    @NotBlank
    private String author;

    public static ArticleSectionDto toDto(Article article) {
        return new ArticleSectionDto(
                article.getId(),
                article.getTitle(),
                article.getText(),
                article.getAuthor()
        );
    }

    public static Article toEntity(ArticleSectionDto dto, int sectionId) {
        return new Article(
                dto.getId(),
                dto.getTitle(),
                dto.getText(),
                dto.getAuthor(),
                sectionId
        );
    }
}

