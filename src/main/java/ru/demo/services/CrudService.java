package ru.demo.services;

import ru.demo.domain.dto.ClusterCreateDto;
import ru.demo.domain.dto.ClusterResponseDto;
import ru.demo.domain.dto.ClusterUpdateDto;
import ru.demo.domain.dto.NodeDTO;

import java.util.List;

public interface CrudService {
    void create(ClusterCreateDto cluster);

    void update(ClusterUpdateDto cluster, Long id);

    void delete(Long id);

    void addNodes(Long id, List<NodeDTO> nodeDTO);

    List<ClusterResponseDto> getClusters();

    List<NodeDTO> getNodes(Long id);
}
