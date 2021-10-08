package ru.demo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClusterUpdateDto {

    @NotNull(message = "id must not be null")
    @Min(1)
    private Long id;

    @NotNull(message = "description must not be null")
    @NotBlank(message = "description must not be blank")
    private String description;

    private List<NodeDto> nodes = new ArrayList<>();
}
