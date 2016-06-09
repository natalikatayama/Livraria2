package newstime.DAO;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import newstime.entidade.Endereco;
import newstime.excecao.BancoException;
import newstime.excecao.FormatacaoIncorretaException;

/**
 * Classe de DAO para endereço
 * @author Ian Melo
 */
public class EnderecoDAO implements DAO<Endereco> {
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
     * Cria uma DAO de endereço, com um banco de dados
     * @param bd Banco de dados a conectar
     */
    public EnderecoDAO(BancoDados bd) {
        this.bd = bd;
    }
    
    @Override
    public void inserir(Endereco o) throws BancoException {
        try {
            //Define String
            sql = "INSERT INTO Endereco VALUES (NULL,?,?,?,?,?,?,?,?,FALSE)";
            //Abre conexao e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Atrubui os dados
            pst.setString(1, o.getLogradouro());
            pst.setString(2, o.getNumero());
            pst.setString(3, o.getComplemento());
            pst.setString(4, o.getBairro());
            pst.setString(5, o.getCidade());
            pst.setString(6, o.getEstado());
            pst.setString(7, o.getCep());
            pst.setString(8, o.getReferencia());
            //Executa
            pst.executeUpdate();
            bd.fecharConexao();
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao inserir o endereço.");
        }
    }

    @Override
    public void alterar(Endereco o) throws BancoException {
        try {
            //Define String
            sql = "UPDATE Endereco SET Logradouro='"+o.getLogradouro()+"', Numero='"+o.getNumero()+"', Complemento='"+o.getComplemento()+"', " +
                "Bairro='"+o.getBairro()+"', Cidade='"+o.getCidade()+"', Estado='"+o.getEstado()+"', Cep='"+o.getCep()+"', Referencia='"+o.getReferencia()+"' " +
                "WHERE CodEndereco="+o.getID();
            //Abre banco e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Executa
            pst.executeUpdate();
            bd.fecharConexao();
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao alterar o endereço.");
        }
    }
    
    @Override
    public void excluir(Endereco o) throws BancoException {
        try {
            //Define String
            sql = "UPDATE Endereco SET XDEAD = TRUE WHERE CodEndereco="+o.getID();
            //Abre banco e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Executa
            pst.executeUpdate();
            bd.fecharConexao();
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao excluir o endereço.");
        }
    }

    @Override
    public Endereco buscar(Endereco o) throws BancoException {
        try {
            Endereco endereco = null;
            //Define String
            sql = "SELECT * FROM Endereco WHERE Numero=? AND Complemento=? AND Cep=? AND XDEAD=FALSE " +
                "ORDER BY CodEndereco DESC";
            //Abre banco e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Atribui os dados
            pst.setString(1, o.getNumero());
            pst.setString(2, o.getComplemento());
            pst.setString(3, o.getCep());
            //Executa e puxa a busca
            rs = pst.executeQuery();
            //Verifica se houve resultados e atribui valores ao objeto
            if(rs.next()){
                endereco = new Endereco();
                endereco.setLogradouro(rs.getString("Logradouro"));
                endereco.setNumero(rs.getString("Numero"));
                endereco.setComplemento(rs.getString("Complemento"));
                endereco.setBairro(rs.getString("Bairro"));
                endereco.setCidade(rs.getString("Cidade"));
                try {
                    endereco.setEstado(rs.getString("Estado"));
                } catch (FormatacaoIncorretaException ex) {}
                try {
                    endereco.setCep(rs.getString("Cep"));
                } catch (FormatacaoIncorretaException ex) {}
                endereco.setReferencia(rs.getString("Referencia"));
                endereco.setID(rs.getInt("CodEndereco"));
            }
            //Verifica se o objeto foi nulo e joga uma exceção, caso não foi encontrado
            if(endereco == null) {
                bd.fecharConexao();
                throw new BancoException("Endereço não foi encontrado.");
            }
            //Prossegue procedimento, caso tenha encontrado
            bd.fecharConexao();
            return endereco;
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao buscar o endereço.");
        }
    }
    
    @Override
    public Endereco buscarId(Endereco o) throws BancoException {
        try {
            Endereco endereco = null;
            //Define String
            sql = "SELECT * FROM Endereco WHERE CodEndereco=? AND XDEAD=FALSE";
            //Abre banco e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Atribui os dados
            pst.setInt(1, o.getID());
            //Executa e puxa a busca
            rs = pst.executeQuery();
            //Verifica se houve resultados e atribui valores ao objeto
            if(rs.next()){
                endereco = new Endereco();
                endereco.setLogradouro(rs.getString("Logradouro"));
                endereco.setNumero(rs.getString("Numero"));
                endereco.setComplemento(rs.getString("Complemento"));
                endereco.setBairro(rs.getString("Bairro"));
                endereco.setCidade(rs.getString("Cidade"));
                try {
                    endereco.setEstado(rs.getString("Estado"));
                } catch (FormatacaoIncorretaException ex) {}
                try {
                    endereco.setCep(rs.getString("Cep"));
                } catch (FormatacaoIncorretaException ex) {}
                endereco.setReferencia(rs.getString("Referencia"));
                endereco.setID(rs.getInt("CodEndereco"));
            }
            //Verifica se o objeto foi nulo e joga uma exceção, caso não foi encontrado
            if(endereco == null) {
                bd.fecharConexao();
                throw new BancoException("Endereço não foi encontrado.");
            }
            //Prossegue procedimento, caso tenha encontrado
            bd.fecharConexao();
            return endereco;
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao buscar o endereço.");
        }
    }
    
    @Override
    public List<Endereco> listar() throws BancoException {
        try {
            ArrayList<Endereco> enderecos = new ArrayList<>();
            Endereco endereco;
            //Define String
            sql = "SELECT * FROM Endereco WHERE XDEAD=FALSE";
            //Abre banco e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Executa e puxa a busca
            rs = pst.executeQuery();
            //Verifica se houve resultados e atribui valores ao objeto
            while(rs.next()){
                endereco = new Endereco();
                endereco.setLogradouro(rs.getString("Logradouro"));
                endereco.setNumero(rs.getString("Numero"));
                endereco.setComplemento(rs.getString("Complemento"));
                endereco.setBairro(rs.getString("Bairro"));
                endereco.setCidade(rs.getString("Cidade"));
                try {
                    endereco.setEstado(rs.getString("Estado"));
                } catch (FormatacaoIncorretaException ex) {}
                try {
                    endereco.setCep(rs.getString("Cep"));
                } catch (FormatacaoIncorretaException ex) {}
                endereco.setReferencia(rs.getString("Referencia"));
                endereco.setID(rs.getInt("CodEndereco"));
                //Adiciona à lista
                enderecos.add(endereco);
            }
            bd.fecharConexao();
            return enderecos;
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao listar os endereços.");
        }
    }
}
