package org.iesfm.newspaper.controllers;

import org.iesfm.newspaper.controllers.dto.ArticleDto;
import org.iesfm.newspaper.controllers.dto.ArticleSectionDto;
import org.iesfm.newspaper.entity.Article;
import org.iesfm.newspaper.service.SectionService;
import org.iesfm.newspaper.service.exceptions.ArticleExistException;
import org.iesfm.newspaper.service.exceptions.ArticleNotFoundExceptions;
import org.iesfm.newspaper.service.exceptions.SectionNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ArticleController {

    @Autowired
    private SectionService sectionService;

    @GetMapping(path ="/articles")
    public ResponseEntity<List<ArticleDto>> list(
            @RequestParam(required = true, defaultValue = "1") int id,
            @RequestParam(value = "author", required = false) String author
    ) {
            return ResponseEntity.ok(
                    sectionService.articleList(id, author)
                            .stream()
                            .map(article -> ArticleDto.toDto(article))
                            .collect(Collectors.toList()));
    }

    @GetMapping(path = "/sections/{sectionId}/articles")
    public ResponseEntity<ArticleSectionDto> getSectionArticles(
           @RequestParam("sectionId") int id,
           @Valid @RequestBody ArticleSectionDto articleSectionDto
    ){
        try{
            return ResponseEntity.ok(
                    sectionService.getArticle(id)
                            .stream()
                            .map(ArticleSectionDto::toDto)
                            .collect(Collectors.toList())
            );
        } catch (ArticleNotFoundExceptions e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/sections/{sectionId}/articles/{author}")
    public ResponseEntity<ArticleSectionDto> getArticleByAuthor(

            @PathVariable("author") String author
    ){

    }

    @PostMapping(path = "/sections/{sectionId}/articles")
    public ResponseEntity<Void> add(
            @Valid @RequestBody ArticleDto articleDto
    ){
        try {
            sectionService.addArticle(ArticleDto.toEntity(articleDto));
            return ResponseEntity.ok().build();
        } catch (SectionNotFoundException e){
            return ResponseEntity.notFound().build();
        } catch (ArticleExistException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PutMapping(path = "/sections/{sectionId}/articles/{id}")
    public ResponseEntity<Void> updateArticle(
            @PathVariable("articleId") int id,
            @Valid @RequestBody ArticleDto article
    ){
        try {
            sectionService.updateArticle(id, ArticleDto.toEntity(article));
            return ResponseEntity.ok().build();
        } catch (SectionNotFoundException | ArticleNotFoundExceptions e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/sections/{sectionId}/articles/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable("articleId") int id
    ){
        try {
            sectionService.deleteArticle(id);
            return ResponseEntity.ok().build();
        } catch (SectionNotFoundException | ArticleNotFoundExceptions e){
            return ResponseEntity.notFound().build();
        }
    }

}
