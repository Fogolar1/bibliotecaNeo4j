package com.ban2.biblioteca.controller;

import com.ban2.biblioteca.node.Enderecos;
import com.ban2.biblioteca.node.Locadores;
import com.ban2.biblioteca.service.LocadoresService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Objects;


@Controller
@RequiredArgsConstructor
public class LocadoresController extends MainController{
    private final LocadoresService locadoresService;

    @Override
    public String listAll() {
        List<Locadores> locadores = locadoresService.listAllLocadores();
        StringBuilder table = new StringBuilder();
        for(Locadores locador : locadores){
            String row = String.format("| %d | %s | %s | %s ",
                    locador.getId(), locador.getNome(), locador.getTelefone(), locador.getEmail());

            table.append(row).append("\n");
        }
        return table.toString();
    }

    @Override
    public String findById() {
        logger.info("Digite o id do locador: ");
        Long id = scanner.nextLong();
        Locadores locador = locadoresService.findLocadoresById(id);

        return String.format("| %d | %s | %s | %s ",
                locador.getId(), locador.getNome(), locador.getTelefone(), locador.getEmail());
    }

    @Override
    public void save() {
        logger.info("Digite o nome do locador: ");
        String nome = scanner.nextLine();
        logger.info("Digite o telefone do locador: ");
        Integer telefone = scanner.nextInt();
        scanner.nextLine();
        logger.info("Digite o email do locador: ");
        String email = scanner.nextLine();
        logger.info("Digite o id do endereço do locador: ");
        Long idEndereco = scanner.nextLong();
        scanner.nextLine();
        Locadores locador = Locadores.builder().nome(nome).telefone(telefone).email(email).build();

        Locadores savedLocador = locadoresService.saveLocadores(locador, idEndereco);

        if(savedLocador != null)
            logger.info("Locador salvo com sucesso!");
        else
            logger.info("Erro ao salvar locador!");
    }

    @Override
    public void update() {
        logger.info("Digite o id do locador que deseja atualizar : ");
        Long id = scanner.nextLong();
        logger.info("Digite o novo nome do locador: ");
        String nome = scanner.nextLine();
        logger.info("Digite o novo telefone do locador: ");
        Integer telefone = scanner.nextInt();
        logger.info("Digite o novo email do locador: ");
        String email = scanner.nextLine();

        Locadores locadorToSave = Locadores.builder().nome(nome).telefone(telefone).email(email).build();

        Locadores savedLocador = locadoresService.updateLocadores(id, locadorToSave);

        if(Objects.nonNull(savedLocador))
            logger.info("Locador atualizado com sucesso!");
        else
            logger.info("Erro ao atualizar locador!");

    }

    @Override
    public void delete() {
        logger.info("Digite o id do locador que deseja deletar: ");
        Long id = scanner.nextLong();
        locadoresService.deleteLocadores(id);
    }

    public String relatorio(){
        List<Locadores> locadores = locadoresService.listAllLocadores();
        StringBuilder table = new StringBuilder();
        for(Locadores locador : locadores){
            Enderecos enderecos = locador.getEnderecos().get(0);
            String row = String.format("| %d | %s | %s | %s | %s | %s | %s | %d",
                    locador.getId(), locador.getNome(), locador.getTelefone(), locador.getEmail(),
                    enderecos.getCidade(), enderecos.getBairro(), enderecos.getLogradouro(), enderecos.getNumero());

            table.append(row).append("\n");
        }
        return table.toString();
    }
}
