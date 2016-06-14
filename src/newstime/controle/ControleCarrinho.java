//TODO: Implementar gravação do carrinho
package newstime.controle;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import newstime.DAO.BancoDados;
import newstime.DAO.ItemCarrinhoDAO;
import newstime.DAO.LivroDAO;
import newstime.entidade.ItemPedido;
import newstime.entidade.Livro;
import newstime.entidade.Carrinho;
import newstime.entidade.Conta;
import newstime.excecao.BancoException;
import newstime.excecao.NegocioException;

/**
 * Classe de controle da gerência do carrinho
 * @author Ian Melo
 */
public class ControleCarrinho {
    /**
     * Exibe todos os itens do carrinho
     * @return Todos os itens do carrinho
     */
    public ArrayList<ItemPedido> exibirCarrinho() {
        //Verifica se o cliente existe e para, caso não haja
        if(!this.verificarConta()) {
            JOptionPane.showMessageDialog(null, "Faça o login ou cadastre-se antes.");
            return null;
        }
        
        return Carrinho.getItens();
    }
    /**
     * Adiciona um item ao carrinho de compras
     * @param isbn
     * @param quantidade 
     */
    public void adicionarItem(String isbn, String quantidade) {
        //Verifica se o cliente existe e para, caso não haja
        if(!this.verificarConta()) {
            JOptionPane.showMessageDialog(null, "Faça o login ou cadastre-se antes.");
            return;
        }
        
        //Verifica se já existe algum item no carrinho; se já, altera e sai
        for(ItemPedido ix : Carrinho.getItens()) {
            if(ix.getLivro().getIsbn().equals(isbn)) {
                alterarItem(isbn,String.valueOf((Integer.parseInt(quantidade)+1)));
                return;
            }
        }
        
        //Prossegue o processo normal
        BancoDados bd = new BancoDados();
        LivroDAO lDao = new LivroDAO(bd);
        
        try {
            ItemPedido itemP = new ItemPedido();
            Livro livro = new Livro();
            livro.setIsbn(isbn);
            livro = lDao.buscar(livro);
            itemP.definirItemPedido(livro, Integer.parseInt(quantidade));
            Carrinho.adicionarItem(itemP);
            JOptionPane.showMessageDialog(null,"Item adicionado com sucesso.");
        } catch (NegocioException | BancoException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    /**
     * Altera o item do carrinho de compras
     * @param isbn
     * @param quantidade
     */
    public void alterarItem(String isbn, String quantidade) {
        //Verifica se o cliente existe e para, caso não haja
        if(!this.verificarConta()) {
            JOptionPane.showMessageDialog(null, "Faça o login ou cadastre-se antes.");
            return;
        }
        
        ItemPedido escolhido = null;
        for(ItemPedido ix : Carrinho.getItens()) {
            if(ix.getLivro().getIsbn().equals(isbn)) {
                escolhido = ix;
                break;
            }
        }
        if(escolhido != null) {
            try {
                escolhido.definirItemPedido(escolhido.getLivro(), Integer.parseInt(quantidade));
                Carrinho.alterarItem(escolhido);
                JOptionPane.showMessageDialog(null,"Item alterado com sucesso.");
            } catch (NegocioException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }
    /**
     * Retira um item do carrinho de compras
     * @param isbn 
     */
    public void retirarItem(String isbn) {
        //Verifica se o cliente existe e para, caso não haja
        if(!this.verificarConta()) {
            JOptionPane.showMessageDialog(null, "Faça o login ou cadastre-se antes.");
            return;
        }
        
        //Retira o item
        ItemPedido escolhido = null;
        for(ItemPedido ix : Carrinho.getItens()) {
            if(ix.getLivro().getIsbn().equals(isbn)) {
                escolhido = ix;
                break;
            }
        }
        if(escolhido != null) {
            Carrinho.retirarItem(escolhido);
            JOptionPane.showMessageDialog(null,"Item removido com sucesso.");
        }
    }
    /**
     * Limpa o carrinho de compras
     */
    public void limparCarrinho() {
        //Verifica se o cliente existe e para, caso não haja
        if(!this.verificarConta()) {
            JOptionPane.showMessageDialog(null, "Faça o login ou cadastre-se antes.");
            return;
        }
        
        //Limpa carrinho no programa
        Carrinho.limparCarrinho();
        //Limpa no banco
        ItemCarrinhoDAO icDao = new ItemCarrinhoDAO(new BancoDados());
        try {
            icDao.excluirCliente(Conta.getCliente());
        } catch (BancoException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
    }
    /**
     * Define o cliente do carrinho à partir da conta do cliente
     */
    public void definirCliente() {
        Carrinho.setCliente(Conta.getCliente());
    }
    /**
     * Verifica a conta do cliente, indicando se este está logado ou não
     * @return true, se estiver logado
     * <br/>false, caso contrário
     */
    private boolean verificarConta() {
        return (Carrinho.getCliente() != null);
    }
}
