package com.ban2.biblioteca.repository;

import com.ban2.biblioteca.node.Livros;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface LivroRepository extends Neo4jRepository<Livros, Long> {

}
