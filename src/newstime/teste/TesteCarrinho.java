package newstime.teste;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import newstime.entidade.*;
import newstime.excecao.*;

/**
 * Classe de teste de carrinho de compras
 * @author Ian Melo
 */
public class TesteCarrinho {
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
            l.setCategoria(Livro.CategoriaLivro.CIENCIAS_BIO);
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
        ItemPedido i2 = new ItemPedido();
        ItemPedido i3 = new ItemPedido();
        //Definição do item
        try {
            i.definirItemPedido(l, 20);
            i2.definirItemPedido(l, 5);
            i3.definirItemPedido(l, 22);
        } catch (NegocioException ex) {
            Logger.getLogger(TesteItem.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
        //Adição de itens ao carrinho
        Carrinho.adicionarItem(i);
        Carrinho.adicionarItem(i2);
        Carrinho.adicionarItem(i3);
        
        //Exibição de quantidade dos itens do carrinho
        System.out.println("Exibição 1");
        for(ItemPedido x: Carrinho.getItens())
            System.out.println(x.getQuantidade());
        
        //Mudança do item 1
        try {
            i.definirItemPedido(l, 10);
        } catch (NegocioException ex) {
            Logger.getLogger(TesteCarrinho.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Alteração do carrinho
        Carrinho.alterarItem(i);
        
        //Exibição de quantidade dos itens do carrinho
        System.out.println("Exibição 2");
        for(ItemPedido x: Carrinho.getItens())
            System.out.println(x.getQuantidade());
        
        //Remoção do item 2 do carrinho
        Carrinho.retirarItem(i2);
        
        //Exibição de quantidade dos itens do carrinho
        System.out.println("Exibição 3");
        for(ItemPedido x: Carrinho.getItens())
            System.out.println(x.getQuantidade());
        
        //Remoção total
        Carrinho.limparCarrinho();
        
        //Exibição de quantidade dos itens do carrinho
        System.out.println("Exibição 4");
        for(ItemPedido x: Carrinho.getItens())
            System.out.println(x.getQuantidade());
    }
}
