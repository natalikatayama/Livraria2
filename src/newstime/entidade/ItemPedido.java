package newstime.entidade;

import java.util.Objects;
import newstime.excecao.NegocioException;

/**
 * Classe de entidade que representa um item do pedido
 * @author Ian Melo
 */
public class ItemPedido {
    /**
     * Livro do item do pedido
     */
    private Livro livro;
    /**
     * Quantidade do item do pedido
     */
    private int quantidade;
    /**
     * Valor subtotal do item do pedido, gerado a partir do preço escolhido vezes a quantidade
     */
    private float subtotal;
    /**
     * Identificador do pedido do item pedido
     */
    private int ID_PEDIDO;
    /**
     * Identificador do livro do item pedido
     */
    private int ID_LIVRO;
    
    /**
     * Cria um item do pedido
     * @param livro Livro do item do pedido
     * @param quantidade Quantidade do item do pedido
     * @throws NegocioException Caso a quantidade não for positiva ou não houver quantidade suficiente em estoque do livro
     */
    public void definirItemPedido(Livro livro, int quantidade) throws NegocioException {
        //Define o livro
        this.setLivro(livro);
        //Caso não haja quantidade disponível joga exceção e interrompe
        if(!verificarQtdDisponivel(quantidade))
            throw new NegocioException("Não há quantidade em estoque disponível para este livro.");
        //Define a quantidade
        this.definirQuantidade(quantidade);
        //Verifica qual valor do livro será utilizado para definir o subtotal
        float preco_escolhido = verificarPreco();
        //Defina o subtotal (preco_escolhido * quantidade)
        this.setSubtotal(preco_escolhido * this.quantidade);
    }
    /**
     * Define a quantidade do item do pedido, através do processo de definição
     * <br/>Deve ser acima de 0
     * @param quantidade Quantidade do livro do item do pedido
     * @throws newstime.excecao.NegocioException Caso não siga a regra definida
     */
    private void definirQuantidade(int quantidade) throws NegocioException {
        if(this.validarQuantidade(quantidade))
            this.quantidade = quantidade;
        else
            throw new NegocioException("A quantidade do pedido deve ser maior que 0.");
    }
    /**
     * Retorna qual preço do livro é escolhido, podendo ser o de oferta ou o de venda
     * @return Preço do livro escolhido
     */
    private float verificarPreco() {
        if(this.livro.isOferta())
            return this.livro.getPrecoOferta();
        else
            return this.livro.getPrecoVenda();
    }
    /**
     * Verifica se há quantidade em estoque do livro disponível para definir quantidade do item do pedido
     * @return true, se há a quantidade disponível
     * false, caso contrário
     */
    private boolean verificarQtdDisponivel(int quantidade) {
        return (quantidade <= this.livro.getQtdEstoque());
    }

    //GETTERS SETTERS
    /**
     * Retorna o livro do item do pedido
     * @return Livro do item do pedido
     */
    public Livro getLivro() {
        return livro;
    }
    /**
     * Define o livro do item do pedido
     * @param livro Livro do item do pedido
     */
    public void setLivro(Livro livro) {
        this.livro = livro;
    }
    /**
     * Retorna a quantidade do item do pedido
     * @return Quantidade do livro do item do pedido
     */
    public int getQuantidade() {
        return quantidade;
    }
    /**
     * Define a quantidade do item do pedido
     * @param quantidade Quantidade do livro do item do pedido
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    /**
     * Retorna o valor subtotal do item do pedido, gerado a partir do preço escolhido vezes a quantidade
     * @return Valor subtotal do item do pedido
     */
    public float getSubtotal() {
        return subtotal;
    }
    /**
     * Define o valor subtotal do item do pedido, gerado a partir do preço escolhido vezes a quantidade
     * @param subtotal Subtotal do item do pedido
     */
    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }
    
    //IDENTIFICADORES
    /**
     * Retorna o identificador do pedido do item pedido
     * @return Identificador do pedido do item pedido
     */
    public int getID_PEDIDO() {
        return ID_PEDIDO;
    }
    /**
     * Define o identificador do pedido do item pedido
     * @param ID_PEDIDO Identificador do pedido do item pedido
     */
    public void setID_PEDIDO(int ID_PEDIDO) {
        this.ID_PEDIDO = ID_PEDIDO;
    }
    /**
     * Retorna o identificador do livro do item pedido
     * @return Identificador do livro do item pedido
     */
    public int getID_LIVRO() {
        return ID_LIVRO;
    }
    /**
     * Define o identificador do livro do item pedido
     * @param ID_LIVRO Identificador do livro do item pedido
     */
    public void setID_LIVRO(int ID_LIVRO) {
        this.ID_LIVRO = ID_LIVRO;
    }
    
    //VALIDADORES
    /**
     * Valida a quantidade de itens do pedido
     * @param quantidade Quantidade de itens do pedido
     * @return true, se a quantidade for acima de 0
     * <br/>false, caso contrário
     */
    private boolean validarQuantidade(int quantidade) {
        return (quantidade > 0);
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.livro);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ItemPedido other = (ItemPedido) obj;
        if (!Objects.equals(this.livro, other.livro)) {
            return false;
        }
        return true;
    }
}
