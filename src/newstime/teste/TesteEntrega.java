package newstime.teste;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import newstime.entidade.Entrega;
import newstime.excecao.*;

/**
 * Classe de teste de entrega
 * @author Ian Melo
 */
public class TesteEntrega {
    public static void main(String[] args) {
        Entrega e1 = new Entrega();
        //Cria entrega não agendada
        try {
            e1.criarEntrega(Entrega.TipoEntrega.RAPIDA);
        } catch (NegocioException ex) {
            Logger.getLogger(TesteEntrega.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Entrega e2 = new Entrega();
        //Cria entrega agendada
        try {
            e2.criarEntregaAgendada(new Date(116,4,20)); // 20 de Maio de 2016
        } catch (NegocioException ex) {
            Logger.getLogger(TesteEntrega.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
        //Exibe entregas (1)
        System.out.println(e1.getDataEntrega());
        System.out.println(e1.getPreco());
        System.out.println(e1.getTipo());
        
        //Exibe entregas (2)
        System.out.println(e2.getDataEntrega());
        System.out.println(e2.getPreco());
        System.out.println(e2.getTipo());
    }
}
