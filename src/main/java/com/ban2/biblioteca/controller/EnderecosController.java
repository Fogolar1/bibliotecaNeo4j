package com.ban2.biblioteca.controller;

import com.ban2.biblioteca.node.Enderecos;
import com.ban2.biblioteca.service.EnderecosService;
import com.ban2.biblioteca.utils.PrinterUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class EnderecosController extends MainController {
    private final EnderecosService enderecosService;
    private static final String[] HEADERS = {"ID", "CIDADE", "BAIRRO", "LOGRADOURO", "NUMERO"};

    @Override
    public void listAll() {
        List<Enderecos> enderecos = enderecosService.listAllEnderecos();
        PrinterUtils.printHeader(HEADERS);
        String retorno = returnTable(enderecos);
        System.out.println(retorno);
    }

    @Override
    public void findById() {
        System.out.println("Insira o id do endereço que deseja buscar: ");
        Long id = scanner.nextLong();
        Enderecos endereco = enderecosService.findEnderecosById(id);
        PrinterUtils.printHeader(HEADERS);
        String retorno = returnTable(List.of(endereco));
        System.out.println(retorno);
    }

    @Override
    public void save() {
        System.out.println("Insira a cidade: ");
        String cidade = scanner.nextLine();
        System.out.println("Insira o bairro: ");
        String bairro = scanner.nextLine();
        System.out.println("Insira o logradouro: ");
        String logradouro = scanner.nextLine();
        System.out.println("Insira o número: ");
        Long numero = scanner.nextLong();
        scanner.nextLine();

        Enderecos endereco = Enderecos.builder().cidade(cidade).bairro(bairro).logradouro(logradouro).numero(numero).build();

        Enderecos savedEndereco = enderecosService.saveEnderecos(endereco);

        if(Objects.nonNull(savedEndereco))
            System.out.println("Endereço salvo com sucesso!");
        else
            System.out.println("Erro ao salvar endereço!");
    }

    @Override
    public void update() {
        listAll();
        System.out.println("Insira o id do endereço que deseja atualizar: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Insira a cidade: ");
        String cidade = scanner.nextLine();
        System.out.println("Insira o bairro: ");
        String bairro = scanner.nextLine();
        System.out.println("Insira o logradouro: ");
        String logradouro = scanner.nextLine();
        System.out.println("Insira o número: ");
        Long numero = scanner.nextLong();
        scanner.nextLine();

        Enderecos endereco = Enderecos.builder().cidade(cidade).bairro(bairro).logradouro(logradouro).numero(numero).build();

        Enderecos savedEndereco =  enderecosService.updateEnderecos(id, endereco);

        if(Objects.nonNull(savedEndereco))
            System.out.println("Endereço atualizado com sucesso!");
        else
            System.out.println("Erro ao atualizar endereço!");
    }

    @Override
    public void delete() {
        listAll();
        System.out.println("Insira o id do endereço que deseja deletar: ");
        Long id = scanner.nextLong();
        enderecosService.deleteEnderecos(id);

        System.out.println("Endereço deletado com sucesso!");
    }

    public String returnTable(List<Enderecos> enderecos){
        StringBuilder table = new StringBuilder();
        for(Enderecos endereco : enderecos){
            String row = String.format("| %-45d | %-45s | %-45s | %-45s | %-45d |",
                    endereco.getId(),
                    endereco.getCidade(),
                    endereco.getBairro(),
                    endereco.getLogradouro(),
                    endereco.getNumero());

            table.append(row).append("\n");

            if(enderecos.indexOf(endereco) == enderecos.size() - 1)
                table.append(PrinterUtils.printLine(row.length()));
        }

        return table.toString();
    }
}
