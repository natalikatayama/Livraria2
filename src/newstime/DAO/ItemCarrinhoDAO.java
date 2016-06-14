package newstime.DAO;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import newstime.entidade.Cliente;
import newstime.entidade.ItemPedido;
import newstime.excecao.BancoException;

/**
 * Classe de DAO para item do carrinho
 * @author Ian Melo
 */
public class ItemCarrinhoDAO implements DAO<ItemPedido> {
    /**
     * Banco de dados para conexão
     */
    private final BancoDados bd;
    /**
     * Leitor de resultados
     */
    private PreparedStatement pst;
    /**
     * Gatilho para comandos
     */
    private ResultSet rs;
    /**
     * Texto de Query SQL
     */
    private String sql;
    
    /**
     * Cria uma DAO de item do carrinho, com um banco de dados
     * @param bd Banco de dados a conectar
     */
    public ItemCarrinhoDAO(BancoDados bd) {
        this.bd = bd;
    }
    
    @Override
    public void inserir(ItemPedido o) throws BancoException {
        try {
            //Define String
            sql = "INSERT INTO ItemCarrinho VALUES (NULL,?,?,?,?,FALSE)";
            //Abre conexao e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Atrubui os dados
            pst.setInt(1,o.getID_CLIENTE());
            pst.setInt(2, o.getID_LIVRO());
            pst.setInt(3,o.getQuantidade());
            pst.setFloat(4,o.getSubtotal());
            //Executa
            pst.executeUpdate();
            bd.fecharConexao();
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao inserir o item do carrinho.");
        }
    }

    @Override
    public void alterar(ItemPedido o) throws BancoException {
        try {
            //Define String
            sql = "UPDATE ItemCarrinho SET Quantidade="+o.getQuantidade()+", Subtotal='"+o.getSubtotal()+"' " +
                "WHERE IdCarrinho="+o.getID_CARRINHO();
            //Abre banco e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Executa
            pst.executeUpdate();
            bd.fecharConexao();
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao alterar o item do carrinho.");
        }
    }

    @Override
    public void excluir(ItemPedido o) throws BancoException {
        try {
            //Define String
            sql = "UPDATE ItemCarrinho SET XDEAD = TRUE WHERE IdCarrinho="+o.getID_CARRINHO();
            //Abre banco e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Executa
            pst.executeUpdate();
            bd.fecharConexao();
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao excluir o item do carrinho.");
        }
    }

    @Override
    public ItemPedido buscar(ItemPedido o) throws BancoException {
        try {
            ItemPedido item = null;
            //Define String
            sql = "SELECT * FROM ItemCarrinho WHERE IdCliente=? AND CodLivro=? AND XDEAD=FALSE";
            //Abre banco e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Atribui os dados
            pst.setInt(1, o.getID_CLIENTE());
            pst.setInt(2, o.getID_LIVRO());
            //Executa e puxa a busca
            rs = pst.executeQuery();
            //Verifica se houve resultados e atribui valores ao objeto
            if(rs.next()){
                item = new ItemPedido();
                item.setID_CARRINHO(rs.getInt("IdCarrinho"));
                item.setID_CLIENTE(rs.getInt("IdCliente"));
                item.setID_LIVRO(rs.getInt("CodLivro"));
                item.setQuantidade(rs.getInt("Quantidade"));
                item.setSubtotal(rs.getInt("Subtotal"));
            }
            //Verifica se o objeto foi nulo e joga uma exceção, caso não foi encontrado
            if(item == null) {
                bd.fecharConexao();
                throw new BancoException("Item do carrinho não foi encontrado.");
            }
            //Prossegue procedimento, caso tenha encontrado
            bd.fecharConexao();
            return item;
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao buscar o item do carrinho.");
        }
    }
    
