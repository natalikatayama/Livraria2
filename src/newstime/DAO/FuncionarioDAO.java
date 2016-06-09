package newstime.DAO;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import newstime.entidade.Funcionario;
import newstime.excecao.BancoException;

/**
 * Classe de DAO para funcionário
 * @author Ian Melo
 */
public class FuncionarioDAO implements DAO<Funcionario> {
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
     * Cria uma DAO de funcionário, com um banco de dados
     * @param bd Banco de dados a conectar
     */
    public FuncionarioDAO(BancoDados bd) {
        this.bd = bd;
    }
    
    @Override
    public void inserir(Funcionario o) throws BancoException {
        try {
            //Define String
            sql = "INSERT INTO Funcionario VALUES (NULL,?,?,?,FALSE)";
            //Abre conexao e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Atrubui os dados
            pst.setString(1, o.getLogin());
            pst.setString(2, o.getSenha());
            pst.setString(3, o.getNome());
            //Executa
            pst.executeUpdate();
            bd.fecharConexao();
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao inserir o funcionário.");
        }
    }

    @Override
    public void alterar(Funcionario o) throws BancoException {
        try {
            //Define String
            sql = "UPDATE Funcionario SET Login='"+o.getLogin()+"', Senha='"+o.getSenha()+"', Nome='"+o.getNome()+"' " +
                  "WHERE IdFuncionario="+o.getID();
            //Abre banco e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Executa
            pst.executeUpdate();
            bd.fecharConexao();
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao alterar o funcionário.");
        }
    }

    @Override
    public void excluir(Funcionario o) throws BancoException {
        try {
            //Define String
            sql = "UPDATE Funcionario SET XDEAD = TRUE WHERE IdFuncionario="+o.getID();
            //Abre banco e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Executa
            pst.executeUpdate();
            bd.fecharConexao();
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao excluir o funcionário.");
        }
    }

    @Override
    public Funcionario buscar(Funcionario o) throws BancoException {
        try {
            Funcionario funcionario = null;
            //Define String
            sql = "SELECT * FROM Funcionario WHERE Login=? AND XDEAD=FALSE";
            //Abre banco e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Atribui os dados
            pst.setString(1, o.getLogin());
            //Executa e puxa a busca
            rs = pst.executeQuery();
            //Verifica se houve resultados e atribui valores ao objeto
            if(rs.next()){
                funcionario = new Funcionario();
                funcionario.setLogin(rs.getString("Login"));
                funcionario.setSenha(rs.getString("Senha"));
                funcionario.setNome(rs.getString("Nome"));
                funcionario.setID(rs.getInt("IdFuncionario"));
            }
            //Verifica se o objeto foi nulo e joga uma exceção, caso não foi encontrado
            if(funcionario == null) {
                bd.fecharConexao();
                throw new BancoException("Funcionário não foi encontrado.");
            }
            //Prossegue procedimento, caso tenha encontrado
            bd.fecharConexao();
            return funcionario;
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao buscar o funcionário.");
        }
    }
    
    @Override
    public Funcionario buscarId(Funcionario o) throws BancoException {
        try {
            Funcionario funcionario = null;
            //Define String
            sql = "SELECT * FROM Funcionario WHERE IdFuncionario=? AND XDEAD=FALSE";
            //Abre banco e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Atribui os dados
            pst.setInt(1, o.getID());
            //Executa e puxa a busca
            rs = pst.executeQuery();
            //Verifica se houve resultados e atribui valores ao objeto
            if(rs.next()){
                funcionario = new Funcionario();
                funcionario.setLogin(rs.getString("Login"));
                funcionario.setSenha(rs.getString("Senha"));
                funcionario.setNome(rs.getString("Nome"));
                funcionario.setID(rs.getInt("IdFuncionario"));
            }
            //Verifica se o objeto foi nulo e joga uma exceção, caso não foi encontrado
            if(funcionario == null) {
                bd.fecharConexao();
                throw new BancoException("Funcionário não foi encontrado.");
            }
            //Prossegue procedimento, caso tenha encontrado
            bd.fecharConexao();
            return funcionario;
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao buscar o funcionário.");
        }
    }
    
    @Override
    public List<Funcionario> listar() throws BancoException {
        try {
            ArrayList<Funcionario> funcionarios = new ArrayList<>();
            Funcionario funcionario;
            //Define String
            sql = "SELECT * FROM Funcionario WHERE XDEAD=FALSE";
            //Abre banco e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Executa e puxa a busca
            rs = pst.executeQuery();
            //Verifica se houve resultados e atribui valores ao objeto
            while(rs.next()){
                funcionario = new Funcionario();
                funcionario.setLogin(rs.getString("Login"));
                funcionario.setSenha(rs.getString("Senha"));
                funcionario.setNome(rs.getString("Nome"));
                funcionario.setID(rs.getInt("IdFuncionario"));
                //Adiciona à lista
                funcionarios.add(funcionario);
            }
            bd.fecharConexao();
            return funcionarios;
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao listar os funcionários.");
        }
    }
}
