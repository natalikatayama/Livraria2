package newstime.entidade;

/**
 * Classe de entidade que representa o funcionário da livraria
 * @author Ian Melo
 */
public class Funcionario {
    /**
     * Nome do funcionário
     */
    private String nome = "";
    /**
     * Nome de login do funcionário
     */
    private String login = "";
    /**
     * Senha do funcionário
     */
    private String senha = "";
    /**
     * Identificador do funcionário
     */
    private int ID;
    
    //GETTERS SETTERS
    /**
     * Retorna o nome do funcionário
     * @return Nome do funcionário
     */
    public String getNome() {
        return nome;
    }
    /**
     * Define o nome do funcionário
     * @param nome Nome do funcionário
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    /**
     * Retorna o nome de login do funcionário
     * @return Nome de login do funcionário
     */
    public String getLogin() {
        return login;
    }
    /**
     * Define o nome de login do funcionário
     * @param login Nome de login do funcionário
     */
    public void setLogin(String login) {
        this.login = login;
    }
    /**
     * Retorna a senha do funcionário
     * @return Senha do funcionário
     */
    public String getSenha() {
        return senha;
    }
    /**
     * Define a senha do funcionário
     * @param senha Senha do funcionário
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    //IDENTIFICADORES
    /**
     * Retorna o identificador do funcionário
     * @return Identificador do funcionário
     */
    public int getID() {
        return ID;
    }
    /**
     * Define o identificador do funcionário
     * @param ID Identificador do funcionário
     */
    public void setID(int ID) {
        this.ID = ID;
    }
    
}
