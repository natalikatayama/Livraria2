//TODO: Implementar busca por pedidos;
package newstime.controle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import newstime.entidade.Cliente;
import newstime.entidade.Conta;
import newstime.entidade.Endereco;
import newstime.entidade.Venda;
import newstime.excecao.BancoException;
import newstime.excecao.FormatacaoIncorretaException;
import newstime.excecao.NegocioException;

/**
 * Classe de controle das atividades de cadastro e conta de cliente da livraria
 * @author Ian Melo
 */
public class ControleContaCliente {
    /**
     * Cria uma conta de cliente da livraria
     * @param email E-mail do cliente
     * @param senha Senha do cliente
     * @param nome Nome do cliente
     * @param sobrenome Sobrenome do cliente
     * @param sexo Sexo do cliente
     * @param cpf CPF do cliente
     * @param dataNascimento Data de nascimento do cliente
     * @param telefone Telefone do cliente
     * @param telefoneAlt Telefone alternativo do cliente
     * @param celular Celular do cliente
     * @param promocional Se cliente deseja mensagens promocionais
     * @param logadouro Logradouro do endereço do cliente
     * @param numero Número do endereço do cliente
     * @param complemento Complemento do endereço do cliente
     * @param bairro Bairro do endereço do cliente
     * @param cidade Cidade do endereço do cliente
     * @param estado Estado do endereço do cliente
     * @param cep CEP do endereço do cliente
     * @param referencia Referência do endereço do cliente
     */
    public void cadastrarCliente(String email, String senha, String nome, 
        String sobrenome, String sexo, String cpf, String dataNascimento, 
        String telefone, String telefoneAlt, String celular,
        boolean promocional, String logadouro, String numero, String complemento, 
        String bairro, String cidade, String estado, String cep, String referencia) {
        
        try {
            //Definição de endereço
            Endereco enderecoC = new Endereco();
            enderecoC.setBairro(bairro);
            enderecoC.setCep(cep);
            enderecoC.setCidade(cidade);
            enderecoC.setComplemento(complemento);
            enderecoC.setEstado(estado);
            enderecoC.setLogradouro(logadouro);
            enderecoC.setNumero(numero);
            enderecoC.setReferencia(referencia);
            //Definição de cliente
            Cliente cliente = new Cliente();
            cliente.setEmail(email);
            cliente.setSenha(senha);
            cliente.setNome(nome);
            cliente.setSobrenome(sobrenome);
            cliente.setSexo(sexo);
            cliente.setCpf(cpf);
            cliente.setTelefone(telefone);
            cliente.setTelefoneAlt(telefoneAlt);
            cliente.setCelular(celular);
            cliente.setPromocional(promocional);
            //Definição de data de nascimento
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date dataNasc = formato.parse(dataNascimento);
            cliente.setDataNascimento(dataNasc);
            //Definição de endereço em cliente
            cliente.setEndereco(enderecoC);
            //Cadastro do cliente
            Conta.cadastrar(cliente);
        } catch (FormatacaoIncorretaException | ParseException | NegocioException | BancoException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    /**
     * Realiza login na conta de cliente
     * @param email E-mail de conta de cliente
     * @param senha Senha de acesso de cliente
     */
    public void logar(String email, String senha){
           try {
            Cliente c = new Cliente();
            c.setEmail(email);
            c.setSenha(senha);
            Conta.logar(c);
        } catch (BancoException | NegocioException | FormatacaoIncorretaException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    /**
     * Sai da conta de cliente
     */
    public void deslogar() {
        Conta.deslogar();
    }
    /**
     * Busca os status do pedido à partir das vendas feitas pelo usuário
     * <br/>Precisa de usuário definido
     * @return
     */
    public ArrayList<Venda> buscarStatusPedidos() {
        //Verifica se o cliente existe e para, caso não haja
        if(!this.verificarConta()) {
            JOptionPane.showMessageDialog(null, "Faça o login ou cadastre-se antes.");
            return null;
        }
        
        //IMPLEMENTAR
        return null;
    }
    /**
     * Verifica se o cliente está logado
     * @return 
     */
    private boolean verificarConta() {
        return (Conta.getCliente() != null);
    }
}
