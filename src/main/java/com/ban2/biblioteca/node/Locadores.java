package com.ban2.biblioteca.node;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Getter
@Setter
@Builder
@Node("Locadores")
public class Locadores {

    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private String email;
    private Integer telefone;

    @Relationship(type = "MORA_EM", direction = Relationship.Direction.OUTGOING)
    private List<Enderecos> enderecos;

    @Relationship(type = "LOCOU", direction = Relationship.Direction.OUTGOING)
    private List<Locacoes> locacoes;

    public void moraEm(Enderecos endereco){
        if(this.enderecos == null)
            this.enderecos = List.of(endereco);
        else
            this.enderecos.add(endereco);
    }
}
