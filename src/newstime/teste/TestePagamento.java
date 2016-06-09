package newstime.teste;

import java.util.logging.Level;
import java.util.logging.Logger;
import newstime.entidade.Pagamento;
import newstime.entidade.Pagamento.TipoPagamento;
import newstime.excecao.NegocioException;

/**
 * Classe de teste de pagamento
 * @author Ian Melo
 */
public class TestePagamento {
    public static void main(String[] args) {
        
        Pagamento p = new Pagamento();
        //Definição de valores
        try {
            p.setForma(TipoPagamento.DEBITO);
            p.setNumParcelas(5);
            p.setParcelaRestante(5);
        } catch (NegocioException ex) {
            Logger.getLogger(TestePagamento.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Exibição
        System.out.println(p.getForma());
        System.out.println(p.getNumParcelas());
        System.out.println(p.getParcelaRestante());
    }
}
