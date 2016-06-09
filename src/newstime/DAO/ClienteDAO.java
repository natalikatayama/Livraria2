package newstime.DAO;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import newstime.entidade.Cliente;
import newstime.excecao.BancoException;
import newstime.excecao.FormatacaoIncorretaException;

/**
 * Classe de DAO para cliente
 * @author Ian Melo
 */
public class ClienteDAO implements DAO<Cliente> {
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
     * Cria uma DAO de cliente, com um banco de dados
     * @param bd Banco de dados a conectar
     */
    public ClienteDAO(BancoDados bd) {
        this.bd = bd;
    }
    
    @Override
    public void inserir(Cliente o) throws BancoException {
        try {
            //Define String
            sql = "INSERT INTO Cliente " +
                "VALUES (NULL,?,?,?,?,?,?,?,?,?,?,?,?,FALSE)";
            //Abre conexao e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Atrubui os dados
            pst.setString(1, o.getEmail());
            pst.setString(2, o.getSenha());
            pst.setString(3, o.getNome());
            pst.setString(4, o.getSobrenome());
            pst.setString(5, o.getSexo());
            pst.setString(6, o.getCpf());
            if(o.getDataNascimento() != null)
                pst.setDate(7, new java.sql.Date(o.getDataNascimento().getTime()));
            else
                pst.setNull(7, java.sql.Types.NULL);
            pst.setString(8, o.getTelefone());
            pst.setString(9, o.getTelefoneAlt());
            pst.setString(10, o.getCelular());
            pst.setBoolean(11, o.isPromocional());
            pst.setInt(12, o.getEndereco().getID());
            //Executa
            pst.executeUpdate();
            bd.fecharConexao();
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao inserir o cliente.");
        }
    }

    @Override
    public void alterar(Cliente o) throws BancoException {
        try {
            //Verificando data
            String data;
            if(o.getDataNascimento() != null)
                data = "'" + new java.sql.Date(o.getDataNascimento().getTime()) + "'";
            else
                data = "NULL";
            //Define String
            sql = "UPDATE Cliente SET Email='"+o.getEmail()+"', Senha='"+o.getSenha()+"', Nome='"+o.getNome()+"', " +
                "Sobrenome='"+o.getSobrenome()+"', Sexo='"+o.getSexo()+"', Cpf='"+o.getCpf()+"', DataNascimento="+data+", Telefone='"+o.getTelefone()+"', " +
                "TelefoneAlt='"+o.getTelefoneAlt()+"', Celular='"+o.getCelular()+"', Q_Promocional="+o.isPromocional()+", CodEndereco="+o.getEndereco().getID()+" " +
                "WHERE IdCliente="+o.getID();
            //Abre banco e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Executa
            pst.executeUpdate();
            bd.fecharConexao();
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao alterar o cliente.");
        }
    }

    @Override
    public void excluir(Cliente o) throws BancoException {
        try {
            //Define String
            sql = "UPDATE Cliente SET XDEAD = TRUE WHERE CodEndereco="+o.getID();
            //Abre banco e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Executa
            pst.executeUpdate();
            bd.fecharConexao();
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao excluir o cliente.");
        }
    }

    @Override
    public Cliente buscar(Cliente o) throws BancoException {
        try {
            Cliente cliente = null;
            //Define String
            sql = "SELECT * FROM Cliente WHERE Email=? AND XDEAD=FALSE";
            //Abre banco e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Atribui os dados
            pst.setString(1, o.getEmail());
            //Executa e puxa a busca
            rs = pst.executeQuery();
            //Verifica se houve resultados e atribui valores ao objeto
            if(rs.next()){
                cliente = new Cliente();
                try {
                    cliente.setEmail(rs.getString("Email"));
                } catch (FormatacaoIncorretaException ex) {}
                try {
                    cliente.setSenha(rs.getString("Senha"));
                } catch (FormatacaoIncorretaException ex) {}
                try {
                    cliente.setCpf(rs.getString("Cpf"));
                } catch (FormatacaoIncorretaException ex) {}
                try {
                    cliente.setTelefone(rs.getString("Telefone"));
                } catch (FormatacaoIncorretaException ex) {}
                try {
                    cliente.setTelefoneAlt(rs.getString("TelefoneAlt"));
                } catch (FormatacaoIncorretaException ex) {}
                try {
                    cliente.setCelular(rs.getString("Celular"));
                } catch (FormatacaoIncorretaException ex) {}
                cliente.setNome(rs.getString("Nome"));
                cliente.setSobrenome(rs.getString("Sobrenome"));
                cliente.setSexo(rs.getString("Sexo"));
                if(rs.getDate("DataNascimento") != null)
                    cliente.setDataNascimento(new java.util.Date(rs.getDate("DataNascimento").getTime()));
                cliente.setPromocional(rs.getBoolean("Q_Promocional"));
                cliente.setID(rs.getInt("IdCliente"));
                cliente.setID_ENDERECO(rs.getInt("CodEndereco"));
            }
            //Verifica se o objeto foi nulo e joga uma exceção, caso não foi encontrado
            if(cliente == null) {
                bd.fecharConexao();
                throw new BancoException("Cliente não foi encontrado.");
            }
            //Prossegue procedimento, caso tenha encontrado
            bd.fecharConexao();
            return cliente;
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao buscar o cliente.");
        }
    }
    
