package com.ban2.biblioteca.service;

import com.ban2.biblioteca.node.Categorias;
import com.ban2.biblioteca.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public List<Categorias> listAllCategorias(){
        return categoriaRepository.findAll();
    }

    public Categorias findCategoriasById(Long id){
        return categoriaRepository.findById(id).orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
    }

    public Categorias saveCategorias(Categorias categoria){
        return categoriaRepository.save(categoria);
    }

    public Categorias updateCategorias(Long id, Categorias categoriaToUpdate)  {
        Categorias currentCategoria = findCategoriasById(id);

        currentCategoria.setCategoria(categoriaToUpdate.getCategoria());
        return categoriaRepository.save(currentCategoria);
    }

    public void deleteCategorias(Long id){
        categoriaRepository.deleteById(id);
    }
}
