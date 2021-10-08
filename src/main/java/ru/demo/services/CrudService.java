package ru.demo.services;

import ru.demo.domain.dto.*;

import java.util.List;

public interface CrudService {
    void create(ClusterCreateDto cluster);

    void update(ClusterUpdateDto cluster, Long id);

    void delete(Long id);

    void addNode(Long id, NodeCreateDto nodeCreateDto);

    List<ClusterResponseDto> getClusters();

    List<NodeDto> getNodes(Long id);
}
