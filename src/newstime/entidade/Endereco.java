package newstime.entidade;

import java.util.regex.Pattern;
import newstime.excecao.FormatacaoIncorretaException;

/**
 * Classe de entidade que representa o endereço
 * @author Ian Melo
 */
public class Endereco {
    /**
     * Logradouro do endereço
     */
    private String logradouro = "";
    /**
     * Número do endereço
     */
    private String numero = "";
    /**
     * Complemento do endereço
     */
    private String complemento = "";
    /**
     * Bairro do endereço
     */
    private String bairro = "";
    /**
     * Cidade do endereço
     */
    private String cidade = "";
    /**
     * Estado do endereço
     */
    private String estado = "";
    /**
     * CEP do endereço
     */
    private String cep = "";
    /**
     * Ponto de referência do endereço
     */
    private String referencia = "";
    /**
     * Identificador do endereço
     */
    private int ID;
    
    //GETTERS SETTERS
    /**
     * Retorna o logradouro do endereço
     * @return Logradouro do endereço
     */
    public String getLogradouro() {
        return logradouro;
    }
    /**
     * Define o logradouro do endereço
     * @param logradouro Logradouro do endereço
     */
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }
    /**
     * Retorna o número do endereço
     * @return Número do endereço
     */
    public String getNumero() {
        return numero;
    }
    /**
     * Define o número do endereço
     * @param numero Número do endereço
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }
    /**
     * Retorna o complemento do endereço
     * @return Complemento do endereço
     */
    public String getComplemento() {
        return complemento;
    }
    /**
     * Define o complemento do endereço
     * @param complemento Complemento do endereço
     */
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
    /**
     * Retorna o bairro do endereço
     * @return Bairro do endereço
     */
    public String getBairro() {
        return bairro;
    }
    /**
     * Define o bairro do endereço
     * @param bairro Bairro do endereço
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    /**
     * Retorna a cidade do endereço
     * @return Cidade do endereço
     */
    public String getCidade() {
        return cidade;
    }
    /**
     * Define a cidade do endereço
     * @param cidade Cidade do endereço
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    /**
     * Retorna o estado do endereço
     * <br/>Modelo: AA
     * @return Estado do endereço
     */
    public String getEstado() {
        return estado;
    }
    /**
     * Define o estado do endereço
     * <br/>Modelo: AA
     * @param estado Estado do endereço
     * @throws newstime.excecao.FormatacaoIncorretaException Caso não siga o modelo definido
     */
    public void setEstado(String estado) throws FormatacaoIncorretaException {
        if(this.validarEstado(estado))
            this.estado = estado;
        else
            throw new FormatacaoIncorretaException("Modelo de estado incorreto.");
    }
    /**
     * Retorna o CEP do endereço
     * <br/>Modelo 00000-000
     * @return CEP do endereço
     */
    public String getCep() {
        return cep;
    }
    /**
     * Define o CEP do endereço
     * <br/>Modelo 00000-000
     * @param cep CEP do endereço
     * @throws newstime.excecao.FormatacaoIncorretaException Caso não siga o modelo definido
     */
    public void setCep(String cep) throws FormatacaoIncorretaException {
        if(this.validarCep(cep))
            this.cep = cep;
        else
            throw new FormatacaoIncorretaException("Modelo de CEP incorreto.");
    }
    /**
     * Retorna o ponto de referência do endereço
     * @return Ponto de referência do endereço
     */
    public String getReferencia() {
        return referencia;
    }
    /**
     * Define o ponto de referência do endereço
     * @param referencia Ponto de referência do endereço
     */
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
    
    //IDENTIFICADORES
    /**
     * Retorna o identificador do endereço
     * @return Identificador do endereço
     */
    public int getID() {
        return ID;
    }
    /**
     * Define o identificador do endereço
     * @param ID Identificador do endereço
     */
    public void setID(int ID) {
        this.ID = ID;
    }
    
    //VALIDADORES
    /**
     * Valida o CEP do endereço
     * @param cep CEP do endereço
     * @return true, caso bata com o padrão definido para CEP
     * <br/>false, caso contrário
     */
    private boolean validarCep(String cep) {
        if(cep == null)
            cep = "";
        return Pattern.matches("(\\d{5})-(\\d{3})", cep);
    }
    /**
     * Valida o estado do endereço
     * @param estado Estado do endereço
     * @return true, caso bata com o padrão definido para endereço
     * <br/>false, caso contrário
     */
    private boolean validarEstado(String estado) {
        if(estado == null)
            estado = "";
        return Pattern.matches("([A-Z]{2})", estado);
    }
}
