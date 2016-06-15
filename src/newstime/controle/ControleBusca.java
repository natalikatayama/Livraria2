//TODO: Testar
package newstime.controle;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import newstime.entidade.BuscaLivro;
import newstime.entidade.Livro;
import newstime.excecao.BancoException;

/**
 * Classe de controle gerência da busca do livro
 * @author Ian Melo
 */
public class ControleBusca {
    /**
     * Lista com livros obtidos através de busca
     */
    private ArrayList<Livro> resultadosBusca = new ArrayList<>();
    
    /**
     * Retorna os resultados de busca de livros
     * @return Lista com livros obtidos através de busca
     */
    public ArrayList<Livro> getResultadosBusca() {
        return resultadosBusca;
    }
    /**
     * Vai enviar os parametros para buscar um livro atravez do objeto
     * BuscaLivro e retornar o livro se caso encontrado
     * <br/><br/>
     * São aceitos os seguintes critérios:<br/>
     * 0 = Filtros<br/>
     * 1 = Editora<br/>
     * 2 = Título<br/>
     * 3 = Autor<br/>
     * 4 = ISBN<br/>
     * 5 = Categoria<br/>
     *
     * @param palChave A palavra chave que será enviada
     * @param criterio O criterio da busca A forma que será organizado
     * @throws newstime.excecao.BancoException
     */
    public void fazerBusca(String palChave, int criterio) throws BancoException {
        BuscaLivro busca = new BuscaLivro();
        Livro l = new Livro();
        ArrayList<Livro> resultados = new ArrayList<>();
       
        switch(criterio) {
            case 1:
                resultados = busca.buscarLivros(palChave, (BuscaLivro.CriterioBusca.EDITORA));
                break;
            case 2:
                resultados = busca.buscarLivros(palChave, (BuscaLivro.CriterioBusca.TITULO));
                break;
            case 3:
                resultados = busca.buscarLivros(palChave, (BuscaLivro.CriterioBusca.AUTOR));
                break;
            case 4:
                resultados = busca.buscarISBN(palChave);
                break;
            case 5:
                resultados = busca.buscarLivros(palChave, (BuscaLivro.CriterioBusca.CATEGORIA));
                break;
            default:
                JOptionPane.showMessageDialog(null, "ERRO - Criterio de busca");
                break;
        }
        
        resultadosBusca = resultados;
    }

    /**
     * Organiza por ordem definida
     * <br/><br/>
     * São aceitos os seguintes critérios:<br/>
     * 0 = Filtros<br/>
     * 1 = Editora<br/>
     * 2 = Título<br/>
     * 3 = Autor<br/>
     * 4 = ISBN<br/>
     * 5 = Categoria
     * <br/><br/>
     * São aceitas as seguintes organizações:<br/>
     * 0 = Alfabético crescente<br/>
     * 1 = Alfabético decrescente<br/>
     * 2 = Por preço crescente<br/>
     * 3 = Por preço decrescente
     * 
     * @param palChave
     * @param criterio
     * @param organizacao
     * @throws newstime.excecao.BancoException
     */
    public void fazerBusca(String palChave, int criterio, int organizacao) throws BancoException {
        BuscaLivro busca = new BuscaLivro();
        Livro l = new Livro();
        ArrayList<Livro> resultados;

        if (criterio == 0) {
            if (organizacao == 0) {
                resultados = busca.buscarLivros(palChave, BuscaLivro.CriterioBusca.CATEGORIA, BuscaLivro.OrganizacaoBusca.ALFA_ASC);
            } else if (organizacao == 1) {
                resultados = busca.buscarLivros(palChave, BuscaLivro.CriterioBusca.CATEGORIA, BuscaLivro.OrganizacaoBusca.ALFA_DESC);
            } else if (organizacao == 2) {
                resultados = busca.buscarLivros(palChave, BuscaLivro.CriterioBusca.CATEGORIA, BuscaLivro.OrganizacaoBusca.PRECO_ASC);
            } else if (organizacao == 3) {
                resultados = busca.buscarLivros(palChave, BuscaLivro.CriterioBusca.CATEGORIA, BuscaLivro.OrganizacaoBusca.PRECO_DESC);
            } else {
                JOptionPane.showMessageDialog(null, "ERRO - Organização de busca");
                return;
            }

        } else if (criterio == 1) {
            if (organizacao == 0) {
                resultados = busca.buscarLivros(palChave, BuscaLivro.CriterioBusca.EDITORA, BuscaLivro.OrganizacaoBusca.ALFA_ASC);
            } else if (organizacao == 1) {
                resultados = busca.buscarLivros(palChave, BuscaLivro.CriterioBusca.EDITORA, BuscaLivro.OrganizacaoBusca.ALFA_DESC);
            } else if (organizacao == 2) {
                resultados = busca.buscarLivros(palChave, BuscaLivro.CriterioBusca.EDITORA, BuscaLivro.OrganizacaoBusca.PRECO_ASC);
            } else if (organizacao == 3) {
                resultados = busca.buscarLivros(palChave, BuscaLivro.CriterioBusca.EDITORA, BuscaLivro.OrganizacaoBusca.PRECO_DESC);
            } else {
                JOptionPane.showMessageDialog(null, "ERRO - Organização de busca");
                return;
            }
        } else if (criterio == 2) {
            if (organizacao == 0) {
                resultados = busca.buscarLivros(palChave, BuscaLivro.CriterioBusca.TITULO, BuscaLivro.OrganizacaoBusca.ALFA_ASC);
            } else if (organizacao == 1) {
                resultados = busca.buscarLivros(palChave, BuscaLivro.CriterioBusca.TITULO, BuscaLivro.OrganizacaoBusca.ALFA_DESC);
            } else if (organizacao == 2) {
                resultados = busca.buscarLivros(palChave, BuscaLivro.CriterioBusca.TITULO, BuscaLivro.OrganizacaoBusca.PRECO_ASC);
            } else if (organizacao == 3) {
                resultados = busca.buscarLivros(palChave, BuscaLivro.CriterioBusca.TITULO, BuscaLivro.OrganizacaoBusca.PRECO_DESC);
            } else {
                JOptionPane.showMessageDialog(null, "ERRO - Organização de busca");
                return;
            }
        } else if (criterio == 3) {
            if (organizacao == 0) {
                resultados = busca.buscarLivros(palChave, BuscaLivro.CriterioBusca.AUTOR, BuscaLivro.OrganizacaoBusca.ALFA_ASC);
            } else if (organizacao == 1) {
                resultados = busca.buscarLivros(palChave, BuscaLivro.CriterioBusca.AUTOR, BuscaLivro.OrganizacaoBusca.ALFA_DESC);
            } else if (organizacao == 2) {
                resultados = busca.buscarLivros(palChave, BuscaLivro.CriterioBusca.AUTOR, BuscaLivro.OrganizacaoBusca.PRECO_ASC);
            } else if (organizacao == 3) {
                resultados = busca.buscarLivros(palChave, BuscaLivro.CriterioBusca.AUTOR, BuscaLivro.OrganizacaoBusca.PRECO_DESC);
            } else {
                JOptionPane.showMessageDialog(null, "ERRO - Organização de busca");
                return;
            }
        } else {
            JOptionPane.showMessageDialog(null, "ERRO - Criterio de busca");
            return;
        }
        
        resultadosBusca = resultados;
    }
}
