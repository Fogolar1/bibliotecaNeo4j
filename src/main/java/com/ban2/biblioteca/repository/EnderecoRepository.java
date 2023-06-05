package com.ban2.biblioteca.repository;

import com.ban2.biblioteca.node.Enderecos;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface EnderecoRepository extends Neo4jRepository<Enderecos, Long> {

}
