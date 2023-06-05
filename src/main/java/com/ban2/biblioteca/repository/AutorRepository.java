package com.ban2.biblioteca.repository;

import com.ban2.biblioteca.node.Autores;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface AutorRepository extends Neo4jRepository<Autores, Long> {

}
