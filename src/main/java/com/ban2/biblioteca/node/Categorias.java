package com.ban2.biblioteca.node;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Getter
@Setter
@Builder
@Node("Categorias")
public class Categorias {
    @Id
    @GeneratedValue
    private Long id;
    private String categoria;
}
