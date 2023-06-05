package com.ban2.biblioteca.menu;

import com.ban2.biblioteca.controller.AutorController;
import com.ban2.biblioteca.controller.CategoriaController;
import com.ban2.biblioteca.controller.EnderecosController;
import com.ban2.biblioteca.controller.LivrosController;
import com.ban2.biblioteca.controller.LocacoesController;
import com.ban2.biblioteca.controller.LocadoresController;
import com.ban2.biblioteca.controller.MainController;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class MenuUtils {
    Logger logger = LoggerFactory.getLogger(MenuUtils.class);
    private final AutorController autorController;
    private final CategoriaController categoriaController;
    private final EnderecosController enderecosController;
    private final LivrosController livrosController;
    private final LocadoresController locadoresController;
    private final LocacoesController locacoesController;
    private final Scanner scanner = new Scanner(System.in);
    public int menuOperacao() {
        logger.info("Informe o número da operação desejada : \n 1 - Adicionar Valor \n 2 - Editar valor \n 3 - Deletar valor \n 4 - Relatórios");
        return scanner.nextInt();
    }

    public int menuTabela() {
        logger.info("""
                Informe o número da tabela que deseja executar a operação :
                1 - Livros
                2 - Autores
                3 - Categorias
                4 - Locadores
                5 - Endereços
                6 - Locações""");
        return scanner.nextInt();
    }

    public int menuRelatorio() {
        logger.info("""
        Informe o número do relatório que deseja executar :
        1 - Selecionar todos os itens de uma tabela
        2 - Selecionar um único item de uma tabela
        3 - Relatório de livro por categoria e autor
        4 - Relatório de locações por mês
        5 - Relatório de locadores por endereço""");
        return scanner.nextInt();
    }

    public boolean menuPrincipal(){
        int op = menuOperacao();
        int relatorio = 0;

        if(op > 0 && op < 5) {
            if (op == 4) {
                relatorio = menuRelatorio();
            }
            if (relatorio < 3) {
                manipulaOperacao(relatorio > 0 ? op + --relatorio : op, menuTabela());
            } else if(relatorio < 6) {
                manipulaOperacaoRelatorio(relatorio);
            } else {
                logger.error("Operação não encontrada.");
            }
        }
        return (op > 0 && op < 5);
    }

    public void manipulaOperacao(int op, int tabela){
        MainController mainController;

        switch (tabela) {
            case 1 -> mainController = livrosController;
            case 2 -> mainController = autorController;
            case 3 -> mainController = categoriaController;
            case 4 -> mainController = locadoresController;
            case 5 -> mainController = enderecosController;
            case 6 -> mainController = locacoesController;
            default -> {
                logger.error("Tabela não encontrada");
                return;
            }
        }
        switch (op) {
            case 1 -> mainController.save();
            case 2 -> mainController.update();
            case 3 -> mainController.delete();
            case 4 -> {
                String response = mainController.listAll();
                logger.info(response);
            }
            case 5 -> {
                String response = mainController.findById();
                logger.info(response);
            }
            default -> logger.error("Operação não encontrada");
        }
    }

    public void manipulaOperacaoRelatorio(int id){
        String relatorio = "";
        switch (id){
            case 3 -> relatorio = livrosController.relatorio();
            case 4 -> relatorio = locacoesController.relatorio();
            case 5 -> relatorio = locadoresController.relatorio();
            default -> logger.error("Opção inválida");
        }

        logger.info(relatorio);
    }
}
