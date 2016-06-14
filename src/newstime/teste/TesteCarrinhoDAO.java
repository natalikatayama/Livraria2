package newstime.teste;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import newstime.DAO.AutorDAO;
import newstime.DAO.BancoDados;
import newstime.DAO.ClienteDAO;
import newstime.DAO.EditoraDAO;
import newstime.DAO.EnderecoDAO;
import newstime.DAO.ItemCarrinhoDAO;
import newstime.DAO.LivroDAO;
import newstime.entidade.*;
import newstime.entidade.Livro.CategoriaLivro;
import newstime.excecao.*;

/**
 * Classe de teste de item do carrinho
 * @author Ian Melo
 */
public class TesteCarrinhoDAO {
    public static void main(String[] args) {
        Endereco en = new Endereco();
        //Definição do endereço
        try {
            en.setLogradouro("Rua Martinez");
            en.setNumero("223");
            en.setComplemento("Apt. 27");
            en.setCep("01223-998");
            en.setBairro("Andrade");
            en.setCidade("São Roque");
            en.setEstado("RJ");
            en.setReferencia("Uma quadra antes do estádio");
        } catch (FormatacaoIncorretaException ex) {
            Logger.getLogger(TesteEndereco.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        Cliente c = new Cliente();
        //Define cliente
        try {    
            c.setEmail("joaomarques@gmail.com");
            c.setSenha("12345678");
            c.setNome("João");
            c.setSobrenome("Marques Dias");
            c.setSexo("M");
            c.setCpf("111.222.333-40");
            c.setDataNascimento(new Date(89,3,19));
            c.setTelefone("(11)4057-8866");
            c.setTelefoneAlt("(11)4056-1234");
            c.setCelular("(11)97022-1212");
            c.setEndereco(en);
        } catch (FormatacaoIncorretaException ex) {
            Logger.getLogger(TesteCliente.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        //Cadastra
        BancoDados banco = new BancoDados();
        ClienteDAO cDao = new ClienteDAO(banco);
        EnderecoDAO enDao = new EnderecoDAO(banco);
        
        try {
            enDao.inserir(en);
            en = enDao.buscar(en);
            
            c.setEndereco(en);
            cDao.inserir(c);
            c = cDao.buscar(c);
            
        } catch (BancoException ex) {
            Logger.getLogger(TesteClienteAdjDAO.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        //Loga
        Conta.setCliente(c);
        
        
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
            l.setFormato(Livro.FormatoLivro.BROCHURA);
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
            
            l = lDao.buscar(l);
            l.setAutor(a);
            l.setEditora(e);
            
        } catch (BancoException ex) {
            Logger.getLogger(TesteLivroAdjDAO.class.getName()).log(Level.SEVERE, null, ex);
            return;
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
        
        ItemCarrinhoDAO icDao = new ItemCarrinhoDAO(banco);
        try {
            //Inserção
            icDao.inserir(i);
            
            //Alteração
            i = icDao.buscar(i);
            icDao.alterar(i);
            
            //Exclusão OK
            //i = icDao.buscarId(i);
            //icDao.excluir(i);
            
            //Exclusão por cliente
            icDao.excluirCliente(Conta.getCliente());
        } catch (BancoException ex) {
            Logger.getLogger(TesteCarrinhoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
    }
}
