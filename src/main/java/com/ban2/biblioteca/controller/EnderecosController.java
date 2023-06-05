package com.ban2.biblioteca.controller;

import com.ban2.biblioteca.node.Enderecos;
import com.ban2.biblioteca.service.EnderecosService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class EnderecosController extends MainController {
    private final EnderecosService enderecosService;

    @Override
    public String listAll() {
        List<Enderecos> enderecos = enderecosService.listAllEnderecos();
        StringBuilder table = new StringBuilder();
        for(Enderecos endereco : enderecos){
            String line = endereco.getId() + " | " +
                    endereco.getCidade() + " | " +
                    endereco.getBairro() + " | " +
                    endereco.getLogradouro() + " | " +
                    endereco.getNumero();

            table.append(line).append("\n");
        }

        return table.toString();
    }

    @Override
    public String findById() {
        logger.info("Insira o id do endereço que deseja buscar: ");
        Long id = scanner.nextLong();
        Enderecos endereco = enderecosService.findEnderecosById(id);
        return String.format("| %d | %s | %s | %s | %d |", endereco.getId(), endereco.getCidade(), endereco.getBairro(), endereco.getLogradouro(), endereco.getNumero());
    }

    @Override
    public void save() {
        logger.info("Insira a cidade: ");
        String cidade = scanner.nextLine();
        logger.info("Insira o bairro: ");
        String bairro = scanner.nextLine();
        logger.info("Insira o logradouro: ");
        String logradouro = scanner.nextLine();
        logger.info("Insira o número: ");
        Long numero = scanner.nextLong();
        scanner.nextLine();

        Enderecos endereco = Enderecos.builder().cidade(cidade).bairro(bairro).logradouro(logradouro).numero(numero).build();

        Enderecos savedEndereco = enderecosService.saveEnderecos(endereco);

        if(Objects.nonNull(savedEndereco))
            logger.info("Endereço salvo com sucesso!");
        else
            logger.info("Erro ao salvar endereço!");
    }

    @Override
    public void update() {
        logger.info("Insira o id do endereço que deseja atualizar: ");
        Long id = scanner.nextLong();
        logger.info("Insira a cidade: ");
        String cidade = scanner.nextLine();
        logger.info("Insira o bairro: ");
        String bairro = scanner.nextLine();
        logger.info("Insira o logradouro: ");
        String logradouro = scanner.nextLine();
        logger.info("Insira o número: ");
        Long numero = scanner.nextLong();

        Enderecos endereco = Enderecos.builder().cidade(cidade).bairro(bairro).logradouro(logradouro).numero(numero).build();

        Enderecos savedEndereco =  enderecosService.updateEnderecos(id, endereco);

        if(Objects.nonNull(savedEndereco))
            logger.info("Endereço atualizado com sucesso!");
        else
            logger.info("Erro ao atualizar endereço!");
    }

    @Override
    public void delete() {
        logger.info("Insira o id do endereço que deseja deletar: ");
        Long id = scanner.nextLong();
        enderecosService.deleteEnderecos(id);

        logger.info("Endereço deletado com sucesso!");
    }
}
