package newstime.teste;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import newstime.entidade.*;
import newstime.entidade.Livro.CategoriaLivro;
import newstime.excecao.*;

/**
 * Classe de teste de item do carrinho/pedido
 * @author Ian Melo
 */
public class TesteItem {
    public static void main(String[] args) {
        Autor a = new Autor();
        //Definição do autor
        a.setNome("Floriano Marquendes");
        a.setDataNasci(new Date(1920 - 1900,11,21)); //O Date começa a contar à partir de 1900, portanto sendo necessário a subtração desse valor
        a.setDataMorte(new Date(1997 - 1900,03,15)); //Ex.: 1920 --> 20; 1997 --> 97; 2005 --> 105
        a.setLocalNasci("São Pedro, MG");
        a.setLocalMorte("Rio de Janeiro, RJ");
        
        Editora e = new Editora();
        //Definição da editora
        try {
            e.setCnpj("12.123.123/1234-56");
            e.setEndereco("R. dos Lençóis, 142, São Beto, São Paulo, SP");
            e.setNome("Editora Manzollini");
            e.setTelefone("(11)1234-1234");
        } catch (FormatacaoIncorretaException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(TesteEditora.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Livro l = new Livro();
        //Definição do livro
        try {
            l.setAutor(a);
            l.setEditora(e);
            l.setIsbn("1234567890123");
            l.setTitulo("A Classe Insecta");
            l.setResumo("Aqui vai o resumo...");
            l.setSumario("Aqui vai o sumário...");
            l.setAnoPublicacao(1982);
            l.setCategoria(CategoriaLivro.CIENCIAS_BIO);
            l.setMargemLucro(25.0f);
            l.setPrecoCusto(25.0f);
            l.setPrecoVenda(50.0f);
            l.setPrecoOferta(35.0f);
            l.setQtdEstoque(500);
            l.setDigital(false);
            l.setOferta(true);
        } catch (NegocioException ex) {
            Logger.getLogger(TesteLivro.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ItemPedido i = new ItemPedido();
        //Definição do item
        try {
            i.definirItemPedido(l, 20);
        } catch (NegocioException ex) {
            Logger.getLogger(TesteItem.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
        //Exibição do item
        System.out.println(i.getQuantidade());
        System.out.println(i.getSubtotal());
        
        //Exibição do livro
        System.out.println(i.getLivro().getIsbn());
        System.out.println(i.getLivro().getTitulo());
        System.out.println(i.getLivro().getResumo());
        System.out.println(i.getLivro().getSumario());
        System.out.println(i.getLivro().getAnoPublicacao());
        System.out.println(i.getLivro().getCategoria());
        System.out.println(i.getLivro().getMargemLucro());
        System.out.println(i.getLivro().getPrecoCusto());
        System.out.println(i.getLivro().getPrecoOferta());
        System.out.println(i.getLivro().getPrecoVenda());
        System.out.println(i.getLivro().getQtdEstoque());
        System.out.println(i.getLivro().isDigital());
        System.out.println(i.getLivro().isOferta());
        
        //Exibição do autor do livro
        System.out.println(i.getLivro().getAutor().getDataMorte());
        System.out.println(i.getLivro().getAutor().getDataNasci());
        System.out.println(i.getLivro().getAutor().getLocalMorte());
        System.out.println(i.getLivro().getAutor().getLocalNasci());
        
        //Exibição da editora do livro
        System.out.println(i.getLivro().getEditora().getCnpj());
        System.out.println(i.getLivro().getEditora().getEndereco());
        System.out.println(i.getLivro().getEditora().getNome());
        System.out.println(i.getLivro().getEditora().getTelefone());
    }
}
