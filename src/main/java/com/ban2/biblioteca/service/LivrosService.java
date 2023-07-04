package com.ban2.biblioteca.service;

import com.ban2.biblioteca.node.Autores;
import com.ban2.biblioteca.node.Categorias;
import com.ban2.biblioteca.node.Livros;
import com.ban2.biblioteca.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class LivrosService {
    private final LivroRepository livrosRepository;
    private final CategoriaService categoriaService;
    private final AutorService autorService;

    public List<Livros> listAllLivros(){
        return livrosRepository.findAll();
    }

    public Livros findLivrosById(Long id){
        return livrosRepository.findById(id).orElseThrow(() -> new RuntimeException("Livro não encontrado"));
    }

    public Livros saveLivros(Livros livro, Long idCategoria, Long idAutor){
        Categorias categoria = categoriaService.findCategoriasById(idCategoria);
        Autores autor = autorService.findAutoresById(idAutor);
        livro.escritoPor(autor);
        livro.pertenceA(categoria);

        return livrosRepository.save(livro);
    }

    public Livros updateLivros(Long id, Livros livroToUpdate)  {
        Livros currentLivro = findLivrosById(id);

        currentLivro.setTitulo(livroToUpdate.getTitulo());
        return livrosRepository.save(currentLivro);
    }

    public void deleteLivros(Long id){
        livrosRepository.deleteById(id);
    }
}
