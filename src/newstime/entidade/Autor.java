package newstime.entidade;

import java.util.Date;

/**
 * Classe de entidade que representa o autor de um livro
 * @author Ian Melo
 */
public class Autor {
    /**
     * Código do autor
     */
    private String codigo = "";
    /**
     * Nome do autor
     */
    private String nome = "";
    /**
     * Data de nascimento do autor
     */
    private Date dataNasci;
    /**
     * Data de morte do autor (se houver)
     */
    private Date dataMorte;
    /**
     * Local de nascimento do autor
     */
    private String localNasci = "";
    /**
     * Local de morte do autor (se houver)
     */
    private String localMorte = "";
    /**
     * Identificador do autor
     */
    private int ID;
    
    //GETTERS SETTERS
    /**
     * Retorna o código do autor
     * @return Código do autor
     */
    public String getCodigo() {
        return codigo;
    }
    /**
     * Define o código do autor
     * @param codigo Código do autor
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    
    /**
     * Retorna o nome do autor
     * @return Nome do autor
     */
    public String getNome() {
        return nome;
    }
    /**
     * Define o nome do autor
     * @param nome Nome do autor
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    /**
     * Retorna a data de nascimento do autor
     * @return Data de nascimento do autor
     */
    public Date getDataNasci() {
        return dataNasci;
    }
    /**
     * Define a data de nascimento do autor
     * @param dataNasci Data de nascimento do autor
     */
    public void setDataNasci(Date dataNasci) {
        this.dataNasci = dataNasci;
    }
    /**
     * Retorna a data de morte do autor (se houver)
     * @return 
     */
    public Date getDataMorte() {
        return dataMorte;
    }
    /**
     * Define a data de morte do autor (se houver)
     * @param dataMorte Data de morte do autor
     */
    public void setDataMorte(Date dataMorte) {
        this.dataMorte = dataMorte;
    }
    /**
     * Retorna o local de nascimento do autor
     * @return Local de nascimento do autor
     */
    public String getLocalNasci() {
        return localNasci;
    }
    /**
     * Define o local de nascimento do autor
     * @param localNasci Local de nascimento do autor
     */
    public void setLocalNasci(String localNasci) {
        this.localNasci = localNasci;
    }
    /**
     * Retorna o local de morte do autor (se houver)
     * @return Local de morte do autor
     */
    public String getLocalMorte() {
        return localMorte;
    }
    /**
     * Define o local de morte do autor
     * @param localMorte Local de morte do autor
     */
    public void setLocalMorte(String localMorte) {
        this.localMorte = localMorte;
    }
    
    //IDENTIFICADORES
    /**
     * Retorna o identificador do autor
     * @return Identificador do autor
     */
    public int getID() {
        return ID;
    }
    /**
     * Define o identificador do autor
     * @param ID Identificador do autor
     */
    public void setID(int ID) {
        this.ID = ID;
    }
}
