package newstime.entidade;

import newstime.excecao.NegocioException;

/**
 * Classe de entidade que representa o pagamento da venda
 * @author Ian Melo
 */
public class Pagamento {
    /**
     * Tipos de pagamento existentes
     */
    public static enum TipoPagamento {
        /**
         * Cartão de débito
         */
        DEBITO,
        /**
         * Cartão de crédito
         */
        CREDITO,
        /**
         * Boleto bancário
         */
        BOLETO
    }
    
    /**
     * Número máximo de parcelas
     */
    public static final int PARCELA_MAX = 6;
    
    /**
     * Número de parcelas restantes do pagamento
     */
    private int parcelaRestante;
    /**
     * Forma de pagamento do pagamento
     */
    private TipoPagamento forma;
    /**
     * Número de parcelas definidas do pagamento (1, se for à vista)
     */
    private int numParcelas;
    /**
     * Identificador do pagamento
     */
    private int ID;
    
    //GETTERS SETTERS
    /**
     * Retorna o número de parcelas restantes do pagamento
     * @return Número de parcelas restantes do pagamento
     */
    public int getParcelaRestante() {
        return parcelaRestante;
    }
    /**
     * Define o número de parcelas restantes do pagamento
     * @param parcelaRestante Número de parcelas restantes do pagamento
     */
    public void setParcelaRestante(int parcelaRestante) {
        this.parcelaRestante = parcelaRestante;
    }
    /**
     * Retorna a forma de pagamento do pagamento
     * @return Forma de pagamento do pagamento
     */
    public TipoPagamento getForma() {
        return forma;
    }
    /**
     * Define a forma de pagamento do pagamento
     * @param forma Forma de pagamento do pagamento
     */
    public void setForma(TipoPagamento forma) {
        this.forma = forma;
    }
    /**
     * Retorna o número de parcelas definidas do pagamento (1, se for à vista)
     * @return Número de parcelas definidas do pagamento
     */
    public int getNumParcelas() {
        return numParcelas;
    }
    /**
     * Define o número de parcelas definidas do pagamento (1, se for à vista)
     * <br/>Deve ser de 1 à 6
     * @param numParcelas Número de parcelas definidas do pagamento
     * @throws newstime.excecao.NegocioException
     */
    public void setNumParcelas(int numParcelas) throws NegocioException {
        if(this.validarNumParcelas(numParcelas))
            this.numParcelas = numParcelas;
        else throw new NegocioException("O número de parcelas deve ser de 1 (à vista) à " + PARCELA_MAX + ".");
    }
    
    //IDENTIFICADORES
    /**
     * Retorna o identificador do pagamento
     * @return Identificador do pagamento
     */
    public int getID() {
        return ID;
    }
    /**
     * Define o identificador do pagamento
     * @param ID Identificador do pagamento
     */
    public void setID(int ID) {
        this.ID = ID;
    }
    
    //VALIDADORES
    /**
     * Valida o número de parcelas definida pela
     * @param numParcelas Número de parcelas a verificar
     * @return true, caso esteja de acordo com a regra
     * <br/>false, caso contrário
     */
    private boolean validarNumParcelas(int numParcelas) {
        return (numParcelas >= 1 && numParcelas <= PARCELA_MAX);
    }
}
