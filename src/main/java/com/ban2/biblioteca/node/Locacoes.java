package com.ban2.biblioteca.node;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@Node("Locacoes")
public class Locacoes {

    @Id
    @GeneratedValue
    private Long id;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    @Relationship(type = "LOCADO_POR", direction = Relationship.Direction.OUTGOING)
    private List<Locadores> locadores;

    @Relationship(type = "LOCADO", direction = Relationship.Direction.OUTGOING)
    private List<Livros> livros;

    public void locadoPor(Locadores locadores) {
        if(this.locadores == null)
            this.locadores = List.of(locadores);
        else
            this.locadores.add(locadores);
    }

    public void locado(Livros livros) {
        if(this.livros == null)
            this.livros = List.of(livros);
        else
            this.livros.add(livros);
    }
}
