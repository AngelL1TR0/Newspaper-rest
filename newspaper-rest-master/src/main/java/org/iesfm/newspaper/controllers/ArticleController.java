package org.iesfm.newspaper.controllers;

import org.iesfm.newspaper.controllers.dto.ArticleDto;
import org.iesfm.newspaper.entity.Article;
import org.iesfm.newspaper.service.SectionService;
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
    public ResponseEntity<ArticleDto> getArticle(
            @PathVariable("articleId") int articleId
    ){
        Article article = sectionService.getArticle(articleId);
        if (article != null){
            return ResponseEntity.ok(ArticleDto.toDto(article));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping(path = "/sections/{sectionId}/articles")
    public ResponseEntity<Void> add(
            @Valid @RequestBody ArticleDto articleDto
    ){
        if (sectionService.addArticle(ArticleDto.toEntity(articleDto))){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PutMapping(path = "/sections/{sectionId}/articles/{id}")
    public ResponseEntity<Void> updateArticle(
            @PathVariable("articleId") int id,
            @Valid @RequestBody ArticleDto article
    ){
        if (sectionService.updateArticle(id, ArticleDto.toEntity(article))){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/sections/{sectionId}/articles/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable("articleId") int id
    ){
        if (sectionService.deleteArticle(id)){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
