package newstime.teste;

import newstime.entidade.*;
import newstime.excecao.BancoException;
import newstime.excecao.NegocioException;

/**
 * Classe de teste de conta restrita
 * @author Ian Melo
 */
public class TesteContaRestrita {
    public static void main(String[] args) {
        //Define funcionário
        Funcionario f = new Funcionario();
        f.setLogin("maria_angela");
        f.setSenha("12445534342");
        
        try {
            ContaRestrita.logar(f);
            System.out.println(ContaRestrita.getFuncionario().getNome());
            System.out.println(ContaRestrita.getDataHoraEntrada());
        } catch (BancoException | NegocioException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
