package com.ban2.biblioteca.repository;

import com.ban2.biblioteca.node.Categorias;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface CategoriaRepository extends Neo4jRepository<Categorias, Long> {

}
