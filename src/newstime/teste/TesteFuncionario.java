package newstime.teste;

import newstime.entidade.*;

/**
 * Classe de teste de funcionário
 * @author Ian Melo
 */
public class TesteFuncionario {
    public static void main(String[] args) {
        Funcionario f = new Funcionario();
        
        //Definição dos dados
        f.setLogin("regina_moreira");
        f.setNome("Regina Moreira");
        f.setSenha("123abc");
        
        //Exibição dos dados
        System.out.println(f.getLogin());
        System.out.println(f.getNome());
        System.out.println(f.getSenha());
    }
}
