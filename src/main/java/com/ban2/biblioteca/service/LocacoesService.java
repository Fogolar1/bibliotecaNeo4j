package com.ban2.biblioteca.service;

import com.ban2.biblioteca.node.Livros;
import com.ban2.biblioteca.node.Locacoes;
import com.ban2.biblioteca.node.Locadores;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LocacoesService {
    private final LocadoresService locadoresService;
    private final LivrosService livrosService;

    public List<Locacoes> listAllLocacoes(){
        List<Locacoes> returnLocacoes = new ArrayList<>();

        List<Locadores> locadores = locadoresService.listAllLocadores();
        for(Locadores locador : locadores){
            returnLocacoes.addAll(locador.getLocacoes());
        }

        return returnLocacoes;
    }

    public Locacoes findLocacoesById(Long id){
        return listAllLocacoes().stream().filter(locacoes -> locacoes.getId().equals(id)).findFirst().orElseThrow(() -> new RuntimeException("Locação não encontrada"));
    }

    public void saveLocacoes(LocalDate dataInicio, LocalDate dataFim , Long idLivro,Long idLocador){
        Livros livro = livrosService.findLivrosById(idLivro);
        Locadores locadores = locadoresService.findLocadoresById(idLocador);

        Locacoes locacao = new Locacoes(livro);
        locacao.setDataFim(dataFim);
        locacao.setDataInicio(dataInicio);

        locadores.locou(locacao);

        locadoresService.save(locadores);
    }

    @SneakyThrows
    public void updateLocacoes(Long id, LocalDate dataInicio, LocalDate dataFim)  {
        Locacoes currentLocacoes = findLocacoesById(id);
        currentLocacoes.setDataInicio(dataInicio);
        currentLocacoes.setDataFim(dataFim);

        Locadores locador = locadoresService.findByLocacao(id);
        locador.locou(currentLocacoes);

        locadoresService.save(locador);
    }

    public void deleteLocacoes(Long id){
        Locadores locador = locadoresService.findByLocacao(id);
        locador.setLocacoes(locador.getLocacoes().stream().filter(locacoes -> !locacoes.getId().equals(id)).toList());

        locadoresService.save(locador);
    }

    public List<Locacoes> relatorioLocacoes(LocalDate dataInicio, LocalDate dataFim) {
        List<Locacoes> locacoes = listAllLocacoes();
        List<Locacoes> locacoesFiltradas = new ArrayList<>();

        for(Locacoes locacao : locacoes){
            if(locacao.getDataInicio().isAfter(dataInicio) && locacao.getDataInicio().isBefore(dataFim)){
                locacoesFiltradas.add(locacao);
            }
        }

        return locacoesFiltradas;
    }

}
