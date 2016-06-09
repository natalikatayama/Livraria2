package newstime.DAO;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import newstime.entidade.Entrega;
import newstime.excecao.BancoException;

/**
 * Classe de DAO para entrega
 * @author Ian Melo
 */
public class EntregaDAO implements DAO<Entrega> {
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
     * Cria uma DAO de entrega, com um banco de dados
     * @param bd Banco de dados a conectar
     */
    public EntregaDAO(BancoDados bd) {
        this.bd = bd;
    }
    
    @Override
    public void inserir(Entrega o) throws BancoException {
        try {
            //Define String
            sql = "INSERT INTO Entrega " +
                "VALUES (NULL,?,?,?,FALSE)";
            //Abre conexao e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Atrubui os dados
            pst.setString(1, o.getTipo().toString());
            pst.setFloat(2, o.getPreco());
            if(o.getDataEntrega() != null)
                pst.setDate(3, new java.sql.Date(o.getDataEntrega().getTime()));
            else
                pst.setNull(3, java.sql.Types.NULL);
            //Executa
            pst.executeUpdate();
            bd.fecharConexao();
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao inserir a entrega.");
        }
    }

    @Override
    public void alterar(Entrega o) throws BancoException {
        try {
            //Verificando data
            String data;
            if(o.getDataEntrega() != null)
                data = "'" + new java.sql.Date(o.getDataEntrega().getTime()) + "'";
            else
                data = "NULL";
            //Define String
            sql = "UPDATE Entrega SET Tipo='"+o.getTipo().toString()+"', Preco='"+o.getPreco()+"', DataEntrega="+data+" " +
                "WHERE CodEntrega="+o.getID();
            //Abre banco e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Executa
            pst.executeUpdate();
            bd.fecharConexao();
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao alterar a entrega.");
        }
    }

    @Override
    public void excluir(Entrega o) throws BancoException {
        try {
            //Define String
            sql = "UPDATE Entrega SET XDEAD = TRUE WHERE CodEntrega="+o.getID();
            //Abre banco e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Executa
            pst.executeUpdate();
            bd.fecharConexao();
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao excluir a entrega.");
        }
    }

    @Override
    public Entrega buscar(Entrega o) throws BancoException {
        try {
            Entrega entrega = null;
            //Define String
            sql = "SELECT * FROM Entrega WHERE Tipo=? AND Preco=? " +
                "AND XDEAD=FALSE ORDER BY CodEntrega DESC";
            //Abre banco e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Atribui os dados
            pst.setString(1, o.getTipo().toString());
            pst.setFloat(2,o.getPreco());
            //Executa e puxa a busca
            rs = pst.executeQuery();
            //Verifica se houve resultados e atribui valores ao objeto
            if(rs.next()){
                entrega = new Entrega();
                entrega.setTipo(Entrega.TipoEntrega.valueOf(rs.getString("Tipo")));
                entrega.setPreco(rs.getFloat("Preco"));
                if(rs.getDate("DataEntrega") != null)
                    entrega.setDataEntrega(new java.util.Date(rs.getDate("DataEntrega").getTime()));
                entrega.setID(rs.getInt("CodEntrega"));
            }
            //Verifica se o objeto foi nulo e joga uma exceção, caso não foi encontrado
            if(entrega == null) {
                bd.fecharConexao();
                throw new BancoException("Entrega não foi encontrada.");
            }
            //Prossegue procedimento, caso tenha encontrado
            bd.fecharConexao();
            return entrega;
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao buscar a entrega.");
        }
    }
    
    @Override
    public Entrega buscarId(Entrega o) throws BancoException {
        try {
            Entrega entrega = null;
            //Define String
            sql = "SELECT * FROM Entrega WHERE CodEntrega=? AND XDEAD=FALSE";
            //Abre banco e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Atribui os dados
            pst.setInt(1, o.getID());
            //Executa e puxa a busca
            rs = pst.executeQuery();
            //Verifica se houve resultados e atribui valores ao objeto
            if(rs.next()){
                entrega = new Entrega();
                entrega.setTipo(Entrega.TipoEntrega.valueOf(rs.getString("Tipo")));
                entrega.setPreco(rs.getFloat("Preco"));
                if(rs.getDate("DataEntrega") != null)
                    entrega.setDataEntrega(new java.util.Date(rs.getDate("DataEntrega").getTime()));
                entrega.setID(rs.getInt("CodEntrega"));
            }
            //Verifica se o objeto foi nulo e joga uma exceção, caso não foi encontrado
            if(entrega == null) {
                bd.fecharConexao();
                throw new BancoException("Entrega não foi encontrada.");
            }
            //Prossegue procedimento, caso tenha encontrado
            bd.fecharConexao();
            return entrega;
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao buscar a entrega.");
        }
    }
    
    @Override
    public List<Entrega> listar() throws BancoException {
        try {
            ArrayList<Entrega> entregas = new ArrayList<>();
            Entrega entrega;
            //Define String
            sql = "SELECT * FROM Entrega WHERE XDEAD=FALSE";
            //Abre banco e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Executa e puxa a busca
            rs = pst.executeQuery();
            //Verifica se houve resultados e atribui valores ao objeto
            while(rs.next()){
                entrega = new Entrega();
                entrega.setTipo(Entrega.TipoEntrega.valueOf(rs.getString("Tipo")));
                entrega.setPreco(rs.getFloat("Preco"));
                if(rs.getDate("DataEntrega") != null)
                    entrega.setDataEntrega(new java.util.Date(rs.getDate("DataEntrega").getTime()));
                entrega.setID(rs.getInt("CodEntrega"));
                //Adiciona à lista
                entregas.add(entrega);
            }
            bd.fecharConexao();
            return entregas;
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao listar as entregas.");
        }
    }
}
