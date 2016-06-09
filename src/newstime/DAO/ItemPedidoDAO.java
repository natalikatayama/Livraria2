package newstime.DAO;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import newstime.entidade.ItemPedido;
import newstime.entidade.Pedido;
import newstime.excecao.BancoException;

/**
 * Classe de DAO para item do pedido
 * @author Ian Melo
 */
public class ItemPedidoDAO implements DAO<ItemPedido> {
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
     * Cria uma DAO de item do pedido, com um banco de dados
     * @param bd Banco de dados a conectar
     */
    public ItemPedidoDAO(BancoDados bd) {
        this.bd = bd;
    }
    
    @Override
    public void inserir(ItemPedido o) throws BancoException {
        try {
            //Define String
            sql = "INSERT INTO ItemPedido VALUES (?,?,?,?,FALSE)";
            //Abre conexao e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Atrubui os dados
            pst.setInt(1,o.getID_PEDIDO());
            pst.setInt(2, o.getID_LIVRO());
            pst.setInt(3,o.getQuantidade());
            pst.setFloat(4,o.getSubtotal());
            //Executa
            pst.executeUpdate();
            bd.fecharConexao();
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao inserir o item do pedido.");
        }
    }

    @Override
    public void alterar(ItemPedido o) throws BancoException {
        try {
            //Define String
            sql = "UPDATE ItemPedido SET Quantidade="+o.getQuantidade()+", Subtotal='"+o.getSubtotal()+"' " +
                "WHERE IdPedido="+o.getID_PEDIDO()+" AND CodLivro="+o.getID_LIVRO();
            //Abre banco e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Executa
            pst.executeUpdate();
            bd.fecharConexao();
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao alterar o item do pedido.");
        }
    }

    @Override
    public void excluir(ItemPedido o) throws BancoException {
        try {
            //Define String
            sql = "UPDATE ItemPedido SET XDEAD = TRUE WHERE IdPedido="+o.getID_PEDIDO()+" AND CodLivro="+o.getID_LIVRO();
            //Abre banco e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Executa
            pst.executeUpdate();
            bd.fecharConexao();
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao excluir o item do pedido.");
        }
    }

    @Override
    public ItemPedido buscar(ItemPedido o) throws BancoException {
        try {
            ItemPedido item = null;
            //Define String
            sql = "SELECT * FROM ItemPedido WHERE IdPedido=? AND CodLivro=? AND XDEAD=FALSE";
            //Abre banco e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Atribui os dados
            pst.setInt(1, o.getID_PEDIDO());
            pst.setInt(2, o.getID_LIVRO());
            //Executa e puxa a busca
            rs = pst.executeQuery();
            //Verifica se houve resultados e atribui valores ao objeto
            if(rs.next()){
                item = new ItemPedido();
                item.setID_PEDIDO(rs.getInt("IdPedido"));
                item.setID_LIVRO(rs.getInt("IdLivro"));
                item.setQuantidade(rs.getInt("Quantidade"));
                item.setSubtotal(rs.getInt("Subtotal"));
            }
            //Verifica se o objeto foi nulo e joga uma exceção, caso não foi encontrado
            if(item == null) {
                bd.fecharConexao();
                throw new BancoException("Item do pedido não foi encontrada.");
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
    public ItemPedido buscarId(ItemPedido o) throws BancoException {
        try {
            ItemPedido item = null;
            //Define String
            sql = "SELECT * FROM ItemPedido WHERE IdPedido=? AND CodLivro=? AND XDEAD=FALSE";
            //Abre banco e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Atribui os dados
            pst.setInt(1, o.getID_PEDIDO());
            pst.setInt(2, o.getID_LIVRO());
            //Executa e puxa a busca
            rs = pst.executeQuery();
            //Verifica se houve resultados e atribui valores ao objeto
            if(rs.next()){
                item = new ItemPedido();
                item.setID_PEDIDO(rs.getInt("IdPedido"));
                item.setID_LIVRO(rs.getInt("IdLivro"));
                item.setQuantidade(rs.getInt("Quantidade"));
                item.setSubtotal(rs.getInt("Subtotal"));
            }
            //Verifica se o objeto foi nulo e joga uma exceção, caso não foi encontrado
            if(item == null) {
                bd.fecharConexao();
                throw new BancoException("Item do pedido não foi encontrada.");
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
            sql = "SELECT * FROM ItemPedido WHERE XDEAD=FALSE";
            //Abre banco e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Executa e puxa a busca
            rs = pst.executeQuery();
            //Verifica se houve resultados e atribui valores ao objeto
            while(rs.next()){
                item = new ItemPedido();
                item.setID_PEDIDO(rs.getInt("IdPedido"));
                item.setID_LIVRO(rs.getInt("IdLivro"));
                item.setQuantidade(rs.getInt("Quantidade"));
                item.setSubtotal(rs.getInt("Subtotal"));
                //Adiciona à lista
                itens.add(item);
            }
            bd.fecharConexao();
            return itens;
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao listar os itens do pedido.");
        }
    }
    /**
     * Lista os itens do pedido de acordo com o pedido
     * @param pedido Pedido de onde buscar os itens
     * @return Lista de itens do pedido definido
     * @throws BancoException Caso ocorra algum problema na listagem
     */
    public List<ItemPedido> listarPedido(Pedido pedido) throws BancoException {
        try {
            ArrayList<ItemPedido> itens = new ArrayList<>();
            ItemPedido item;
            //Define String
            sql = "SELECT * FROM ItemPedido WHERE IdPedido=? AND XDEAD=FALSE";
            //Abre banco e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Atribui os dados
            pst.setInt(1, pedido.getID());
            //Executa e puxa a busca
            rs = pst.executeQuery();
            //Verifica se houve resultados e atribui valores ao objeto
            while(rs.next()){
                item = new ItemPedido();
                item.setID_PEDIDO(rs.getInt("IdPedido"));
                item.setID_LIVRO(rs.getInt("IdLivro"));
                item.setQuantidade(rs.getInt("Quantidade"));
                item.setSubtotal(rs.getInt("Subtotal"));
                //Adiciona à lista
                itens.add(item);
            }
            bd.fecharConexao();
            return itens;
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao listar os itens do pedido.");
        }
    }
    /**
     * Excluir os itens do pedido de acordo com o pedido
     * @param pedido Pedido de onde excluir os itens
     * @throws BancoException Caso ocorra algum problema na exclusão
     */
    public void excluirPedido(Pedido pedido) throws BancoException {
        try {
            //Define String
            sql = "UPDATE ItemPedido SET XDEAD = TRUE WHERE IdPedido="+pedido.getID();
            //Abre banco e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Executa
            pst.executeUpdate();
            bd.fecharConexao();
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao excluir os itens do pedido definido.");
        }
    }
}
