package newstime.teste;

import newstime.entidade.*;
import newstime.excecao.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe de teste de cliente
 * @author voce
 */
public class TesteCliente {
    public static void main(String[] args) {
        Endereco e = new Endereco();
        //Definição do endereço
        try {
            e.setLogradouro("Rua Martinez");
            e.setNumero("223");
            e.setComplemento("Apt. 27");
            e.setCep("01223-998");
            e.setBairro("Andrade");
            e.setCidade("São Roque");
            e.setEstado("RJ");
            e.setReferencia("Uma quadra antes do estádio");
        } catch (FormatacaoIncorretaException ex) {
            Logger.getLogger(TesteEndereco.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
        Cliente c = new Cliente();
        //Define cliente
        try {    
            c.setEmail("joaomarques@gmail.com");
            c.setSenha("12345678");
            c.setNome("João");
            c.setSobrenome("Marques Dias");
            c.setSexo("M");
            c.setCpf("111.222.333-40");
            c.setDataNascimento(new Date(89,3,19));
            c.setTelefone("(11)4057-8866");
            c.setTelefoneAlt("(11)4056-1234");
            c.setCelular("(11)97022-1212");
            c.setEndereco(e);
        } catch (FormatacaoIncorretaException ex) {
            Logger.getLogger(TesteCliente.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
        //Exibe cliente
        System.out.println(c.getEmail());
        System.out.println(c.getSenha());
        System.out.println(c.getNome());
        System.out.println(c.getSobrenome());
        System.out.println(c.getSexo());
        System.out.println(c.getCpf());
        System.out.println(c.getDataNascimento());
        System.out.println(c.getTelefone());
        System.out.println(c.getTelefoneAlt());
        System.out.println(c.getCelular());
        System.out.println(c.getEndereco());
    }
}
