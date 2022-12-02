package org.iesfm.newspaper.controllers;

import org.iesfm.newspaper.controllers.dto.SectionDto;
import org.iesfm.newspaper.entity.Section;
import org.iesfm.newspaper.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SectionController {

    @Autowired
    private SectionService sectionService;

    @GetMapping(path = "/sections")
    public ResponseEntity<List<SectionDto>> listSections(
    ){
        return ResponseEntity.ok(
                sectionService.sectionList()
                        .stream()
                        .map(SectionDto::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping(path = "/sections/{sectionId}")
    public ResponseEntity<List<SectionDto>> getSection(
            @PathVariable("sectionId") int id
    ){
        Section section = sectionService.getSection(id);
        if (section != null){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping(path = "/sections")
    public ResponseEntity<Void> addSection(
            @Valid @RequestBody SectionDto section
    ){
        if (sectionService.addSection(SectionDto.toEntity(section))){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(path = "sections/{sectionId}")
    public ResponseEntity<Void> updateSection(
            @PathVariable("sectionId") int id,
            @Valid @RequestBody SectionDto section
    ){
        if (sectionService.updateSection(id, SectionDto.toEntity(section))){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/sections/{sectionId}")
    public ResponseEntity<Void> deleteSection(
            @PathVariable("sectionId") int id
    ){
        if (sectionService.deleteSection(id)){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
