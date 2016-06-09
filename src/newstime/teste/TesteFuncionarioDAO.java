package newstime.teste;

import java.util.logging.Level;
import java.util.logging.Logger;
import newstime.DAO.BancoDados;
import newstime.DAO.FuncionarioDAO;
import newstime.entidade.Funcionario;
import newstime.excecao.BancoException;

/**
 * Classe de teste de DAO de Funcionário
 * @author Ian Melo
 */
public class TesteFuncionarioDAO {
    public static void main(String[] args) {
        Funcionario f = new Funcionario();
        f.setLogin("hitler");
        f.setNome("Amaraldo Rômulo");
        f.setSenha("212312");
        
        FuncionarioDAO fDao = new FuncionarioDAO(new BancoDados());
        try {
            fDao.inserir(f);
            f = fDao.buscar(f);
            
            System.out.println(f.getID());
        } catch (BancoException ex) {
            Logger.getLogger(TesteFuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
