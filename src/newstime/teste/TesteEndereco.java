package newstime.teste;

import java.util.logging.Level;
import java.util.logging.Logger;
import newstime.entidade.*;
import newstime.excecao.*;

/**
 * Classe de teste de endereço
 * @author Ian Melo
 */
public class TesteEndereco {
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
        }
        
        //Exibição
        System.out.println(e.getLogradouro());
        System.out.println(e.getNumero());
        System.out.println(e.getComplemento());
        System.out.println(e.getCep());
        System.out.println(e.getBairro());
        System.out.println(e.getCidade());
        System.out.println(e.getEstado());
        System.out.println(e.getReferencia());
    }
}
