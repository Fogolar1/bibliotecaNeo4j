package com.ban2.biblioteca.controller;

import com.ban2.biblioteca.node.Livros;
import com.ban2.biblioteca.service.LivrosService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class LivrosController extends MainController {
    private final LivrosService livrosService;
    @Override
    public String listAll() {
        StringBuilder table = new StringBuilder();
        List<Livros> livros = livrosService.listAllLivros();
        for(Livros livro : livros){
            String line = String.format("| %-45d | %-45s %n",
                    livro.getId(),
                    livro.getTitulo());

            table.append(line);
        }


        return table.toString();
    }

    @Override
    public String findById() {
        logger.info("Insira o id do livro que deseja buscar: ");
        Long id = scanner.nextLong();
        Livros livro = livrosService.findLivrosById(id);

        return String.format("| %-45d | %-45s  %n",
                livro.getId(),
                livro.getTitulo());
    }

    @Override
    public void save() {
        logger.info("Insira o nome do livro que deseja salvar: ");
        String nomeLivro = scanner.nextLine();
        logger.info("Insira o id da categoria do livro: ");
        Long idCategoria = scanner.nextLong();
        scanner.nextLine(); // Consume the \n character
        logger.info("Insira o id do autor do livro: ");
        Long idAutor = scanner.nextLong();
        scanner.nextLine(); // Consume the \n character

        Livros livro = Livros.builder().titulo(nomeLivro).build();

        Livros savedLivro = livrosService.saveLivros(livro, idCategoria, idAutor);
        if(Objects.nonNull(savedLivro))
            logger.info("Livro salvo com sucesso!");
        else
            logger.info("Erro ao salvar livro!");

    }

    @Override
    public void update() {
        logger.info("Insira o id do livro que deseja atualizar: ");
        Long id = scanner.nextLong();
        logger.info("Insira o novo nome do livro: ");
        String nomeLivro = scanner.nextLine();

        Livros livro = Livros.builder().titulo(nomeLivro).build();

        Livros savedLivro = livrosService.updateLivros(id, livro);
        if(Objects.nonNull(savedLivro))
            logger.info("Livro atualizado com sucesso!");
        else
            logger.info("Erro ao atualizar livro!");
    }

    @Override
    public void delete() {
        logger.info("Insira o id do livro que deseja deletar: ");
        Long id = scanner.nextLong();
        livrosService.deleteLivros(id);

        logger.info("Livro deletado com sucesso!");
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
        }

        return table.toString();
    }
}