    @Override
    public Cliente buscarId(Cliente o) throws BancoException {
        try {
            Cliente cliente = null;
            //Define String
            sql = "SELECT * FROM Cliente WHERE IdCliente=? AND XDEAD=FALSE";
            //Abre banco e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Atribui os dados
            pst.setInt(1, o.getID());
            //Executa e puxa a busca
            rs = pst.executeQuery();
            //Verifica se houve resultados e atribui valores ao objeto
            if(rs.next()){
                cliente = new Cliente();
                try {
                    cliente.setEmail(rs.getString("Email"));
                } catch (FormatacaoIncorretaException ex) {}
                try {
                    cliente.setSenha(rs.getString("Senha"));
                } catch (FormatacaoIncorretaException ex) {}
                try {
                    cliente.setCpf(rs.getString("Cpf"));
                } catch (FormatacaoIncorretaException ex) {}
                try {
                    cliente.setTelefone(rs.getString("Telefone"));
                } catch (FormatacaoIncorretaException ex) {}
                try {
                    cliente.setTelefoneAlt(rs.getString("TelefoneAlt"));
                } catch (FormatacaoIncorretaException ex) {}
                try {
                    cliente.setCelular(rs.getString("Celular"));
                } catch (FormatacaoIncorretaException ex) {}
                cliente.setNome(rs.getString("Nome"));
                cliente.setSobrenome(rs.getString("Sobrenome"));
                cliente.setSexo(rs.getString("Sexo"));
                if(rs.getDate("DataNascimento") != null)
                    cliente.setDataNascimento(new java.util.Date(rs.getDate("DataNascimento").getTime()));
                cliente.setPromocional(rs.getBoolean("Q_Promocional"));
                cliente.setID(rs.getInt("IdCliente"));
                cliente.setID_ENDERECO(rs.getInt("CodEndereco"));
            }
            //Verifica se o objeto foi nulo e joga uma exceção, caso não foi encontrado
            if(cliente == null) {
                bd.fecharConexao();
                throw new BancoException("Cliente não foi encontrado.");
            }
            //Prossegue procedimento, caso tenha encontrado
            bd.fecharConexao();
            return cliente;
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao buscar o cliente.");
        }
    }
    
    @Override
    public List<Cliente> listar() throws BancoException {
        try {
            ArrayList<Cliente> clientes = new ArrayList<>();
            Cliente cliente;
            //Define String
            sql = "SELECT * FROM Cliente WHERE XDEAD=FALSE";
            //Abre banco e prepara gatilho
            pst = bd.abrirConexao().prepareStatement(sql);
            //Executa e puxa a busca
            rs = pst.executeQuery();
            //Verifica se houve resultados e atribui valores ao objeto
            while(rs.next()){
                cliente = new Cliente();
                try {
                    cliente.setEmail(rs.getString("Email"));
                } catch (FormatacaoIncorretaException ex) {}
                try {
                    cliente.setSenha(rs.getString("Senha"));
                } catch (FormatacaoIncorretaException ex) {}
                try {
                    cliente.setCpf(rs.getString("Cpf"));
                } catch (FormatacaoIncorretaException ex) {}
                try {
                    cliente.setTelefone(rs.getString("Telefone"));
                } catch (FormatacaoIncorretaException ex) {}
                try {
                    cliente.setTelefoneAlt(rs.getString("TelefoneAlt"));
                } catch (FormatacaoIncorretaException ex) {}
                try {
                    cliente.setCelular(rs.getString("Celular"));
                } catch (FormatacaoIncorretaException ex) {}
                cliente.setNome(rs.getString("Nome"));
                cliente.setSobrenome(rs.getString("Sobrenome"));
                cliente.setSexo(rs.getString("Sexo"));
                if(rs.getDate("DataNascimento") != null)
                    cliente.setDataNascimento(new java.util.Date(rs.getDate("DataNascimento").getTime()));
                cliente.setPromocional(rs.getBoolean("Q_Promocional"));
                cliente.setID(rs.getInt("IdCliente"));
                cliente.setID_ENDERECO(rs.getInt("CodEndereco"));
                //Adiciona à lista
                clientes.add(cliente);
            }
            bd.fecharConexao();
            return clientes;
        } catch (SQLException ex) {
            bd.fecharConexao();
            throw new BancoException("Houve um problema ao listar os clientes.");
        }
    }
}
