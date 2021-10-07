package ru.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.demo.domain.entity.Node;

public interface NodeJpaRepository extends JpaRepository<Node, Long> {
}
