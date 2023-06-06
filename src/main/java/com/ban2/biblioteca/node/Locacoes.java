package com.ban2.biblioteca.node;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
@RelationshipProperties
public class Locacoes {

    @RelationshipId
    @GeneratedValue
    private Long id;
    @Property("dataInicio")
    private LocalDate dataInicio;
    @Property("dataFim")
    private LocalDate dataFim;

    @TargetNode
    private final Livros livros;
}
