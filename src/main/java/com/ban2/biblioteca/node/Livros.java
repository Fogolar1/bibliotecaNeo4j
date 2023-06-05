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
@Node("Livros")
public class Livros  {

    @Id
    @GeneratedValue
    private Long id;
    private String titulo;

    @Relationship(type = "ESCRITO_POR", direction = Relationship.Direction.OUTGOING)
    private List<Autores> autores;

    @Relationship(type = "PERTENCE_A", direction = Relationship.Direction.OUTGOING)
    private List<Categorias> categorias;

    public void escritoPor(Autores autor){
        if(this.autores == null)
            this.autores = List.of(autor);
        else
            this.autores.add(autor);
    }

    public void pertenceA(Categorias categoria){
        if(this.categorias == null)
            this.categorias = List.of(categoria);
        else
            this.categorias.add(categoria);
    }
}
