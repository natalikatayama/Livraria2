package newstime.entidade;

import java.util.regex.Pattern;
import newstime.excecao.FormatacaoIncorretaException;

/**
 * Classe de entidade que representa uma editora
 * @author Ian Melo
 */
public class Editora {
    /**
     * CNPJ da editora
     */
    private String cnpj = "";
    /**
     * Nome da editora
     */
    private String nome = "";
    /**
     * Endereço físico da editora
     */
    private String endereco = "";
    /**
     * Telefone para contato da editora
     */
    private String telefone = "";
    /**
     * Identificador da editora
     */
    private int ID;
    
    //GETTERS SETTERS
    /**
     * Retorna o CNPJ da editora
     * <br/>Modelo: 00.000.000/0000-00
     * @return CNPJ da editora
     */
    public String getCnpj() {
        return cnpj;
    }
    /**
     * Define o CNPJ da editora
     * <br/>Modelo: 00.000.000/0000-00
     * @param cnpj CNPJ da editora
     * @throws newstime.excecao.FormatacaoIncorretaException Caso não siga o modelo definido
     */
    public void setCnpj(String cnpj) throws FormatacaoIncorretaException {
        if(this.validarCnpj(cnpj))
            this.cnpj = cnpj;
        else
            throw new FormatacaoIncorretaException("Modelo de CNPJ incorreto.");
    }
    /**
     * Retorna o nome da editora
     * @return Nome da editora
     */
    public String getNome() {
        return nome;
    }
    /**
     * Define o nome da editora
     * @param nome Nome da editora
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    /**
     * Retorna o endereço físico da editora
     * @return Endereço físico da editora
     */
    public String getEndereco() {
        return endereco;
    }
    /**
     * Define o endereço físico da editora
     * @param endereco Endereço físico da editora
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    /**
     * Retorna o telefone para contato da editora
     * <br/>Modelo: (00)0000-0000 ou (00)00000-0000
     * @return Telefone para contato da editora
     */
    public String getTelefone() {
        return telefone;
    }
    /**
     * Define o telefone para contato da editora
     * <br/>Modelo: (00)0000-0000 ou (00)00000-0000
     * @param telefone Telefone para contato da editora
     * @throws newstime.excecao.FormatacaoIncorretaException Caso não siga o modelo definido
     */
    public void setTelefone(String telefone) throws FormatacaoIncorretaException {
        if(this.validarTelefone(telefone))
            this.telefone = telefone;
        else
            throw new FormatacaoIncorretaException("Modelo de telefone incorreto.");
    }
    
    //IDENTIFICADORES
    /**
     * Retorna o identificador da editora
     * @return Identificador da editora
     */
    public int getID() {
        return ID;
    }
    /**
     * Define o identificador da editora
     * @param ID Identificador da editora
     */
    public void setID(int ID) {
        this.ID = ID;
    }
    
    //VALIDADORES
    /**
     * Valida o CNPJ da editora
     * @param cnpj CNPJ da editora
     * @return true, caso bata com o padrão definido para CNPJ
     * <br/>false, caso contrário
     */
    private boolean validarCnpj(String cnpj) {
        if(cnpj == null)
            cnpj = "";
        return Pattern.matches("(\\d{2}).(\\d{3}).(\\d{3})/(\\d{4})-(\\d{2})", cnpj);
    }
    /**
     * Valida o telefone da editora
     * @param telefone Telefone da editora
     * @return true, caso bata com o padrão definido para telefone
     * <br/>false, caso contrário
     */
    private boolean validarTelefone(String telefone) {
        if(telefone == null)
            telefone = "";
        return Pattern.matches("(\\(\\d{2}\\))(\\d{4,5})-(\\d{4})", telefone);
    }
}
