package newstime.entidade;

import java.util.ArrayList;
import java.util.Date;
import newstime.excecao.NegocioException;

/**
 * Classe de entidade que representa o pedido de uma venda
 * @author Ian Melo
 */
public class Pedido {
    /**
     * Itens do pedido
     */
    private ArrayList<ItemPedido> itensPedido;
    /**
     * Cliente do pedido
     */
    private Cliente cliente;
    /**
     * Data e hora do pedido
     */
    private Date dataHora;
    /**
     * Identificador do pedido
     */
    private int ID;
    /**
     * Identificador do cliente do pedido
     */
    private int ID_CLIENTE;
    
    /**
     * Abre um pedido
     * <br/>É necessário estar logado para criar o pedido
     * @throws NegocioException Caso não siga a regra definida
     */
    public void abrirPedido() throws NegocioException {
        if(!this.verificarCliente())
            throw new NegocioException("É necessário estar logado para realizar um pedido.");
    }
    /**
     * Verifica se há um cliente logado
     * @return true, se houver cliente logado
     * <br/>false, caso contrário
     */
    private boolean verificarCliente() {
        return (Conta.getCliente() != null);
    }
    
    //GETTERS SETTERS
    /**
     * Retorna os itens do pedido
     * @return Itens do pedido
     */
    public ArrayList<ItemPedido> getItensPedido() {
        return itensPedido;
    }
    /**
     * Define os itens do pedido
     * @param itensPedido Itens do pedido
     */
    public void setItensPedido(ArrayList<ItemPedido> itensPedido) {
        this.itensPedido = itensPedido;
    }
    /**
     * Retorna o cliente do pedido
     * @return Cliente do pedido
     */
    public Cliente getCliente() {
        return cliente;
    }
    /**
     * Define o cliente do pedido
     * @param cliente Cliente do pedido
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    /**
     * Retorna a data e hora do pedido
     * @return Data e hora do pedido
     */
    public Date getDataHora() {
        return dataHora;
    }
    /**
     * Define a data e hora do pedido
     * @param dataHora Data e hora do pedido
     */
    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }
    
    //IDENTIFICADORES
    /**
     * Retorna o identificador do pedido
     * @return Identificador do pedido
     */
    public int getID() {
        return ID;
    }
    /**
     * Define o identificador do pedido
     * @param ID Identificador do pedido
     */
    public void setID(int ID) {
        this.ID = ID;
    }
    /**
     * Retorna o identificador do cliente do pedido
     * @return Identificador do cliente do pedido
     */
    public int getID_CLIENTE() {
        return ID_CLIENTE;
    }
    /**
     * Define o identificador do cliente do pedido
     * @param ID_CLIENTE Identificador do cliente do pedido
     */
    public void setID_CLIENTE(int ID_CLIENTE) {
        this.ID_CLIENTE = ID_CLIENTE;
    }
    
}
