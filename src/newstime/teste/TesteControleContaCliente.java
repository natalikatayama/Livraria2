package newstime.teste;

import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import newstime.controle.ControleContaCliente;
import newstime.entidade.Endereco;
import newstime.excecao.BancoException;
import newstime.excecao.FormatacaoIncorretaException;
import newstime.excecao.NegocioException;


/**
 *
 * @author Fabio
 */
public class TesteControleContaCliente {
     public static void main(String[] args){
         ControleContaCliente controleCliente = new  ControleContaCliente();
         
         try {
     
            controleCliente.cadastrarCliente("testeteste.com", "senha1234", "nomeC", "SobreNome", "F", "111.111.111-11", "11/11/2000", "(11)1111-1111", "(11)1111-1111", "(11)1111-1111", "endereco", true, "logadouro", "1", "complemento", "Bairro", "São Paulo", "SP", "11111-010", "referenci");
         
         
         
         
         } catch (BancoException ex) {
             JOptionPane.showMessageDialog(null, "teste1" + ex);
             Logger.getLogger(TesteControleContaCliente.class.getName()).log(Level.SEVERE, null, ex);
             
         }catch (NegocioException ex) {
             JOptionPane.showMessageDialog(null, "teste2" + ex.toString());
             JOptionPane.showMessageDialog(null, "teste 2 " + ex.getMessage());
             Logger.getLogger(TesteControleContaCliente.class.getName()).log(Level.SEVERE, null, ex);
             
         }catch (ParseException ex) {
             JOptionPane.showMessageDialog(null, "teste3" + ex);
             Logger.getLogger(TesteControleContaCliente.class.getName()).log(Level.SEVERE, null, ex);
             
         }catch (FormatacaoIncorretaException ex) {
             JOptionPane.showMessageDialog(null, "teste4" + ex);
             Logger.getLogger(TesteControleContaCliente.class.getName()).log(Level.SEVERE, null, ex);
             
         }
     }
}
