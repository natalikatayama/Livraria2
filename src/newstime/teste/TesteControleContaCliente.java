package newstime.teste;

import newstime.controle.ControleContaCliente;

/**
 * Teste de Controle de conta cliente
 * @author Fabio
 */
public class TesteControleContaCliente {
     public static void main(String[] args){
         ControleContaCliente controleCliente = new  ControleContaCliente();
         controleCliente.cadastrarCliente("testeteste.com", "senha1234", "nomeC", "SobreNome", "F", "111.111.111-11", "11/11/2000", "(11)1111-1111", "(11)1111-1111", "(11)1111-1111", true, "logadouro", "1", "complemento", "Bairro", "São Paulo", "SP", "11111-010", "referenci");
     }
}
