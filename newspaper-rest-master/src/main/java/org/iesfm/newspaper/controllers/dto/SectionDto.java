package org.iesfm.newspaper.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SectionDto {
    @NotBlank
    @Positive
    private int id;

    @NotBlank
    private String name;


}
