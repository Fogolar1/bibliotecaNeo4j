package com.ban2.biblioteca.controller;

import com.ban2.biblioteca.node.Locacoes;
import com.ban2.biblioteca.service.LocacoesService;
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


    @Override
    public String listAll() {
        List<Locacoes> locacoes = locacoesService.listAllLocacoes();
        return returnTable(locacoes);
    }

    @Override
    public String findById() {
        logger.info("Digite o id da locação que deseja buscar :");
        Long id = scanner.nextLong();

        Locacoes locacao = locacoesService.findLocacoesById(id);

        return String.format("%d | %s | %s | %s | %s",
                locacao.getId(),
                locacao.getDataInicio(),
                locacao.getDataFim(),
                locacao.getLocadores().get(0).getNome(),
                locacao.getLivros().get(0).getTitulo()
        );
    }

    @Override
    public void save() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

        logger.info("Digite a data de inicio da locação (Padrão dd/MM/yyyy) : ");
        String dataInicio = scanner.nextLine();
        logger.info("Digite a data de fim da locação (Padrão dd/MM/yyyy) :");
        String dataFim = scanner.nextLine();
        logger.info("Digite o id do livro :");
        Long idLivro = scanner.nextLong();
        logger.info("Digite o id do locador :");
        Long idLocador = scanner.nextLong();
        scanner.nextLine();

        Locacoes locacao = Locacoes.builder().dataFim(LocalDate.parse(dataFim, formatter)).dataInicio(LocalDate.parse(dataInicio, formatter)).build();

        Locacoes savedLocacao = locacoesService.saveLocacoes(locacao, idLivro, idLocador);

        if(Objects.nonNull(savedLocacao))
            logger.info("Locação salva com sucesso!");
        else
            logger.info("Erro ao salvar locação!");
    }

    @Override
    public void update() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

        logger.info("Digite o id da locação a ser atualizada :");
        Long id = scanner.nextLong();
        logger.info("Digite a nova data de inicio da locação (Padrão dd/MM/yyyy) : ");
        String dataInicio = scanner.nextLine();
        logger.info("Digite a nova data de fim da locação (Padrão dd/MM/yyyy) :");
        String dataFim = scanner.nextLine();

        Locacoes locacao = Locacoes.builder().dataFim(LocalDate.parse(dataFim, formatter)).dataInicio(LocalDate.parse(dataInicio, formatter)).build();

        Locacoes savedLocacoes = locacoesService.updateLocacoes(id, locacao);
        if(Objects.nonNull(savedLocacoes))
            logger.info("Locação atualizada com sucesso!");
        else
            logger.info("Erro ao atualizar locação!");
    }

    @Override
    public void delete() {
        logger.info("Digite o id da locação a ser deletada :");
        Long id = scanner.nextLong();

        locacoesService.deleteLocacoes(id);

        logger.info("Locação deletada com sucesso!");
    }

    public String relatorio(){
        logger.info("Insira o mês e o ano que deseja gerar o relatório (MM/yyyy): ");
        String mesAno = scanner.nextLine();

        LocalDate dataInicio = LocalDate.parse("01/" + mesAno, DateTimeFormatter.ofPattern(DATE_FORMAT));
        LocalDate dataFim = dataInicio.plusMonths(1);

        List<Locacoes> locacoes = locacoesService.relatorioLocacoes(dataInicio, dataFim);

        return returnTable(locacoes);
    }

    private String returnTable(List<Locacoes> locacoes){
        StringBuilder table = new StringBuilder();
        for(Locacoes locacao : locacoes){
            String row = String.format("%d | %s | %s | %s | %s",
                    locacao.getId(),
                    locacao.getDataInicio(),
                    locacao.getDataFim(),
                    locacao.getLocadores().get(0).getNome(),
                    locacao.getLivros().get(0).getTitulo()
            );

            table.append(row).append("\n");
        }

        return table.toString();
    }
}
