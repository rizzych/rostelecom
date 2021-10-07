package ru.demo.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Table(name = "node")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Node {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, updatable = false)
    private Long id;

    private String name;

    @ManyToOne
    private Cluster cluster;
}
