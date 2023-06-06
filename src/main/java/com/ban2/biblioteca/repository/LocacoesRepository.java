package com.ban2.biblioteca.repository;


import com.ban2.biblioteca.node.Locacoes;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.time.LocalDate;
import java.util.List;

public interface LocacoesRepository extends Neo4jRepository<Locacoes, Long> {
    List<Locacoes> findAllByDataInicioIsBetween(LocalDate dataInicio, LocalDate dataFim);

    @Query("MATCH (u:Locadores)-[r:LOCOU]->(m:Livros) RETURN r")
    List<Locacoes> findAllBy();
}
