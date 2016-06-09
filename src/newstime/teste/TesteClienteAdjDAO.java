package newstime.teste;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import newstime.excecao.*;
import newstime.entidade.*;
import newstime.DAO.*;

/**
 * Classe de teste de DAO de Clientes e adjacentes
 * @author Ian Melo
 */
public class TesteClienteAdjDAO {
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
        
        /*DAO*/
        BancoDados banco = new BancoDados();
        ClienteDAO cDao = new ClienteDAO(banco);
        EnderecoDAO enDao = new EnderecoDAO(banco);
        
        try {
            enDao.inserir(e);
            e = enDao.buscar(e);
            
            c.setEndereco(e);
            cDao.inserir(c);
            c = cDao.buscar(c);
            
            c.setEndereco(e);
            enDao.alterar(e);
            cDao.alterar(c);
            
            enDao.excluir(e);
            cDao.excluir(c);
            
        } catch (BancoException ex) {
            Logger.getLogger(TesteClienteAdjDAO.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
    }
}
