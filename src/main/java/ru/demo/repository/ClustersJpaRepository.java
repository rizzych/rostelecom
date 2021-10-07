package ru.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.demo.domain.entity.Cluster;

@Repository
public interface ClustersJpaRepository extends JpaRepository<Cluster, Long> {

}
