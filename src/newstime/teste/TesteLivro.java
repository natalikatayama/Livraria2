package newstime.teste;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import newstime.entidade.*;
import newstime.entidade.Livro.CategoriaLivro;
import newstime.excecao.*;

/**
 * Classe de teste de livro
 * @author Ian Melo
 */
public class TesteLivro {
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
            l.setOferta(false);
        } catch (NegocioException ex) {
            Logger.getLogger(TesteLivro.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Exibição do livro
        System.out.println(l.getIsbn());
        System.out.println(l.getTitulo());
        System.out.println(l.getResumo());
        System.out.println(l.getSumario());
        System.out.println(l.getAnoPublicacao());
        System.out.println(l.getCategoria());
        System.out.println(l.getMargemLucro());
        System.out.println(l.getPrecoCusto());
        System.out.println(l.getPrecoOferta());
        System.out.println(l.getPrecoVenda());
        System.out.println(l.getQtdEstoque());
        System.out.println(l.isDigital());
        System.out.println(l.isOferta());
        
        //Exibição do autor do livro
        System.out.println(l.getAutor().getDataMorte());
        System.out.println(l.getAutor().getDataNasci());
        System.out.println(l.getAutor().getLocalMorte());
        System.out.println(l.getAutor().getLocalNasci());
        
        //Exibição da editora do livro
        System.out.println(l.getEditora().getCnpj());
        System.out.println(l.getEditora().getEndereco());
        System.out.println(l.getEditora().getNome());
        System.out.println(l.getEditora().getTelefone());
    }
}
