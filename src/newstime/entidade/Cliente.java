package newstime.entidade;

import newstime.excecao.FormatacaoIncorretaException;
import java.util.Date;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Classe de entidade que representa o cliente da livraria
 * @author Ian Melo
 */
public class Cliente {
    /**
     * E-mail do cliente
     */
    private String email;
    /**
     * Senha do cliente
     */
    private String senha;
    /**
     * Nome do cliente
     */
    private String nome;
    /**
     * Sobrenome do cliente
     */
    private String sobrenome;
    /**
     * Sexo do cliente
     */
    private String sexo;
    /**
     * CPF do cliente
     */
    private String cpf;
    /**
     * Data de nascimento do cliente
     */
    private Date dataNascimento;
    /**
     * Telefone do cliente
     */
    private String telefone;
    /**
     * Telefone alternativo do cliente
     */
    private String telefoneAlt;
    /**
     * Celular do cliente
     */
    private String celular;
    /**
     * Endereço do cliente
     */
    private Endereco endereco;
    /**
     * Indica se o cliente que receber mensagens promocionais (ou não)
     */
    private boolean promocional;
    /**
     * Identificador do cliente
     */
    private int ID;
    /**
     * Identificdor do endereço do cliente
     */
    private int ID_ENDERECO;
    
    //GETTERS SETTERS
    /**
     * Retorna o e-mail do cliente
     * <br/>Modelo: nom_e@servidor.com
     * @return E-mail do cliente
     */
    public String getEmail() {
        return email;
    }
    /**
     * Define o e-mail do cliente
     * <br/>Modelo: nom_e@servidor.com
     * @param email E-mail do cliente
     * @throws newstime.excecao.FormatacaoIncorretaException Caso não siga o modelo
     */
    public void setEmail(String email) throws FormatacaoIncorretaException {
        if(this.validarEmail(email))
            this.email = email;
        else
            throw new FormatacaoIncorretaException("Modelo de e-mail incorreto.");
    }
    /**
     * Retorna a senha do cliente
     * <br/>Deve haver de 8 a 16 caracteres
     * @return Senha do Cliente
     */
    public String getSenha() {
        return senha;
    }
    /**
     * Define a senha do cliente
     * <br/>Deve haver de 8 a 16 caracteres
     * @param senha Senha do cliente
     * @throws FormatacaoIncorretaException Caso não siga o modelo
     */
    public void setSenha(String senha) throws FormatacaoIncorretaException {
        if(this.validarSenha(senha))
            this.senha = senha;
        else
            throw new FormatacaoIncorretaException("A senha deve ter entre 8 e 16 caracteres.");
    }
    /**
     * Retorna o nome do cliente
     * @return Nome do cliente
     */
    public String getNome() {
        return nome;
    }
    /**
     * Define o nome do cliente
     * @param nome Nome do cliente
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    /**
     * Retorna o sobrenome do cliente
     * @return Sobrenome do cliente
     */
    public String getSobrenome() {
        return sobrenome;
    }
    /**
     * Define o sobrenome do cliente
     * @param sobrenome Sobrenome do cliente
     */
    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
    /**
     * Retorna o sexo do cliente
     * @return Sexo do cliente
     */
    public String getSexo() {
        return sexo;
    }
    /**
     * Define o sexo do cliente
     * @param sexo Sexo do cliente
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    /**
     * Retorna o CPF do cliente
     * <br/>Modelo: 000.000.000/00
     * @return CPF do cliente
     */
    public String getCpf() {
        return cpf;
    }
    /**
     * Define o CPF do cliente
     * <br/>Modelo: 000.000.000/00
     * @param cpf CPF do cliente
     * @throws newstime.excecao.FormatacaoIncorretaException Caso não siga o modelo
     */
    public void setCpf(String cpf) throws FormatacaoIncorretaException {
        if(this.validarCpf(cpf))
            this.cpf = cpf;
        else
            throw new FormatacaoIncorretaException("Modelo de CPF incorreto.");
    }
    /**
     * Retorna a data de nascimento do cliente
     * @return Data de nascimento do cliente
     */
    public Date getDataNascimento() {
        return dataNascimento;
    }
    /**
     * Define a data de nascimento do cliente
     * @param dataNascimento Data de nascimento do cliente
     */
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    /**
     * Retorna o telefone do cliente
     * <br/>Modelo: (00)0000-0000 ou (00)00000-0000 ou (00)00000-000
     * @return Telefone do cliente
     */
    public String getTelefone() {
        return telefone;
    }
    /**
     * Define o telefone do cliente
     * <br/>Modelo: (00)0000-0000 ou (00)00000-0000 ou (00)00000-000
     * @param telefone Telefone do cliente
     * @throws newstime.excecao.FormatacaoIncorretaException Caso não siga o modelo
     */
    public void setTelefone(String telefone) throws FormatacaoIncorretaException {
        if(this.validarTelefone(telefone))
            this.telefone = telefone;
        else
            throw new FormatacaoIncorretaException("Modelo do telefone incorreto.");
    }
    /**
     * Retorna o telefone alternativo do cliente
     * <br/>Modelo: (00)0000-0000 ou (00)00000-0000
     * @return Telefone alternativo do cliente
     */
    public String getTelefoneAlt() {
        return telefoneAlt;
    }
    /**
     * Define o telefone alternativo do cliente
     * <br/>Modelo: (00)0000-0000 ou (00)00000-0000
     * @param telefoneAlt Telefone alternativo do cliente
     * @throws newstime.excecao.FormatacaoIncorretaException Caso não siga o modelo
     */
    public void setTelefoneAlt(String telefoneAlt) throws FormatacaoIncorretaException {
        if(this.validarTelefone(telefoneAlt))
            this.telefoneAlt = telefoneAlt;
        else
            throw new FormatacaoIncorretaException("Modelo do telefone alternativo incorreta.");
    }
    /**
     * Retorna o celular do cliente
     * <br/>Modelo: (00)0000-0000 ou (00)00000-0000
     * @return Celular do cliente
     */
    public String getCelular() {
        return celular;
    }
    /**
     * Define o celular do cliente
     * <br/>Modelo: (00)0000-0000 ou (00)00000-0000
     * @param celular Celular do cliente
     * @throws newstime.excecao.FormatacaoIncorretaException Caso não siga o modelo
     */
    public void setCelular(String celular) throws FormatacaoIncorretaException {
        if(this.validarTelefone(celular))
            this.celular = celular;
        else
            throw new FormatacaoIncorretaException("Modelo de celular incorreto.");
    }
    /**
     * Retorna o endereço do cliente
     * @return Endereço do cliente
     */
    public Endereco getEndereco() {
        return endereco;
    }
    /**
     * Define o endereço do cliente
     * @param endereco Endereço do cliente
     */
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    /**
     * Retorna se o cliente aceita mensagem promocional (ou não)
     * @return true, caso o cliente aceita mensagem promocional
     * <br/>false, caso contrário
     */
    public boolean isPromocional() {
        return promocional;
    }
    /**
     * Define se o cliente aceita mensagem promocional - true (ou não - false)
     * @param promocional Se o cliente aceita mensagem promocional - true (ou não - false)
     */
    public void setPromocional(boolean promocional) {
        this.promocional = promocional;
    }
    
