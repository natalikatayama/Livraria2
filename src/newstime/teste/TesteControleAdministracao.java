package newstime.teste;
import java.util.Date;
import newstime.controle.ControleAdministracao;
import newstime.entidade.Autor;
import newstime.entidade.Editora;
import newstime.excecao.BancoException;
import newstime.excecao.FormatacaoIncorretaException;
import newstime.excecao.NegocioException;

/**
 *
 * @author Usuario
 */
public class TesteControleAdministracao {
    public static void main(String[] args) throws NegocioException, BancoException, FormatacaoIncorretaException {
            ControleAdministracao controleAdm = new ControleAdministracao();
            
            Autor au = new Autor();
            Editora editora = new Editora();
            /*
            au.setCodigo("TEST2");
            au.setNome("Mendez Souza");
            au.setDataNasci(new Date(1920 - 1900,11,21)); //O Date começa a contar à partir de 1900, portanto sendo necessário a subtração desse valor
            au.setDataMorte(new Date(1997 - 1900,03,15)); //Ex.: 1920 --> 20; 1997 --> 97; 2005 --> 105
            au.setLocalNasci("São Pedro, MG");
            au.setLocalMorte("São Paulo, SP");
            
            Editora ed = new Editora();
            ed.setCnpj("12.123.123/1234-56");
            ed.setEndereco("R. dos Lençóis, 142, São Beto, São Paulo, SP");
            ed.setNome("Editora Manzollini");
            ed.setTelefone("(11)1234-1234");
            */
            /*
            controleAdm.inserirLivro("1234567890001", "titulo2", "Floriano2 Marquendes2", "Editora Manzollini" , "2222", "categoria2", "resumo2", "sumario2", 50, "44.4", "5.5", "6.6", "7.7", true, true);
            
            controleAdm.buscarLivro("1234567890122");
            
            controleAdm.alterarLivro("1234567890001", "tiffffulo", "Floriano2 Marquendes2", "Editora Manzollini" , "2000", "categoria", "resumoalterado", "sumarialterado", 2500, "99.9", "9.9", "9.9", "9.9", true, true);
            //System.out.println("-----------------------------------------------");
            controleAdm.removerLivro("1234567890001");
            //controleAdm.buscarLivro("1234567890121");
            */
            
            

    }
    


    
}
