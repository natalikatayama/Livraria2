package newstime.teste;

import java.util.ArrayList;
import newstime.entidade.*;
import newstime.excecao.BancoException;

/**
 * Classe de teste de busca de livros
 * @author Ian Melo
 */
public class TesteBuscaLivros {
    public static void main(String[] args) throws BancoException {
        BuscaLivro busca = new BuscaLivro();
        ArrayList<Livro> resultados = busca.buscarLivros("Insecta", BuscaLivro.CriterioBusca.TITULO);
        for(Livro x:resultados) {
            System.out.println(x.getTitulo());
            System.out.println(x.getAutor());
            System.out.println(x.getEditora());
        }
        resultados = busca.buscarLivros("Insecta", BuscaLivro.CriterioBusca.TITULO, BuscaLivro.OrganizacaoBusca.PRECO_ASC);
        for(Livro x:resultados) {
            System.out.println(x.getTitulo());
            System.out.println(x.getAutor());
            System.out.println(x.getEditora());
        }
    }
}
