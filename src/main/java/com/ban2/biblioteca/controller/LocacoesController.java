package com.ban2.biblioteca.controller;

import com.ban2.biblioteca.node.Locacoes;
import com.ban2.biblioteca.service.LocacoesService;
import com.ban2.biblioteca.utils.PrinterUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Controller
public class LocacoesController extends MainController{
    private final LocacoesService locacoesService;
    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final String[] HEADERS = {"ID", "Data Inicio", "Data Fim", "Livro", "Locador"};

    @Override
    public void listAll() {
        List<Locacoes> locacoes = locacoesService.listAllLocacoes();
        PrinterUtils.printHeader(HEADERS);
        String retorno = returnTable(locacoes);
        System.out.println(retorno);
    }

    @Override
    public void findById() {
        System.out.println("Digite o id da locação que deseja buscar :");
        Long id = scanner.nextLong();
        PrinterUtils.printHeader(HEADERS);
        Locacoes locacao = locacoesService.findLocacoesById(id);
        String retorno = returnTable(List.of(locacao));

        System.out.println(retorno);
    }

    @Override
    public void save() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

        System.out.println("Digite a data de inicio da locação (Padrão dd/MM/yyyy) : ");
        String dataInicio = scanner.nextLine();
        System.out.println("Digite a data de fim da locação (Padrão dd/MM/yyyy) :");
        String dataFim = scanner.nextLine();
        System.out.println("Digite o id do livro :");
        Long idLivro = scanner.nextLong();
        System.out.println("Digite o id do locador :");
        Long idLocador = scanner.nextLong();
        scanner.nextLine();

        Locacoes savedLocacao = locacoesService.saveLocacoes(LocalDate.parse(dataInicio, formatter), LocalDate.parse(dataFim, formatter) , idLivro, idLocador);

        if(Objects.nonNull(savedLocacao))
            System.out.println("Locação salva com sucesso!");
        else
            System.out.println("Erro ao salvar locação!");
    }

    @Override
    public void update() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

        System.out.println("Digite o id da locação a ser atualizada :");
        Long id = scanner.nextLong();
        System.out.println("Digite a nova data de inicio da locação (Padrão dd/MM/yyyy) : ");
        String dataInicio = scanner.nextLine();
        System.out.println("Digite a nova data de fim da locação (Padrão dd/MM/yyyy) :");
        String dataFim = scanner.nextLine();

        Locacoes savedLocacoes = locacoesService.updateLocacoes(id, LocalDate.parse(dataInicio, formatter), LocalDate.parse(dataFim, formatter) );
        if(Objects.nonNull(savedLocacoes))
            System.out.println("Locação atualizada com sucesso!");
        else
            System.out.println("Erro ao atualizar locação!");
    }

    @Override
    public void delete() {
        System.out.println("Digite o id da locação a ser deletada :");
        Long id = scanner.nextLong();

        locacoesService.deleteLocacoes(id);

        System.out.println("Locação deletada com sucesso!");
    }

    public String relatorio(){
        System.out.println("Insira o mês e o ano que deseja gerar o relatório (MM/yyyy): ");
        String mesAno = scanner.nextLine();

        LocalDate dataInicio = LocalDate.parse("01/" + mesAno, DateTimeFormatter.ofPattern(DATE_FORMAT));
        LocalDate dataFim = dataInicio.plusMonths(1);

        List<Locacoes> locacoes = locacoesService.relatorioLocacoes(dataInicio, dataFim);

        return returnTable(locacoes);
    }

    private String returnTable(List<Locacoes> locacoes){
        StringBuilder table = new StringBuilder();
        for(Locacoes locacao : locacoes){
            String row = String.format("%-45d | %-45s | %-45s | %-45s | %-45s",
                    locacao.getId(),
                    locacao.getDataInicio(),
                    locacao.getDataFim(),
                    locacao.getLocadores().getNome(),
                    locacao.getLivros().getTitulo()
            );
            table.append(row).append("\n");

            if(locacoes.indexOf(locacao) == locacoes.size() - 1)
                table.append(PrinterUtils.printLine(row.length()));
        }

        return table.toString();
    }
}
