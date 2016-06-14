//TODO: Testar
package newstime.controle;

import java.util.ArrayList;
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
        ItemCarrinhoDAO icDao = new ItemCarrinhoDAO(bd);
        try {
            //Busca livro
            Livro livro = new Livro();
            livro.setIsbn(isbn);
            livro = lDao.buscar(livro);
            //Cria item de carrinho
            ItemPedido itemP = new ItemPedido();
            itemP.definirItemPedido(livro, Integer.parseInt(quantidade));
            
            //Adiciona no banco
            icDao.inserir(itemP);
            //Adiciona no programa
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
        ItemCarrinhoDAO icDao = new ItemCarrinhoDAO(new BancoDados());
        
        //Verifica se o cliente existe e para, caso não haja
        if(!this.verificarConta()) {
            JOptionPane.showMessageDialog(null, "Faça o login ou cadastre-se antes.");
            return;
        }
        
        //Busca
        ItemPedido escolhido = null;
        for(ItemPedido ix : Carrinho.getItens()) {
            if(ix.getLivro().getIsbn().equals(isbn)) {
                escolhido = ix;
                break;
            }
        }
        //Se encontrado
        if(escolhido != null) {
            try {
                escolhido.definirItemPedido(escolhido.getLivro(), Integer.parseInt(quantidade));
                //Altera item no banco
                icDao.alterar(escolhido);
                //Altera item no programa
                Carrinho.alterarItem(escolhido);
                JOptionPane.showMessageDialog(null,"Item alterado com sucesso.");
            } catch (NegocioException | BancoException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }
    /**
     * Retira um item do carrinho de compras
     * @param isbn 
     */
    public void retirarItem(String isbn) {
        ItemCarrinhoDAO icDao = new ItemCarrinhoDAO(new BancoDados());
        
        //Verifica se o cliente existe e para, caso não haja
        if(!this.verificarConta()) {
            JOptionPane.showMessageDialog(null, "Faça o login ou cadastre-se antes.");
            return;
        }
        
        //Busca
        ItemPedido escolhido = null;
        for(ItemPedido ix : Carrinho.getItens()) {
            if(ix.getLivro().getIsbn().equals(isbn)) {
                escolhido = ix;
                break;
            }
        }
        //Se encontrado
        if(escolhido != null) {
            try {
                //Retira o item no banco
                icDao.excluir(escolhido);
                //Retira o item no programa
                Carrinho.retirarItem(escolhido);
                JOptionPane.showMessageDialog(null,"Item removido com sucesso.");
            } catch (BancoException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
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
        
        try {
            //Limpa carrinho no banco
            ItemCarrinhoDAO icDao = new ItemCarrinhoDAO(new BancoDados());
            icDao.excluirCliente(Conta.getCliente());
            //Limpa carrinho no programa
            Carrinho.limparCarrinho();
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
