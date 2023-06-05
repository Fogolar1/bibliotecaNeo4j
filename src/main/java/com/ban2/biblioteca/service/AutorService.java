package com.ban2.biblioteca.service;

import com.ban2.biblioteca.node.Autores;
import com.ban2.biblioteca.repository.AutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AutorService {
    private final AutorRepository autorRepository;

    public List<Autores> listAllAutores(){
        return autorRepository.findAll();
    }

    public Autores findAutoresById(Long id){
        return autorRepository.findById(id).orElseThrow(() -> new RuntimeException("Autor não encontrado"));
    }

    public Autores saveAutores(Autores autor){
        return autorRepository.save(autor);
    }

    public Autores updateAutores(Long id, Autores autorToSave)  {
        Autores currentAutor = findAutoresById(id);
        currentAutor.setNome(autorToSave.getNome());
        return autorRepository.save(currentAutor);
    }

    public void deleteAutores(Long id){
        autorRepository.deleteById(id);
    }
}
