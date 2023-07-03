package com.ban2.biblioteca.controller;

import com.ban2.biblioteca.node.Enderecos;
import com.ban2.biblioteca.node.Locadores;
import com.ban2.biblioteca.service.LocadoresService;
import com.ban2.biblioteca.utils.PrinterUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@Controller
@RequiredArgsConstructor
public class LocadoresController extends MainController{
    private final LocadoresService locadoresService;
    private final EnderecosController enderecosController;
    private static final String[] HEADERS = {"ID", "NOME", "TELEFONE", "EMAIL", "CIDADE", "BAIRRO", "LOGRADOURO", "NUMERO"};

    @Override
    public void listAll() {
        List<Locadores> locadores = locadoresService.listAllLocadores();
        String retorno = returnTable(locadores);
        System.out.println(retorno);
    }

    @Override
    public void findById() {
        System.out.println("Digite o id do locador: ");
        Long id = scanner.nextLong();
        Locadores locador = locadoresService.findLocadoresById(id);

        String retorno = returnTable(List.of(locador));
        System.out.println(retorno);
    }

    @Override
    public void save() {
        System.out.println("Digite o nome do locador: ");
        String nome = scanner.nextLine();
        System.out.println("Digite o telefone do locador: ");
        Integer telefone = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Digite o email do locador: ");
        String email = scanner.nextLine();
        enderecosController.listAll();
        System.out.println("Digite o id do endereço do locador: ");
        Long idEndereco = scanner.nextLong();
        scanner.nextLine();
        Locadores locador = Locadores.builder().nome(nome).telefone(telefone).email(email).build();

        Locadores savedLocador = locadoresService.saveLocadores(locador, idEndereco);

        if(savedLocador != null)
            System.out.println("Locador salvo com sucesso!");
        else
            System.out.println("Erro ao salvar locador!");
    }

    @Override
    public void update() {
        listAll();
        System.out.println("Digite o id do locador que deseja atualizar : ");
        Long id = scanner.nextLong();
        System.out.println("Digite o novo nome do locador: ");
        String nome = scanner.nextLine();
        System.out.println("Digite o novo telefone do locador: ");
        Integer telefone = scanner.nextInt();
        System.out.println("Digite o novo email do locador: ");
        String email = scanner.nextLine();

        Locadores locadorToSave = Locadores.builder().nome(nome).telefone(telefone).email(email).build();

        Locadores savedLocador = locadoresService.updateLocadores(id, locadorToSave);

        if(Objects.nonNull(savedLocador))
            System.out.println("Locador atualizado com sucesso!");
        else
            System.out.println("Erro ao atualizar locador!");

    }

    @Override
    public void delete() {
        listAll();
        System.out.println("Digite o id do locador que deseja deletar: ");
        Long id = scanner.nextLong();
        locadoresService.deleteLocadores(id);
    }

    public String relatorio(){
        List<Locadores> locadores = locadoresService.listAllLocadores();
        StringBuilder table = new StringBuilder();
        for(Locadores locador : locadores){
            Enderecos enderecos = locador.getEnderecos().get(0);
            String row = String.format("| %-45d | %-45s | %-45s | %-45s | %-45s | %-45s | %-45s | %-45d",
                    locador.getId(), locador.getNome(), locador.getTelefone(), locador.getEmail(),
                    enderecos.getCidade(), enderecos.getBairro(), enderecos.getLogradouro(), enderecos.getNumero());

            table.append(row).append("\n");
        }
        return table.toString();
    }

    public String returnTable(List<Locadores> locadores){
        StringBuilder table = new StringBuilder();
        PrinterUtils.printHeader(Arrays.copyOfRange(HEADERS, 0, 4));

        for(Locadores locador : locadores){
            String row = String.format("| %-45d | %-45s | %-45s | %-45s |",
                    locador.getId(),
                    locador.getNome(),
                    locador.getTelefone(),
                    locador.getEmail());

            table.append(row).append("\n");

            if(locadores.indexOf(locador) == locadores.size() - 1)
                table.append(PrinterUtils.printLine(row.length()));
        }
        return table.toString();
    }
}
