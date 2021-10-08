package ru.demo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NodeCreateDto {

    @NotNull(message = "name must not be null")
    @NotBlank(message = "name must not be blank")
    private String name;

}
