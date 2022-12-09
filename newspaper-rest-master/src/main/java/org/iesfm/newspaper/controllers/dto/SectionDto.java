package org.iesfm.newspaper.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.iesfm.newspaper.entity.Section;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SectionDto {
    @NotNull
    @Positive
    private int id;

    @NotBlank
    private String name;

    public static SectionDto toDto(Section section){
        return new SectionDto(
                section.getId(),
                section.getName()
        );
    }
    public static Section toEntity(SectionDto section){
        return new Section(
                section.getId(),
                section.getName()
        );
    }


}
