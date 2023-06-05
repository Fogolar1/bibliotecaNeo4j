package com.ban2.biblioteca.service;

import com.ban2.biblioteca.node.Enderecos;
import com.ban2.biblioteca.node.Locadores;
import com.ban2.biblioteca.repository.LocadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LocadoresService {
    private final LocadorRepository locadorRepository;
    private final EnderecosService enderecoService;

    public List<Locadores> listAllLocadores(){
        return locadorRepository.findAll();
    }

    public Locadores findLocadoresById(Long id){
        return locadorRepository.findById(id).orElseThrow(() -> new RuntimeException("Locador não encontrado"));
    }

    public Locadores saveLocadores(Locadores locador, Long idEndereco){
        Enderecos endereco = enderecoService.findEnderecosById(idEndereco);
        locador.moraEm(endereco);

        return locadorRepository.save(locador);
    }

    public Locadores updateLocadores(Long id, Locadores locadorToUpdate)  {
        Locadores currentLocador = findLocadoresById(id);

        currentLocador.setNome(locadorToUpdate.getNome());
        currentLocador.setTelefone(locadorToUpdate.getTelefone());
        currentLocador.setEmail(locadorToUpdate.getEmail());

        return locadorRepository.save(currentLocador);
    }

    public void deleteLocadores(Long id){
        locadorRepository.deleteById(id);
    }
}
