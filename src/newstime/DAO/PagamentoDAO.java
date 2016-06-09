package newstime.DAO;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import newstime.entidade.Pagamento;
import newstime.excecao.NegocioException;
import newstime.excecao.BancoException;

/**
 * Classe de DAO para pagamento
 * @author Ian Melo
 */
public class PagamentoDAO implements DAO<Pagamento> {
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
     * Cria uma DAO de pagamento, com um banco de dados
     * @param bd Banco de dados a conectar
     */
    public PagamentoDAO(BancoDados bd) {
        this.bd = bd;
    }
    
    @Override
    public void inserir(Pagamento o) throws BancoException {
        try {
            //Define String
            sql = "INSERT INTO Pagamento VALUES (NULL,?,?,?,FALSE)";
            //Abre conexao e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Atrubui os dados
            pst.setString(1, o.getForma().toString());
            pst.setInt(2, o.getNumParcelas());
            pst.setInt(3, o.getParcelaRestante());
            //Executa
            pst.executeUpdate();
            bd.fecharConexao();
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao inserir o pagamento.");
        }
    }

    @Override
    public void alterar(Pagamento o) throws BancoException {
        try {
            //Define String
            sql = "UPDATE Pagamento SET Forma='"+o.getForma().toString()+"', NumParcelas="+o.getNumParcelas()+", ParcelaRestante="+o.getParcelaRestante()+" " +
                "WHERE CodPagamento="+o.getID();
            //Abre banco e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Executa
            pst.executeUpdate();
            bd.fecharConexao();
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao alterar o pagamento.");
        }
    }

    @Override
    public void excluir(Pagamento o) throws BancoException {
        try {
            //Define String
            sql = "UPDATE Pagamento SET XDEAD = TRUE WHERE CodPagamento="+o.getID();
            //Abre banco e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Executa
            pst.executeUpdate();
            bd.fecharConexao();
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao excluir o pagamento.");
        }
    }

    @Override
    public Pagamento buscar(Pagamento o) throws BancoException {
        try {
            Pagamento pagamento = null;
            //Define String
            sql = "SELECT * FROM Pagamento WHERE Forma=? AND NumParcelas=? " +
                "AND XDEAD=FALSE ORDER BY CodPagamento DESC";
            //Abre banco e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Atribui os dados
            pst.setString(1, o.getForma().toString());
            pst.setFloat(2,o.getNumParcelas());
            //Executa e puxa a busca
            rs = pst.executeQuery();
            //Verifica se houve resultados e atribui valores ao objeto
            if(rs.next()){
                pagamento = new Pagamento();
                pagamento.setForma(Pagamento.TipoPagamento.valueOf(rs.getString("Forma")));
                try {
                    pagamento.setNumParcelas(rs.getInt("NumParcelas"));
                } catch (NegocioException ex) {}
                pagamento.setParcelaRestante(rs.getInt("ParcelaRestante"));
                pagamento.setID(rs.getInt("CodPagamento"));
            }
            //Verifica se o objeto foi nulo e joga uma exceção, caso não foi encontrado
            if(pagamento == null) {
                bd.fecharConexao();
                throw new BancoException("Pagamento não foi encontrada.");
            }
            //Prossegue procedimento, caso tenha encontrado
            bd.fecharConexao();
            return pagamento;
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao buscar o pagamento.");
        }
    }
    
    @Override
    public Pagamento buscarId(Pagamento o) throws BancoException {
        try {
            Pagamento pagamento = null;
            //Define String
            sql = "SELECT * FROM Pagamento WHERE CodPagamento=? AND XDEAD=FALSE";
            //Abre banco e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Atribui os dados
            pst.setInt(1, o.getID());
            //Executa e puxa a busca
            rs = pst.executeQuery();
            //Verifica se houve resultados e atribui valores ao objeto
            if(rs.next()){
                pagamento = new Pagamento();
                pagamento.setForma(Pagamento.TipoPagamento.valueOf(rs.getString("Forma")));
                try {
                    pagamento.setNumParcelas(rs.getInt("NumParcelas"));
                } catch (NegocioException ex) {}
                pagamento.setParcelaRestante(rs.getInt("ParcelaRestante"));
                pagamento.setID(rs.getInt("CodPagamento"));
            }
            //Verifica se o objeto foi nulo e joga uma exceção, caso não foi encontrado
            if(pagamento == null) {
                bd.fecharConexao();
                throw new BancoException("Pagamento não foi encontrado.");
            }
            //Prossegue procedimento, caso tenha encontrado
            bd.fecharConexao();
            return pagamento;
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao buscar o pagamento.");
        }
    }
    
    @Override
    public List<Pagamento> listar() throws BancoException {
        try {
            ArrayList<Pagamento> pagamentos = new ArrayList<>();
            Pagamento pagamento;
            //Define String
            sql = "SELECT * FROM Pagamento WHERE XDEAD=FALSE";
            //Abre banco e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Executa e puxa a busca
            rs = pst.executeQuery();
            //Verifica se houve resultados e atribui valores ao objeto
            while(rs.next()){
                pagamento = new Pagamento();
                pagamento.setForma(Pagamento.TipoPagamento.valueOf(rs.getString("Forma")));
                try {
                    pagamento.setNumParcelas(rs.getInt("NumParcelas"));
                } catch (NegocioException ex) {}
                pagamento.setParcelaRestante(rs.getInt("ParcelaRestante"));
                pagamento.setID(rs.getInt("CodPagamento"));
                //Adiciona à lista
                pagamentos.add(pagamento);
            }
            bd.fecharConexao();
            return pagamentos;
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao listar os pagamentos.");
        }
    }
}
