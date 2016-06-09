package newstime.teste;

import newstime.DAO.BancoDados;
import newstime.excecao.BancoException;
import java.sql.Connection;

/**
 * Teste de banco de dados
 * @author Ian Melo
 */
public class TesteBanco {
    public static void main(String[] args) {
        BancoDados bd = new BancoDados();
        Connection c;
        try {
            c = bd.abrirConexao();
            bd.fecharConexao();
        } catch (BancoException ex) {
            System.out.println("ERRO");
        }
    }
}
