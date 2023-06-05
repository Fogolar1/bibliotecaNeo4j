package com.ban2.biblioteca.controller;

import com.ban2.biblioteca.node.Categorias;
import com.ban2.biblioteca.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class CategoriaController extends MainController {
    private final CategoriaService categoriaService;
    @Override
    public String listAll() {
        StringBuilder table = new StringBuilder();
        List<Categorias> categorias = categoriaService.listAllCategorias();
        for(Categorias categoria : categorias)
        {
            String row = String.format("| %d | %s |", categoria.getId(), categoria.getCategoria());
            table.append(row).append("\n");
        }

        return table.toString();
    }

    @Override
    public String findById() {
        logger.info("Insira o id da categoria que deseja buscar: ");
        Long id = scanner.nextLong();
        Categorias categoria = categoriaService.findCategoriasById(id);

        return String.format("| %d | %s |", categoria.getId(), categoria.getCategoria());
    }

    @Override
    public void save() {
        logger.info("Insira o nome da categoria que deseja salvar: ");
        String nomeCategoria = scanner.nextLine();
        Categorias categoria = Categorias.builder().categoria(nomeCategoria).build();
        Categorias savedCategoria = categoriaService.saveCategorias(categoria);

        if(Objects.nonNull(savedCategoria))
            logger.info("Categoria salva com sucesso!");
        else
            logger.info("Erro ao salvar categoria!");
    }

    @Override
    public void update() {
        logger.info("Insira o id da categoria que deseja atualizar: ");
        Long id = scanner.nextLong();
        logger.info("Insira o novo nome da categoria: ");
        String nomeCategoria = scanner.nextLine();
        Categorias categoria = Categorias.builder().categoria(nomeCategoria).build();

        Categorias savedCategoria = categoriaService.updateCategorias(id, categoria);

        if(Objects.nonNull(savedCategoria))
            logger.info("Categoria atualizada com sucesso!");
        else
            logger.info("Erro ao atualizar categoria!");
    }

    @Override
    public void delete() {
        logger.info("Insira o id da categoria que deseja deletar: ");
        Long id = scanner.nextLong();
        categoriaService.deleteCategorias(id);

        logger.info("Categoria deletada com sucesso!");
    }
}