    @Override
    public ItemPedido buscarId(ItemPedido o) throws BancoException {
        try {
            ItemPedido item = null;
            //Define String
            sql = "SELECT * FROM ItemCarrinho WHERE IdCarrinho=? AND XDEAD=FALSE";
            //Abre banco e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Atribui os dados
            pst.setInt(1, o.getID_CARRINHO());
            //Executa e puxa a busca
            rs = pst.executeQuery();
            //Verifica se houve resultados e atribui valores ao objeto
            if(rs.next()){
                item = new ItemPedido();
                item.setID_CARRINHO(rs.getInt("IdCarrinho"));
                item.setID_CLIENTE(rs.getInt("IdCliente"));
                item.setID_LIVRO(rs.getInt("CodLivro"));
                item.setQuantidade(rs.getInt("Quantidade"));
                item.setSubtotal(rs.getInt("Subtotal"));
            }
            //Verifica se o objeto foi nulo e joga uma exceção, caso não foi encontrado
            if(item == null) {
                bd.fecharConexao();
                throw new BancoException("Item do carrinho não foi encontrado.");
            }
            //Prossegue procedimento, caso tenha encontrado
            bd.fecharConexao();
            return item;
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao buscar o item do pedido.");
        }
    }
    
    @Override
    public List<ItemPedido> listar() throws BancoException {
        try {
            ArrayList<ItemPedido> itens = new ArrayList<>();
            ItemPedido item;
            //Define String
            sql = "SELECT * FROM ItemCarrinho WHERE XDEAD=FALSE";
            //Abre banco e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Executa e puxa a busca
            rs = pst.executeQuery();
            //Verifica se houve resultados e atribui valores ao objeto
            while(rs.next()){
                item = new ItemPedido();
                item.setID_CARRINHO(rs.getInt("IdCarrinho"));
                item.setID_CLIENTE(rs.getInt("IdCliente"));
                item.setID_LIVRO(rs.getInt("CodLivro"));
                item.setQuantidade(rs.getInt("Quantidade"));
                item.setSubtotal(rs.getInt("Subtotal"));
                //Adiciona à lista
                itens.add(item);
            }
            bd.fecharConexao();
            return itens;
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao listar os itens do carrinho.");
        }
    }
    /**
     * Lista os itens do carrinho de acordo com o cliente
     * @param cliente Cliente do qual buscar os itens
     * @return Lista de itens do pedido definido
     * @throws BancoException Caso ocorra algum problema na listagem
     */
    public List<ItemPedido> listarCliente(Cliente cliente) throws BancoException {
        try {
            ArrayList<ItemPedido> itens = new ArrayList<>();
            ItemPedido item;
            //Define String
            sql = "SELECT * FROM ItemCarrinho WHERE IdCliente=? AND XDEAD=FALSE";
            //Abre banco e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Atribui os dados
            pst.setInt(1, cliente.getID());
            //Executa e puxa a busca
            rs = pst.executeQuery();
            //Verifica se houve resultados e atribui valores ao objeto
            while(rs.next()){
                item = new ItemPedido();
                item.setID_CARRINHO(rs.getInt("IdCarrinho"));
                item.setID_CLIENTE(rs.getInt("IdCliente"));
                item.setID_LIVRO(rs.getInt("CodLivro"));
                item.setQuantidade(rs.getInt("Quantidade"));
                item.setSubtotal(rs.getInt("Subtotal"));
                //Adiciona à lista
                itens.add(item);
            }
            bd.fecharConexao();
            return itens;
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao listar os itens do cliente.");
        }
    }
    /**
     * Excluir os itens do carrinho de acordo com o cliente
     * @param cliente Cliente do qual excluir os itens
     * @throws BancoException Caso ocorra algum problema na exclusão
     */
    public void excluirCliente(Cliente cliente) throws BancoException {
        try {
            //Define String
            sql = "UPDATE ItemCarrinho SET XDEAD = TRUE WHERE IdCliente="+cliente.getID();
            //Abre banco e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Executa
            pst.executeUpdate();
            bd.fecharConexao();
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao excluir os itens do cliente definido.");
        }
    }
}
