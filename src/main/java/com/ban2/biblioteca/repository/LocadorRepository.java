package com.ban2.biblioteca.repository;

import com.ban2.biblioteca.node.Locadores;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

public interface LocadorRepository extends Neo4jRepository<Locadores, Long> {

    @Query("MATCH (u:Locadores)-[r:LOCOU]-() WHERE id(r)={id} RETURN id(u)")
    Long getByLocacao(Long id);
}
