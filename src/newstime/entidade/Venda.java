package newstime.entidade;

import newstime.excecao.NegocioException;

/**
 * Classe de entidade que representa a venda
 * @author Ian Melo
 */
public class Venda {
    /**
     * Tipos de situação de pagamento da venda
     */
    public static enum PagoVenda{
        /**
         * A venda já foi paga por completo; nenhuma parcela restante ou pago à vista
         */
        SIM,
        /**
         * Não foi realizado o pagamento inicial da venda (1ª parcela) ou o pagamento à vista
         */
        NAO,
        /**
         * Já foi paga uma ou mais parcelas da venda, mas ainda há parcelas a pagar
         */
        PAGANDO
    }
    /**
     * Tipos de status em que a venda pode se encontrar à respeito da entrega do produto e pagamento
     */
    public static enum StatusVenda{
        /**
         * Não foi feito o pagamento total nem parcial da venda
         */
        AGUARDO,
        /**
         * Não foi entregue o produto, mas já houve o pagamento total ou parcial
         */
        ANDAMENTO,
        /**
         * O produto foi entregue e já houve o pagamento total ou parcial
         */
        CONCLUIDO
    }
    
    /**
     * Pedido da venda
     */
    private Pedido pedido;
    /**
     * Endereço da venda
     */
    private Endereco endereco;
    /**
     * Situação de pagamento da venda
     */
    private PagoVenda pago;
    /**
     * Status da venda
     */
    private StatusVenda status;
    /**
     * Pagamento da venda
     */
    private Pagamento pagamento;
    /**
     * Entrega da venda
     */
    private Entrega entrega;
    /**
     * Identificador da venda
     */
    private int ID;
    /**
     * Identificador do pedido da venda
     */
    private int ID_PEDIDO;
    /**
     * Identificador do endereço da venda
     */
    private int ID_ENDERECO;
    /**
     * Identificador da entrega da venda
     */
    private int ID_ENTREGA;
    /**
     * Identificador do pagamento da venda
     */
    private int ID_PAGAMENTO;
    
    /**
     * Define o pedido da venda e status iniciais, através do processo de venda
     * @param pedido Pedido da venda
     */
    public void abrirVenda(Pedido pedido) {
        //Pedido
        this.setPedido(pedido);
        //Status de pago inicial
        this.setPago(PagoVenda.NAO);
        //Status de entrega inicial
        this.setStatus(StatusVenda.AGUARDO);
    }
    /**
     * Define o endereço da venda, através do processo de venda
     * @param endereco Endereço da venda
     */
    public void definirEndereco(Endereco endereco) {
        this.setEndereco(endereco);
    }
    /**
     * Define a entrega da venda, através do processo de venda
     * @param entrega Entrega da venda
     */
    public void definirEntrega(Entrega entrega) {
        this.setEntrega(entrega);
    }
    /**
     * Define o pagamento da venda, através do processo de venda
     * @param pagamento Pagamento da venda
     */
    public void definirPagamento(Pagamento pagamento) {
        this.setPagamento(pagamento);
    }
    /**
     * Conclui uma parcela do pagamento da venda, e verifica a situação de pago e de entrega
     * <br/>Só pode ser feito caso as parcelas a pagar não estejam zeradas
     * @throws newstime.excecao.NegocioException Caso não siga a regra definida
     */
    public void concluirParcela() throws NegocioException {
        //Verifica o número de parcelas, evitando o pagamento quando zerado
        if(this.getPagamento().getParcelaRestante() == 0)
            throw new NegocioException("Todas as parcelas desta venda já foram pagas.");
        //Reduz as parcelas restantes em -1
        this.getPagamento().setParcelaRestante(this.getPagamento().getParcelaRestante() - 1);
        //Verifica a situação de pago
        this.verificarPago();
        //Verifica o status de entrega
        this.verificarStatus();
    }
    /**
     * Conclui a entrega (altera para CONCLUIDO)
     * <br/>Só pode ser feito a partir do status de ANDAMENTO
     * @throws newstime.excecao.NegocioException Caso não siga a regra definida
     */
    public void concluirEntrega() throws NegocioException {
        if(this.status == StatusVenda.ANDAMENTO)
            this.setStatus(StatusVenda.CONCLUIDO);
        else
            throw new NegocioException("Só pode ser concluída a entrega a partir do estado de ANDAMENTO.");
    }
    /**
     * Verifica a situção de pago
     */
    private void verificarPago() {
        //Caso o número de parcelas restantes seja igual ao definido
        if(this.getPagamento().getParcelaRestante() == this.getPagamento().getNumParcelas())
            this.setPago(PagoVenda.NAO);
        //Caso o número de parcelas restantes seja menor que o definido e maior que ZERO
        else if(this.getPagamento().getParcelaRestante() < this.getPagamento().getNumParcelas() &&
                this.getPagamento().getParcelaRestante() > 0)
            this.setPago(PagoVenda.PAGANDO);
        //Caso o número de parcelas restantes seja ZERO
        else if(this.getPagamento().getParcelaRestante() == 0)
            this.setPago(PagoVenda.SIM);
    }
    /**
     * Verifica o status, alterando para ANDAMENTO se estiver em AGUARDO e com uma parcela concluída
     */
    private void verificarStatus() {
        //Altera para ANDAMENTO se estiver em AGUARDO e com uma parcela concluída
        if(this.status == StatusVenda.AGUARDO &&
            this.getPagamento().getParcelaRestante() < this.getPagamento().getNumParcelas())
            this.setStatus(StatusVenda.ANDAMENTO);
    }
    
