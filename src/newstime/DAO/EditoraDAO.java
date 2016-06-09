package newstime.DAO;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import newstime.entidade.Editora;
import newstime.excecao.BancoException;
import newstime.excecao.FormatacaoIncorretaException;

/**
 * Classe de DAO para editora
 * @author Ian Melo
 */
public class EditoraDAO implements DAO<Editora> {
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
     * Cria uma DAO de editora, com um banco de dados
     * @param bd Banco de dados a conectar
     */
    public EditoraDAO(BancoDados bd) {
        this.bd = bd;
    }
    
    @Override
    public void inserir(Editora o) throws BancoException {
        try {
            //Define String
            sql = "INSERT INTO Editora VALUES (NULL,?,?,?,?,FALSE)";
            //Abre conexao e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Atrubui os dados
            pst.setString(1, o.getCnpj());
            pst.setString(2, o.getNome());
            pst.setString(3, o.getEndereco());
            pst.setString(4, o.getTelefone());
            //Executa
            pst.executeUpdate();
            bd.fecharConexao();
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao inserir a editora.");
        }
    }

    @Override
    public void alterar(Editora o) throws BancoException {
        try {
            //Define String
            sql = "UPDATE Editora SET Cnpj='"+o.getCnpj()+"', Nome='"+o.getNome()+"', Endereco='"+o.getEndereco()+"', Telefone='"+o.getTelefone()+"' " +
                "WHERE CodEditora="+o.getID();
            //Abre banco e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Executa
            pst.executeUpdate(sql);
            bd.fecharConexao();
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao alterar a editora.");
        }
    }

    @Override
    public void excluir(Editora o) throws BancoException {
        try {
            //Define String
            sql = "UPDATE Editora SET XDEAD = TRUE WHERE CodEditora="+o.getID();
            //Abre banco e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Executa
            pst.executeUpdate(sql);
            bd.fecharConexao();
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao excluir a editora.");
        }
    }

    @Override
    public Editora buscar(Editora o) throws BancoException {
        try {
            Editora editora = null;
            //Define String
            sql = "SELECT * FROM Editora WHERE Nome=? AND XDEAD=FALSE";
            //Abre banco e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Atribui os dados
            pst.setString(1, o.getNome());
            //Executa e puxa a busca
            rs = pst.executeQuery();
            //Verifica se houve resultados e atribui valores ao objeto
            if(rs.next()){
                editora = new Editora();
                editora.setNome(rs.getString("Nome"));
                editora.setEndereco(rs.getString("Endereco"));
                try {
                    editora.setCnpj(rs.getString("Cnpj"));
                } catch(FormatacaoIncorretaException ex) {}
                try {
                    editora.setTelefone(rs.getString("Telefone"));
                } catch(FormatacaoIncorretaException ex) {}
                editora.setID(rs.getInt("CodEditora"));
            }
            //Verifica se o objeto foi nulo e joga uma exceção, caso não foi encontrado
            if(editora == null) {
                bd.fecharConexao();
                throw new BancoException("Editora não foi encontrada.");
            }
            //Prossegue procedimento, caso tenha encontrado
            bd.fecharConexao();
            return editora;
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao buscar a editora.");
        }
    }
    
    @Override
    public Editora buscarId(Editora o) throws BancoException {
        try {
            Editora editora = null;
            //Define String
            sql = "SELECT * FROM Editora WHERE CodEditora=? AND XDEAD=FALSE";
            //Abre banco e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Atribui os dados
            pst.setInt(1, o.getID());
            //Executa e puxa a busca
            rs = pst.executeQuery();
            //Verifica se houve resultados e atribui valores ao objeto
            if(rs.next()){
                editora = new Editora();
                editora.setNome(rs.getString("Nome"));
                editora.setEndereco(rs.getString("Endereco"));
                try {
                    editora.setCnpj(rs.getString("Cnpj"));
                } catch(FormatacaoIncorretaException ex) {}
                try {
                    editora.setTelefone(rs.getString("Telefone"));
                } catch(FormatacaoIncorretaException ex) {}
                editora.setID(rs.getInt("CodEditora"));
            }
            //Verifica se o objeto foi nulo e joga uma exceção, caso não foi encontrado
            if(editora == null) {
                bd.fecharConexao();
                throw new BancoException("Editora não foi encontrada.");
            }
            //Prossegue procedimento, caso tenha encontrado
            bd.fecharConexao();
            return editora;
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao buscar a editora.");
        }
    }

    @Override
    public List<Editora> listar() throws BancoException {
        try {
            ArrayList<Editora> editoras = new ArrayList<>();
            Editora editora;
            //Define String
            sql = "SELECT * FROM Editora WHERE XDEAD=FALSE";
            //Abre banco e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Executa e puxa a busca
            rs = pst.executeQuery();
            //Verifica se houve resultados e atribui valores ao objeto
            while(rs.next()){
                editora = new Editora();
                editora.setNome(rs.getString("Nome"));
                editora.setEndereco(rs.getString("Endereco"));
                try {
                    editora.setCnpj(rs.getString("Cnpj"));
                } catch(FormatacaoIncorretaException ex) {}
                try {
                    editora.setTelefone(rs.getString("Telefone"));
                } catch(FormatacaoIncorretaException ex) {}
                editora.setID(rs.getInt("CodEditora"));
                //Adiciona à lista
                editoras.add(editora);
            }
            bd.fecharConexao();
            return editoras;
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao listar as editoras.");
        }
    }
}