    //IDENTIFICAORES
    /**
     * Retorna o identificador do cliente
     * @return Identificador do cliente
     */
    public int getID() {
        return ID;
    }
    /**
     * Define o identificador do cliente
     * @param ID Identificador do cliente
     */
    public void setID(int ID) {
        this.ID = ID;
    }
    /**
     * Retorna o identificador do endereço do cliente
     * @return Identificador do endereço do cliente
     */
    public int getID_ENDERECO() {
        return ID_ENDERECO;
    }
    /**
     * Define o identificador do endereço do cliente
     * @param ID_ENDERECO Identificador do endereço do cliente
     */
    public void setID_ENDERECO(int ID_ENDERECO) {
        this.ID_ENDERECO = ID_ENDERECO;
    }
    
    //VALIDADORES
    /**
     * Valida o CPF do cliente
     * @param cpf CPF do cliente
     * @return true, caso bata com o padrão definido para CPF
     * <br/>false, caso contrário
     */
    private boolean validarCpf(String cpf) {
        if(cpf == null)
            cpf = "";
        return Pattern.matches("(\\d{3}).(\\d{3}).(\\d{3})-([0-9X]{2})", cpf);
    }
    /**
     * Valida a senha do cliente
     * @param senha Senha do cliente
     * @return true, caso bata com o padrão definido para senha
     * <br/>false, caso contrário
     */
    private boolean validarSenha(String senha) {
        if(senha == null)
            senha = "";
        return (senha.length() >= 8 && senha.length() <= 16);
    }
    /**
     * Valida o e-mail do cliente
     * @param email E-mail do cliente
     * @return true, caso bata com o padrão definido para e-mail
     * <br/>false, caso contrário
     */
    private boolean validarEmail(String email) {
        if(email == null)
            email = "";
        return Pattern.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", email);
    }
    /**
     * Valida o telefone do cliente
     * @param telefone Telefone qualquer do cliente
     * @return true, caso bata com o padrão definido para telefone
     * <br/>false, caso contrário
     */
    private boolean validarTelefone(String telefone) {
        if(telefone == null)
            telefone = "";
        return Pattern.matches("(\\(\\d{2}\\))(\\d{4,5})-(\\d{3,4})", telefone);
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.email);
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
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }
}
