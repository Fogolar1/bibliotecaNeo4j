package com.ban2.biblioteca.service;

import com.ban2.biblioteca.node.Locacoes;
import com.ban2.biblioteca.repository.LocacoesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LocacoesService {
    private final LocacoesRepository locacoesRepository;
    private final LocadoresService locadoresService;
    private final LivrosService livrosService;

    public List<Locacoes> listAllLocacoes(){
        return locacoesRepository.findAll();
    }

    public Locacoes findLocacoesById(Long id){
        return locacoesRepository.findById(id).orElseThrow(() -> new RuntimeException("Locacoes não encontrado"));
    }

    public Locacoes saveLocacoes(Locacoes locacoes, Long idLivro,Long idLocador){
        locacoes.locado(livrosService.findLivrosById(idLivro));
        locacoes.locadoPor(locadoresService.findLocadoresById(idLocador));

        return locacoesRepository.save(locacoes);
    }

    public Locacoes updateLocacoes(Long id, Locacoes locacoesToSave)  {
        Locacoes currentLocacoes = findLocacoesById(id);
        currentLocacoes.setDataInicio(locacoesToSave.getDataInicio());
        currentLocacoes.setDataFim(locacoesToSave.getDataFim());

        return locacoesRepository.save(currentLocacoes);
    }

    public void deleteLocacoes(Long id){
        locacoesRepository.deleteById(id);
    }

    public List<Locacoes> relatorioLocacoes(LocalDate dataInicio, LocalDate dataFim) {
        return locacoesRepository.findAllByDataInicioIsBetween(dataInicio, dataFim);
    }

}
