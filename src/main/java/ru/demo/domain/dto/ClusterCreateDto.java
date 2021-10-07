package ru.demo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClusterCreateDto {

    @NotNull(message = "description must not be null")
    @NotBlank(message = "description must not be blank")
    private String description;

    private List<NodeDTO> node = new ArrayList<>();

}
