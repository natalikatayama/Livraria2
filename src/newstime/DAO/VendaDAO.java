package newstime.DAO;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import newstime.entidade.Venda;
import newstime.excecao.BancoException;

/**
 * Classe de DAO para venda
 * @author Ian Melo
 */
public class VendaDAO implements DAO<Venda> {
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
     * Cria uma DAO de venda, com um banco de dados
     * @param bd Banco de dados a conectar
     */
    public VendaDAO(BancoDados bd) {
        this.bd = bd;
    }
    
    @Override
    public void inserir(Venda o) throws BancoException {
        try {
            //Define String
            sql = "INSERT INTO Venda VALUES (NULL,?,?,?,?,?,?,FALSE)";
            //Abre conexao e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Atrubui os dados
            pst.setString(1,o.getStatus().toString());
            pst.setString(2,o.getPago().toString());
            pst.setInt(3,o.getID_PEDIDO());
            pst.setInt(4,o.getID_ENDERECO());
            pst.setInt(5,o.getID_PAGAMENTO());
            pst.setInt(6, o.getID_ENTREGA());
            //Executa
            pst.executeUpdate();
            bd.fecharConexao();
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao inserir a venda."+ex.getMessage());
        }
    }

    @Override
    public void alterar(Venda o) throws BancoException {
        try {
            //Define String
            sql = "UPDATE Venda SET Status='"+o.getStatus().toString()+"', Pago='"+o.getPago().toString()+"', IdPedido="+o.getID_PEDIDO()+", CodEndereco="+o.getID_ENDERECO()+", " +
                "CodPagamento="+o.getID_PAGAMENTO()+", CodEntrega="+o.getID_ENTREGA()+" WHERE CodVenda="+o.getID();
            //Abre banco e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Executa
            pst.executeUpdate();
            bd.fecharConexao();
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao alterar a venda.");
        }
    }

    @Override
    public void excluir(Venda o) throws BancoException {
        try {
            //Define String
            sql = "UPDATE Venda SET XDEAD = TRUE WHERE CodVenda="+o.getID();
            //Abre banco e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Executa
            pst.executeUpdate();
            bd.fecharConexao();
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao excluir a venda.");
        }
    }

    @Override
    public Venda buscar(Venda o) throws BancoException {
        try {
            Venda venda = null;
            //Define String
            sql = "SELECT * FROM Venda WHERE IdPedido=? AND XDEAD=FALSE";
            //Abre banco e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Atribui os dados
            pst.setInt(1, o.getID_PEDIDO());
            //Executa e puxa a busca
            rs = pst.executeQuery();
            //Verifica se houve resultados e atribui valores ao objeto
            if(rs.next()){
                venda = new Venda();
                venda.setStatus(Venda.StatusVenda.valueOf(rs.getString("Status")));
                venda.setPago(Venda.PagoVenda.valueOf(rs.getString("Pago")));
                venda.setID(rs.getInt("CodVenda"));
                venda.setID_PEDIDO(rs.getInt("IdPedido"));
                venda.setID_ENDERECO(rs.getInt("CodEndereco"));
                venda.setID_PAGAMENTO(rs.getInt("CodPagamento"));
                venda.setID_ENTREGA(rs.getInt("CodEntrega"));
            }
            //Verifica se o objeto foi nulo e joga uma exceção, caso não foi encontrado
            if(venda == null) {
                bd.fecharConexao();
                throw new BancoException("Venda não foi encontrada.");
            }
            //Prossegue procedimento, caso tenha encontrado
            bd.fecharConexao();
            return venda;
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao buscar a venda.");
        }
    }
    
    @Override
    public Venda buscarId(Venda o) throws BancoException {
        try {
            Venda venda = null;
            //Define String
            sql = "SELECT * FROM Venda WHERE CodVenda=? AND XDEAD=FALSE";
            //Abre banco e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Atribui os dados
            pst.setInt(1, o.getID());
            //Executa e puxa a busca
            rs = pst.executeQuery();
            //Verifica se houve resultados e atribui valores ao objeto
            if(rs.next()){
                venda = new Venda();
                venda.setStatus(Venda.StatusVenda.valueOf(rs.getString("Status")));
                venda.setPago(Venda.PagoVenda.valueOf(rs.getString("Pago")));
                venda.setID(rs.getInt("CodVenda"));
                venda.setID_PEDIDO(rs.getInt("IdPedido"));
                venda.setID_ENDERECO(rs.getInt("CodEndereco"));
                venda.setID_PAGAMENTO(rs.getInt("CodPagamento"));
                venda.setID_ENTREGA(rs.getInt("CodEntrega"));
            }
            //Verifica se o objeto foi nulo e joga uma exceção, caso não foi encontrado
            if(venda == null) {
                bd.fecharConexao();
                throw new BancoException("Venda não foi encontrada.");
            }
            //Prossegue procedimento, caso tenha encontrado
            bd.fecharConexao();
            return venda;
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao buscar a venda.");
        }
    }
    
    @Override
    public List<Venda> listar() throws BancoException {
        try {
            ArrayList<Venda> vendas = new ArrayList<>();
            Venda venda;
            //Define String
            sql = "SELECT * FROM Venda WHERE XDEAD=FALSE";
            //Abre banco e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Executa e puxa a busca
            rs = pst.executeQuery();
            //Verifica se houve resultados e atribui valores ao objeto
            while(rs.next()){
                venda = new Venda();
                venda.setStatus(Venda.StatusVenda.valueOf(rs.getString("Status")));
                venda.setPago(Venda.PagoVenda.valueOf(rs.getString("Pago")));
                venda.setID(rs.getInt("CodVenda"));
                venda.setID_PEDIDO(rs.getInt("IdPedido"));
                venda.setID_ENDERECO(rs.getInt("CodEndereco"));
                venda.setID_PAGAMENTO(rs.getInt("CodPagamento"));
                venda.setID_ENTREGA(rs.getInt("CodEntrega"));
                //Adiciona à lista
                vendas.add(venda);
            }
            bd.fecharConexao();
            return vendas;
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao listar as vendas.");
        }
    }
}