    //GETTERS SETTERS
    /**
     * Retorna o pedido da venda
     * @return Pedido da venda
     */
    public Pedido getPedido() {
        return pedido;
    }
    /**
     * Define o pedido da venda
     * @param pedido Pedido da venda
     */
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    /**
     * Retorna o endereço da venda
     * @return Endereço da venda
     */
    public Endereco getEndereco() {
        return endereco;
    }
    /**
     * Define o endereço da venda
     * @param endereco Endereço da venda
     */
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    /**
     * Retorna a situação de pagamento da venda
     * @return Situação de pagamento da venda
     */
    public PagoVenda getPago() {
        return pago;
    }
    /**
     * Define a situação de pagamento da venda
     * @param pago Situação de pagamento da venda
     */
    public void setPago(PagoVenda pago) {
        this.pago = pago;
    }
    /**
     * Retorna o status da venda
     * @return Status da venda
     */
    public StatusVenda getStatus() {
        return status;
    }
    /**
     * Define o status da venda
     * @param status Status da venda
     */
    public void setStatus(StatusVenda status) {
        this.status = status;
    }
    /**
     * Retorna o pagamento da venda
     * @return Pagamento da venda
     */
    public Pagamento getPagamento() {
        return pagamento;
    }
    /**
     * Define o pagamento da venda
     * @param pagamento Pagamento da venda
     */
    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }
    /**
     * Retorna a entrega da venda
     * @return Entrega da venda
     */
    public Entrega getEntrega() {
        return entrega;
    }
    /**
     * Define a entrega da venda
     * @param entrega Entrega da venda
     */
    public void setEntrega(Entrega entrega) {
        this.entrega = entrega;
    }
    
    //IDENTIFICADORES
    /**
     * Retorna o identificador da venda
     * @return Identificador da venda
     */
    public int getID() {
        return ID;
    }
    /**
     * Define o identificador da venda
     * @param ID Identificador da venda
     */
    public void setID(int ID) {
        this.ID = ID;
    }
    /**
     * Retorna o identificador do pedido da venda
     * @return Identificador do pedido da venda
     */
    public int getID_PEDIDO() {
        return ID_PEDIDO;
    }
    /**
     * Define o identificador do pedido da venda
     * @param ID_PEDIDO Identificador do pedido da venda
     */
    public void setID_PEDIDO(int ID_PEDIDO) {
        this.ID_PEDIDO = ID_PEDIDO;
    }
    /**
     * Retorna o identificador do endereço da venda
     * @return Identificador do endereço da venda
     */
    public int getID_ENDERECO() {
        return ID_ENDERECO;
    }
    /**
     * Define o identificador do endereço da venda
     * @param ID_ENDERECO Identificador do endereço da venda
     */
    public void setID_ENDERECO(int ID_ENDERECO) {
        this.ID_ENDERECO = ID_ENDERECO;
    }
    /**
     * Retorna o identificador da entrega da venda
     * @return Identificador da entrega da venda
     */
    public int getID_ENTREGA() {
        return ID_ENTREGA;
    }
    /**
     * Define o identificador da entrega da venda
     * @param ID_ENTREGA Identificador da entrega da venda
     */
    public void setID_ENTREGA(int ID_ENTREGA) {
        this.ID_ENTREGA = ID_ENTREGA;
    }
    /**
     * Retorna o identificador do pagamento da venda
     * @return Identificador do pagamento da venda
     */
    public int getID_PAGAMENTO() {
        return ID_PAGAMENTO;
    }
    /**
     * Define o identificador do pagamento da venda
     * @param ID_PAGAMENTO Identificador do pagamento da venda
     */
    public void setID_PAGAMENTO(int ID_PAGAMENTO) {
        this.ID_PAGAMENTO = ID_PAGAMENTO;
    }
}