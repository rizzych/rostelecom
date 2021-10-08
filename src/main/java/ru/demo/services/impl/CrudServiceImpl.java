package ru.demo.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.demo.domain.dto.*;
import ru.demo.domain.entity.Cluster;
import ru.demo.domain.entity.Node;
import ru.demo.repository.ClustersJpaRepository;
import ru.demo.repository.NodeJpaRepository;
import ru.demo.services.CrudService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CrudServiceImpl implements CrudService {

    private Logger logger = LoggerFactory.getLogger(CrudServiceImpl.class);
    private final ClustersJpaRepository clustersJpaRepository;
    private final NodeJpaRepository nodeJpaRepository;

    public CrudServiceImpl(ClustersJpaRepository clustersJpaRepository, NodeJpaRepository nodeJpaRepository) {
        this.clustersJpaRepository = clustersJpaRepository;
        this.nodeJpaRepository = nodeJpaRepository;
    }

    @Override
    @Validated
    public void create(ClusterCreateDto cluster) {
        Cluster dbCluster = new Cluster();
        dbCluster.setDescription(cluster.getDescription());
        if (!cluster.getNodes().isEmpty()) {
            List<Node> nodeList = cluster.getNodes().stream().map(x -> Node.builder()
                    .name(x.getName())
                    .cluster(dbCluster)
                    .build()
            ).collect(Collectors.toList());
            dbCluster.setNodes(nodeList);
        }
        clustersJpaRepository.save(dbCluster);
    }

    @Override
    public void update(ClusterUpdateDto cluster, Long id) {
        Optional<Cluster> clusterById = clustersJpaRepository.findById(id);
        if (clusterById.isPresent()) {
            Cluster dbCluster = clusterById.get();
            dbCluster.setDescription(cluster.getDescription());
            if (!dbCluster.getNodes().isEmpty()) {
                Map<Long, Node> map = dbCluster.getNodes()
                        .stream()
                        .collect(Collectors.toMap(Node::getId, node -> node));
                cluster.getNodes().forEach(dtoNode -> {
                    Node node = map.get(dtoNode.getId());
                    node.setName(dtoNode.getName());
                });
                clustersJpaRepository.save(dbCluster);
            }
        } else {
            throw new RuntimeException(id + "- этого id нет в базе");
        }
    }

    @Override
    public void delete(Long id) {
        clustersJpaRepository.deleteById(id);
    }

    @Override
    public void addNode(Long id, NodeCreateDto nodeCreateDto) {
        Optional<Cluster> clusterByIdOptional = clustersJpaRepository.findById(id);
        if (clusterByIdOptional.isPresent()) {
            Cluster cluster = clusterByIdOptional.get();
            if (cluster.getNodes().isEmpty()) {
                Node node = new Node();
                node.setName(nodeCreateDto.getName());
                node.setCluster(cluster);
                nodeJpaRepository.save(node);
            } else {
                throw new RuntimeException(id + "- у этого id есть nodes");
            }
        }
    }

    @Override
    public List<ClusterResponseDto> getClusters() {
        List<Cluster> clusters = clustersJpaRepository.findAll();
        if (!clusters.isEmpty()) {
            return clusters.stream().map(x -> ClusterResponseDto.builder()
                    .id(x.getId())
                    .description(x.getDescription())
                    .build()).collect(Collectors.toList());
        } else {
            throw new RuntimeException("В базе данных нет ни одного cluster");
        }

    }

    @Override
    public List<NodeDto> getNodes(Long id) {
        Optional<Cluster> clustersById = clustersJpaRepository.findById(id);
        if (clustersById.isPresent()) {
            List<Node> node = clustersById.get().getNodes();
            if (!node.isEmpty()) {
                return node.stream().map(x -> NodeDto.builder()
                        .id(x.getId())
                        .name(x.getName())
                        .build()
                ).collect(Collectors.toList());
            } else {
                throw new RuntimeException(id + " - у этого cluster нет ни одной node");
            }
        } else {
            throw new RuntimeException(id + " - этого id нет в базе/не существует node по этому id");
        }
    }
}
