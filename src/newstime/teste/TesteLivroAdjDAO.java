package newstime.teste;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import newstime.DAO.*;
import newstime.entidade.*;
import newstime.excecao.*;

/**
 * Classe de teste de DAO de Livros e adjacentes
 * @author Ian Melo
 */
public class TesteLivroAdjDAO {
    public static void main(String[] args) {
        Autor a = new Autor();
        Editora e = new Editora();
        Livro l = new Livro();
        ArrayList<Autor> autores = null;
        ArrayList<Editora> editoras = null;
        ArrayList<Livro> livros = null;
        
        //Definição do autor
        a.setNome("Floriano Marquendes");
        a.setDataNasci(new Date(1920 - 1900,11,21)); //O Date começa a contar à partir de 1900, portanto sendo necessário a subtração desse valor
        a.setDataMorte(new Date(1997 - 1900,03,15)); //Ex.: 1920 --> 20; 1997 --> 97; 2005 --> 105
        a.setLocalNasci("São Pedro, MG");
        a.setLocalMorte("Rio de Janeiro, RJ");
        
        //Definição da editora
        try {
            e.setCnpj("12.123.123/1234-56");
            e.setEndereco("R. dos Lençóis, 142, São Beto, São Paulo, SP");
            e.setNome("Editora Manzollini");
            e.setTelefone("(11)1234-1234");
        } catch (FormatacaoIncorretaException ex) {
            System.out.println(ex.getMessage());
            return;
        }
        
        //Definição do livro
        try {
            l.setAutor(a);
            l.setEditora(e);
            l.setIsbn("1234567890123");
            l.setTitulo("A Classe Insecta");
            l.setResumo("Aqui vai o resumo...");
            l.setSumario("Aqui vai o sumário...");
            l.setAnoPublicacao(1982);
            l.setCategoria(Livro.CategoriaLivro.CIENCIAS_BIO);
            l.setMargemLucro(25.0f);
            l.setPrecoCusto(25.0f);
            l.setPrecoVenda(50.0f);
            l.setPrecoOferta(35.0f);
            l.setQtdEstoque(500);
            l.setDigital(false);
            l.setOferta(false);
        } catch (NegocioException ex) {
            System.out.println(ex.getMessage());
            return;
        }
        
        BancoDados banco = new BancoDados();
        LivroDAO lDao = new LivroDAO(banco);
        AutorDAO aDao = new AutorDAO(banco);
        EditoraDAO eDao = new EditoraDAO(banco);
        
        try {
            aDao.inserir(a);
            a = aDao.buscar(a);
            eDao.inserir(e);
            e = eDao.buscar(e);
            
            l.setAutor(a);
            l.setEditora(e);
            
            lDao.inserir(l);
            
            livros = (ArrayList<Livro>) lDao.listar();
            autores = (ArrayList<Autor>) aDao.listar();
            editoras = (ArrayList<Editora>) eDao.listar();
            
            System.out.println("\nInserido");
        } catch (BancoException ex) {
            Logger.getLogger(TesteLivroAdjDAO.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
        for(Livro x : livros) {
            //Exibição do livro
            System.out.println("-----------------");
            System.out.println(x.getIsbn());
            System.out.println(x.getTitulo());
            System.out.println(x.getResumo());
            System.out.println(x.getSumario());
            System.out.println(x.getAnoPublicacao());
            System.out.println(x.getCategoria());
            System.out.println(x.getMargemLucro());
            System.out.println(x.getPrecoCusto());
            System.out.println(x.getPrecoOferta());
            System.out.println(x.getPrecoVenda());
            System.out.println(x.getQtdEstoque());
            System.out.println(x.isDigital());
            System.out.println(x.isOferta());
        }
        
        for(Autor x : autores) {
            //Exibição do autor do livro
            System.out.println("-----------------");
            System.out.println(x.getDataMorte());
            System.out.println(x.getDataNasci());
            System.out.println(x.getLocalMorte());
            System.out.println(x.getLocalNasci());
        }
        
        for(Editora x : editoras) {
            //Exibição da editora do livro
            System.out.println("-----------------");
            System.out.println(x.getCnpj());
            System.out.println(x.getEndereco());
            System.out.println(x.getNome());
            System.out.println(x.getTelefone());
        }
        
        try {
            aDao.alterar(a);
            eDao.alterar(e);
            
            l.setAutor(a);
            l.setEditora(e);
            
            lDao.alterar(l);
            
            livros = (ArrayList<Livro>) lDao.listar();
            autores = (ArrayList<Autor>) aDao.listar();
            editoras = (ArrayList<Editora>) eDao.listar();
            
            System.out.println("\nAlterado");
        } catch (BancoException ex) {
            Logger.getLogger(TesteLivroAdjDAO.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
        for(Livro x : livros) {
            //Exibição do livro
            System.out.println("-----------------");
            System.out.println(x.getIsbn());
            System.out.println(x.getTitulo());
            System.out.println(x.getResumo());
            System.out.println(x.getSumario());
            System.out.println(x.getAnoPublicacao());
            System.out.println(x.getCategoria());
            System.out.println(x.getMargemLucro());
            System.out.println(x.getPrecoCusto());
            System.out.println(x.getPrecoOferta());
            System.out.println(x.getPrecoVenda());
            System.out.println(x.getQtdEstoque());
            System.out.println(x.isDigital());
            System.out.println(x.isOferta());
        }
        
        for(Autor x : autores) {
            //Exibição do autor do livro
            System.out.println("-----------------");
            System.out.println(x.getDataMorte());
            System.out.println(x.getDataNasci());
            System.out.println(x.getLocalMorte());
            System.out.println(x.getLocalNasci());
        }
        
        for(Editora x : editoras) {
            //Exibição da editora do livro
            System.out.println("-----------------");
            System.out.println(x.getCnpj());
            System.out.println(x.getEndereco());
            System.out.println(x.getNome());
            System.out.println(x.getTelefone());
        }
        
        try {
            Autor ara = new Autor();
            ara.setID(1);
            Editora era = new Editora();
            era.setID(1);
            Livro lira = new Livro();
            lira.setID(1);
            
            aDao.excluir(ara);
            eDao.excluir(era);
            
            lDao.excluir(lira);
            
            livros = (ArrayList<Livro>) lDao.listar();
            autores = (ArrayList<Autor>) aDao.listar();
            editoras = (ArrayList<Editora>) eDao.listar();
            
            System.out.println("\nExcluido");
        } catch (BancoException ex) {
            Logger.getLogger(TesteLivroAdjDAO.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
        for(Livro x : livros) {
            //Exibição do livro
            System.out.println("-----------------");
            System.out.println(x.getIsbn());
            System.out.println(x.getTitulo());
            System.out.println(x.getResumo());
            System.out.println(x.getSumario());
            System.out.println(x.getAnoPublicacao());
            System.out.println(x.getCategoria());
            System.out.println(x.getMargemLucro());
            System.out.println(x.getPrecoCusto());
            System.out.println(x.getPrecoOferta());
            System.out.println(x.getPrecoVenda());
            System.out.println(x.getQtdEstoque());
            System.out.println(x.isDigital());
            System.out.println(x.isOferta());
        }
        
        for(Autor x : autores) {
            //Exibição do autor do livro
            System.out.println("-----------------");
            System.out.println(x.getDataMorte());
            System.out.println(x.getDataNasci());
            System.out.println(x.getLocalMorte());
            System.out.println(x.getLocalNasci());
        }
        
        for(Editora x : editoras) {
            //Exibição da editora do livro
            System.out.println("-----------------");
            System.out.println(x.getCnpj());
            System.out.println(x.getEndereco());
            System.out.println(x.getNome());
            System.out.println(x.getTelefone());
        }
    }
}
