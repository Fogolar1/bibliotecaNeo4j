package com.ban2.biblioteca.service;

import com.ban2.biblioteca.node.Enderecos;
import com.ban2.biblioteca.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnderecosService {
    private final EnderecoRepository enderecoRepository;

    public List<Enderecos> listAllEnderecos(){
        return enderecoRepository.findAll();
    }

    public Enderecos findEnderecosById(Long id){
        return enderecoRepository.findById(id).orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
    }

    public Enderecos saveEnderecos(Enderecos livro){
        return enderecoRepository.save(livro);
    }

    public Enderecos updateEnderecos(Long id, Enderecos enderecoToUpdate)  {
        Enderecos currentEndereco = findEnderecosById(id);

        currentEndereco.setBairro(enderecoToUpdate.getBairro());
        currentEndereco.setCidade(enderecoToUpdate.getCidade());
        currentEndereco.setLogradouro(enderecoToUpdate.getLogradouro());
        currentEndereco.setNumero(enderecoToUpdate.getNumero());
        return enderecoRepository.save(currentEndereco);
    }

    public void deleteEnderecos(Long id){
        enderecoRepository.deleteById(id);
    }
}
