package org.iesfm.newspaper.controllers;

import org.iesfm.newspaper.controllers.dto.ArticleDto;
import org.iesfm.newspaper.entity.Article;
import org.iesfm.newspaper.service.ArticleService;
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
    private ArticleService articleService;

    @GetMapping(path ="/articles")
    public ResponseEntity<List<ArticleDto>> list(
            @RequestParam(required = true, defaultValue = "1") int id,
            @RequestParam(value = "author", required = false) String author
    ) {
            return ResponseEntity.ok(
                    articleService.articleList(id, author)
                            .stream()
                            .map(article -> ArticleDto.toDto(article))
                            .collect(Collectors.toList()));
    }

    @GetMapping(path = "/articles/{articleId}")
    public ResponseEntity<ArticleDto> getArticle(
            @PathVariable("articleId") int articleId
    ){
        Article article = articleService.getArticle(articleId);
        if (article != null){
            return ResponseEntity.ok(ArticleDto.toDto(article));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping(path = "/articles")
    public ResponseEntity<Void> add(
            @Valid @RequestBody ArticleDto articleDto
    ){
        if (articleService.addArticle(ArticleDto.toEntity(articleDto))){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
