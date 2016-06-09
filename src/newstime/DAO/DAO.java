package newstime.DAO;

import java.util.List;
import newstime.excecao.BancoException;

/**
 * Interface DAO para comunicação com o banco de dados
 * @author Ian Melo
 * @param <E> Genérico
 */
public interface DAO<E> {
    /**
     * Insere um item no banco de dados
     * @param o Item a inserir
     * @throws BancoException Caso encontre algum erro ao inserir
     */
    public void inserir(E o) throws BancoException;
    /**
     * Altera um item no banco de dados
     * @param o Item a alterar
     * @throws BancoException Caso encontre algum erro ao alterar
     */
    public void alterar(E o) throws BancoException;
    /**
     * Exclui um item no banco de dados
     * @param o Item a excluir (necessário somente o identificador)
     * @throws BancoException Caso encontre algum erro ao excluir
     */
    public void excluir(E o) throws BancoException;
    /**
     * Busca por um item no banco de dados
     * @param o Item a buscar
     * @return Item de busca
     * @throws BancoException Caso encontre algum erro ao buscar
     */
    public E buscar(E o) throws BancoException;
    /**
     * Busca por um item no banco de dados a partir de seu identificador
     * @param o Item a buscar (necessário somente o identificador)
     * @return Item de busca
     * @throws BancoException Caso encontre algum erro ao buscar
     */
    public E buscarId(E o) throws BancoException;
    /**
     * Lista todos os itens de um grupo (tabela,etc.) do banco de dados
     * @return Lista com todos os itens do banco de dados
     * @throws BancoException Caso encontre algum erro ao listar
     */
    public List<E> listar() throws BancoException;
}
