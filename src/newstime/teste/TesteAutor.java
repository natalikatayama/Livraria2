package newstime.teste;

import java.util.Date;
import newstime.entidade.*;

/**
 * Classe de teste de autor
 * @author Ian Melo
 */
public class TesteAutor {
    public static void main(String[] args) {
        Autor a = new Autor();
        
        //Definição
        a.setCodigo("FLMQS");
        a.setNome("Floriano Marquendes");
        a.setDataNasci(new Date(1920 - 1900,11,21)); //O Date começa a contar à partir de 1900, portanto sendo necessário a subtração desse valor
        a.setDataMorte(new Date(1997 - 1900,03,15)); //Ex.: 1920 --> 20; 1997 --> 97; 2005 --> 105
        a.setLocalNasci("São Pedro, MG");
        a.setLocalMorte("Rio de Janeiro, RJ");
        
        //Exibição
        System.out.println(a.getCodigo());
        System.out.println(a.getNome());
        System.out.println(a.getDataNasci());
        System.out.println(a.getLocalNasci());
        System.out.println(a.getDataMorte());
        System.out.println(a.getLocalMorte());
    }
}
