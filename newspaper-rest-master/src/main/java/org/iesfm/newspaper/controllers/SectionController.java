package org.iesfm.newspaper.controllers;

import org.iesfm.newspaper.controllers.dto.SectionDto;
import org.iesfm.newspaper.service.ArticleService;
import org.iesfm.newspaper.service.exceptions.ArticleNotFoundExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SectionController {

    @Autowired
    private ArticleService articleService;

    @GetMapping(path = "/articles/{articleId}/sections")
    public ResponseEntity<List<SectionDto>> listSections(
    ){
        return ResponseEntity.ok(
                articleService.sectionList()
                        .stream()
                        .map(SectionDto::toDto)
                        .collect(Collectors.toList())
        );
    }
}
