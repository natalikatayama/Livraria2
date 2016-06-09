package newstime.teste;

import java.util.logging.Level;
import java.util.logging.Logger;
import newstime.entidade.*;
import newstime.excecao.*;

/**
 * Classe de teste de editora
 * @author Ian Melo
 */
public class TesteEditora {
    public static void main(String[] args) {
        Editora e = new Editora();
        
        //Definição
        try {
            e.setCnpj("12.123.123/1234-56");
            e.setEndereco("R. dos Lençóis, 142, São Beto, São Paulo, SP");
            e.setNome("Editora Manzollini");
            e.setTelefone("(11)1234-1234");
        } catch (FormatacaoIncorretaException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(TesteEditora.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Exibição
        System.out.println(e.getNome());
        System.out.println(e.getCnpj());
        System.out.println(e.getEndereco());
        System.out.println(e.getTelefone());
    }
}
