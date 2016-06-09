package newstime.teste;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import newstime.controle.*;
import newstime.entidade.Livro;
import newstime.excecao.BancoException;


/**
 *
 * @author Usuario
 */
public class TesteControleBusca {
    public static void main(String[] args){
        ControleBusca busca = new ControleBusca();
        ArrayList<Livro> resultadosBusca = new ArrayList<>();
        try {
           
            busca.fazerBusca("artes", 0, 1);
            //controleB.fazerBusca("1", 1, 1);
        } catch (BancoException ex) {
            Logger.getLogger(TesteControleBusca.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        resultadosBusca =  busca.getResultadosBusca();
        
        for(Livro x:resultadosBusca) {
            System.out.println("______________________________________");
            System.out.println(x.getTitulo());
            System.out.println(x.getAutor());
            System.out.println(x.getEditora());
        }
    }
    
    
 
}
