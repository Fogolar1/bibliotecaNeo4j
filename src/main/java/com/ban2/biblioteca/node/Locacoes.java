package com.ban2.biblioteca.node;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@RelationshipProperties
public class Locacoes {

    @RelationshipId
    @GeneratedValue
    private Long id;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    private final Locadores locadores;

    @TargetNode
    private final Livros livros;
}
