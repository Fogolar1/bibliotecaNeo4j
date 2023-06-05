package com.ban2.biblioteca.node;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Getter
@Builder
@Setter
@Node("Enderecos")
public class Enderecos {

    @Id
    @GeneratedValue
    private Long id;
    private String cidade;
    private String bairro;
    private String logradouro;
    private Long numero;

}
