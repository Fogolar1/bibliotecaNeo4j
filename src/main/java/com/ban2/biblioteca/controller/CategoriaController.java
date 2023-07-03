package com.ban2.biblioteca.controller;

import com.ban2.biblioteca.node.Categorias;
import com.ban2.biblioteca.service.CategoriaService;
import com.ban2.biblioteca.utils.PrinterUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class CategoriaController extends MainController {
    private final CategoriaService categoriaService;
    private static final String[] HEADERS = {"ID", "CATEGORIA"};
    @Override
    public void listAll() {
        StringBuilder table = new StringBuilder();
        List<Categorias> categorias = categoriaService.listAllCategorias();
        PrinterUtils.printHeader(HEADERS);
        for(Categorias categoria : categorias)
        {
            String row = String.format("| %-45d | %-45s |", categoria.getId(), categoria.getCategoria());
            table.append(row).append("\n");
            if(categorias.indexOf(categoria) == categorias.size() - 1)
                table.append(PrinterUtils.printLine(row.length()));
        }

        String retorno = table.toString();
        System.out.println(retorno);
    }

    @Override
    public void findById() {
        System.out.println("Insira o id da categoria que deseja buscar: ");
        Long id = scanner.nextLong();
        Categorias categoria = categoriaService.findCategoriasById(id);
        PrinterUtils.printHeader(HEADERS);
        String retorno = String.format("| %-45d | %-45s |", categoria.getId(), categoria.getCategoria());
        System.out.println(retorno);
        System.out.print(PrinterUtils.printLine(retorno.length()));
    }

    @Override
    public void save() {
        System.out.println("Insira o nome da categoria que deseja salvar: ");
        String nomeCategoria = scanner.nextLine();
        Categorias categoria = Categorias.builder().categoria(nomeCategoria).build();
        Categorias savedCategoria = categoriaService.saveCategorias(categoria);

        if(Objects.nonNull(savedCategoria))
            System.out.println("Categoria salva com sucesso!");
        else
            System.out.println("Erro ao salvar categoria!");
    }

    @Override
    public void update() {
        listAll();
        System.out.println("Insira o id da categoria que deseja atualizar: ");
        Long id = scanner.nextLong();
        System.out.println("Insira o novo nome da categoria: ");
        String nomeCategoria = scanner.nextLine();
        Categorias categoria = Categorias.builder().categoria(nomeCategoria).build();

        Categorias savedCategoria = categoriaService.updateCategorias(id, categoria);

        if(Objects.nonNull(savedCategoria))
            System.out.println("Categoria atualizada com sucesso!");
        else
            System.out.println("Erro ao atualizar categoria!");
    }

    @Override
    public void delete() {
        listAll();
        System.out.println("Insira o id da categoria que deseja deletar: ");
        Long id = scanner.nextLong();
        categoriaService.deleteCategorias(id);

        System.out.println("Categoria deletada com sucesso!");
    }
}
