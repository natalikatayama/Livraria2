package newstime.entidade;

import java.util.ArrayList;
import newstime.excecao.NegocioException;

/**
 * Classe de entidade que representa o carrinho de compras
 * @author Ian Melo
 */
public class Carrinho {
    /**
     * Itens do carrinho de compras
     */
    private static final ArrayList<ItemPedido> itens = new ArrayList<>();
    
    /**
     * Adiciona o item ao carrinho de compras
     * @param item Item do carrinho de compras
     */
    public static void adicionarItem(ItemPedido item) {
        itens.add(item);
    }
    /**
     * Altera o item do carrinho de compras
     * @param item Item do carrinho de compras
     */
    public static void alterarItem(ItemPedido item) {
        try {
            //Busca a posição
            int p = itens.indexOf(item);
            //Altera o item
            itens.get(p).definirItemPedido(item.getLivro(), item.getQuantidade());
        } catch (NegocioException ex) { } //Não é necessário, uma vez que já foi definido
    }
    /**
     * Retira o item do carrinho de compras
     * @param item Item do carrinho de compras
     */
    public static void retirarItem(ItemPedido item) {
        itens.remove(item);
    }
    /**
     * Remove todos os itens do carrinho de compras
     */
    public static void limparCarrinho() {
        itens.clear();
    }
    
    //GETTERS SETTERS
    /**
     * Retorna os itens do carrinho de compras
     * @return Itens do carrinho de compras
     */
    public static ArrayList<ItemPedido> getItens() {
        return itens;
    }   
}