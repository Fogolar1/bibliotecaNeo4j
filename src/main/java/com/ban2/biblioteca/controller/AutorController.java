package com.ban2.biblioteca.controller;

import com.ban2.biblioteca.node.Autores;
import com.ban2.biblioteca.service.AutorService;
import com.ban2.biblioteca.utils.PrinterUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class AutorController extends MainController {
    private final AutorService autorService;
    private static final String ASK_FOR_ID = "Digite o id do autor: ";

    private static final String[] HEADERS = {"ID", "Nome"};

    @Override
    public void listAll()
    {
        StringBuilder table = new StringBuilder();
        List<Autores> autores = autorService.listAllAutores();
        PrinterUtils.printHeader(HEADERS);
        for(Autores autor : autores)
        {
            String row = String.format("| %-45d | %-45s |", autor.getId(), autor.getNome());
            table.append(row).append("\n");
            if(autores.indexOf(autor) == autores.size() - 1)
                table.append(PrinterUtils.printLine(row.length()));
        }

        String retorno = table.toString();
        System.out.println(retorno);
    }

    @Override
    public void findById() {
        System.out.println(ASK_FOR_ID);
        Long id = scanner.nextLong();
        PrinterUtils.printHeader(HEADERS);
        Autores autor =  autorService.findAutoresById(id);

        String retorno =  String.format("| %-45d | %-45s |", autor.getId(), autor.getNome());
        System.out.println(retorno);
        System.out.print(PrinterUtils.printLine(retorno.length()));
    }

    @Override
    public void save(){
        System.out.println("Digite o nome do autor: ");
        String nome = scanner.nextLine();
        Autores autor = Autores.builder().nome(nome).build();

        Autores savedAutor =  autorService.saveAutores(autor);

        if(Objects.nonNull(savedAutor))
            System.out.println("Autor salvo com sucesso!");
        else
            System.out.println("Erro ao salvar autor!");
    }

    @Override
    public void update() {
        listAll();
        System.out.println(ASK_FOR_ID);
        Long id = scanner.nextLong();
        System.out.println("Digite o novo nome do autor: ");
        String nome = scanner.nextLine();
        Autores autorToSave = Autores.builder().nome(nome).build();

        Autores autor =  autorService.updateAutores(id, autorToSave);

        if(Objects.nonNull(autor))
            System.out.println("Autor salvo com sucesso!");
        else
            System.out.println("Erro ao salvar autor!");
    }

    @Override
    public void delete() {
        listAll();
        System.out.println(ASK_FOR_ID);
        Long id = scanner.nextLong();

        autorService.deleteAutores(id);

        System.out.println("Autor deletado com sucesso!");
    }

}
