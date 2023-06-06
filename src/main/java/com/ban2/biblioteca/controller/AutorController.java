package com.ban2.biblioteca.controller;

import com.ban2.biblioteca.node.Autores;
import com.ban2.biblioteca.service.AutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class AutorController extends MainController {
    private final AutorService autorService;
    private static final String ASK_FOR_ID = "Digite o id do autor: ";

    @Override
    public String listAll()
    {
        StringBuilder table = new StringBuilder();
        List<Autores> autores = autorService.listAllAutores();
        for(Autores autor : autores)
        {
            String row = String.format("| %-45d | %-45s |", autor.getId(), autor.getNome());
            table.append(row).append("\n");
        }

        return table.toString();
    }

    @Override
    public String findById() {
        logger.info(ASK_FOR_ID);
        Long id = scanner.nextLong();
        Autores autor =  autorService.findAutoresById(id);

        return String.format("| %-45d | %-45s |", autor.getId(), autor.getNome());
    }

    @Override
    public void save(){
        logger.info("Digite o nome do autor: ");
        String nome = scanner.nextLine();
        Autores autor = Autores.builder().nome(nome).build();

        Autores savedAutor =  autorService.saveAutores(autor);

        if(Objects.nonNull(savedAutor))
            logger.info("Autor salvo com sucesso!");
        else
            logger.info("Erro ao salvar autor!");
    }

    @Override
    public void update() {
        logger.info(ASK_FOR_ID);
        Long id = scanner.nextLong();
        logger.info("Digite o novo nome do autor: ");
        String nome = scanner.nextLine();
        Autores autorToSave = Autores.builder().nome(nome).build();

        Autores autor =  autorService.updateAutores(id, autorToSave);

        if(Objects.nonNull(autor))
            logger.info("Autor salvo com sucesso!");
        else
            logger.info("Erro ao salvar autor!");
    }

    @Override
    public void delete() {
        logger.info(ASK_FOR_ID);
        Long id = scanner.nextLong();

        autorService.deleteAutores(id);

        logger.info("Autor deletado com sucesso!");
    }

}
