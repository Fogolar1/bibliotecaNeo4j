package com.ban2.biblioteca.repository;

import com.ban2.biblioteca.node.Locadores;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface LocadorRepository extends Neo4jRepository<Locadores, Long> {

}
