package com.ban2.biblioteca.controller;

import com.ban2.biblioteca.node.Livros;
import com.ban2.biblioteca.service.LivrosService;
import com.ban2.biblioteca.utils.PrinterUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class LivrosController extends MainController {
    private final LivrosService livrosService;
    private static final String[] HEADERS = {"ID", "TITULO", "AUTOR", "CATEGORIA"};

    @Override
    public void listAll() {
        StringBuilder table = new StringBuilder();
        List<Livros> livros = livrosService.listAllLivros();
        PrinterUtils.printHeader(Arrays.copyOfRange(HEADERS, 0, 2));
        for(Livros livro : livros){
            String line = String.format("| %-45d | %-45s | %n", livro.getId(), livro.getTitulo());

            table.append(line);
            if(livros.indexOf(livro) == livros.size() - 1)
                table.append(PrinterUtils.printLine(line.length() - 2));
        }

        String retorno = table.toString();
        System.out.print(retorno);
    }

    @Override
    public void findById() {
        System.out.println("Insira o id do livro que deseja buscar: ");
        Long id = scanner.nextLong();
        Livros livro = livrosService.findLivrosById(id);
        PrinterUtils.printHeader(Arrays.copyOfRange(HEADERS, 0, 2));
        String retorno = String.format("| %-45d | %-45s | %n", livro.getId(), livro.getTitulo());
        System.out.print(retorno);
        System.out.print(PrinterUtils.printLine(retorno.length() - 3));
    }

    @Override
    public void save() {
        System.out.println("Insira o nome do livro que deseja salvar: ");
        String nomeLivro = scanner.nextLine();
        System.out.println("Insira o id da categoria do livro: ");
        Long idCategoria = scanner.nextLong();
        scanner.nextLine(); // Consume the \n character
        System.out.println("Insira o id do autor do livro: ");
        Long idAutor = scanner.nextLong();
        scanner.nextLine(); // Consume the \n character

        Livros livro = Livros.builder().titulo(nomeLivro).build();

        Livros savedLivro = livrosService.saveLivros(livro, idCategoria, idAutor);
        if(Objects.nonNull(savedLivro))
            System.out.println("Livro salvo com sucesso!");
        else
            System.out.println("Erro ao salvar livro!");

    }

    @Override
    public void update() {
        System.out.println("Insira o id do livro que deseja atualizar: ");
        Long id = scanner.nextLong();
        System.out.println("Insira o novo nome do livro: ");
        String nomeLivro = scanner.nextLine();

        Livros livro = Livros.builder().titulo(nomeLivro).build();

        Livros savedLivro = livrosService.updateLivros(id, livro);
        if(Objects.nonNull(savedLivro))
            System.out.println("Livro atualizado com sucesso!");
        else
            System.out.println("Erro ao atualizar livro!");
    }

    @Override
    public void delete() {
        System.out.println("Insira o id do livro que deseja deletar: ");
        Long id = scanner.nextLong();
        livrosService.deleteLivros(id);

        System.out.println("Livro deletado com sucesso!");
    }


    public String relatorio() {
        StringBuilder table = new StringBuilder();
        List<Livros> livros = livrosService.listAllLivros();
        for (Livros livro : livros) {
            String line = String.format("| %-45d | %-45s | %-45s | %-45s %n",
                    livro.getId(),
                    livro.getTitulo(),
                    livro.getAutores().get(0).getNome(),
                    livro.getCategorias().get(0).getCategoria());

            table.append(line);

            if(livros.indexOf(livro) == livros.size() - 1)
                table.append(PrinterUtils.printLine(line.length()));
        }

        return table.toString();
    }
}
