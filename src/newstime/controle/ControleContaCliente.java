//TODO: Implementar busca por pedidos;
package newstime.controle;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import newstime.DAO.BancoDados;
import newstime.DAO.EntregaDAO;
import newstime.DAO.ItemPedidoDAO;
import newstime.DAO.PedidoDAO;
import newstime.DAO.VendaDAO;
import newstime.entidade.Cliente;
import newstime.entidade.Conta;
import newstime.entidade.Endereco;
import newstime.entidade.Entrega;
import newstime.entidade.ItemPedido;
import newstime.entidade.Pedido;
import newstime.entidade.Venda;
import newstime.excecao.BancoException;
import newstime.excecao.FormatacaoIncorretaException;
import newstime.excecao.NegocioException;

/**
 * Classe de controle das atividades de cadastro e conta de cliente da livraria
 * @author Ian Melo
 */
public class ControleContaCliente {
    public String campoId;
    public String data;
    public String valorTotal;
    
    private ArrayList<ControleContaCliente> resultados;
    
    
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
     * <br/><br/>
     * São aceitos os seguintes critérios:<br/>
     * 1 = Todos os pedidos<br/>
     * 2 = Aguardando pagamento<br/>
     * 3 = Em pagamento<br/>
     * 4 = Pagamento concluído<br/>
     * 5 = Em processo de entrega<br/>
     * 6 = Entregue<br/>
     * 7 = Por ID
     * <br/><br/>
     * Precisa de usuário definido
     * @param criterio Critério de busca
     * @param id ID do pedido
     * @return Lista de pedidos de acordo com a busca
     */
    public ArrayList<ControleContaCliente> buscarStatusPedidos(int criterio, String id) {
        ArrayList<Venda> vList, result = new ArrayList<>(), r2 = new ArrayList<>();
        //Verifica se o cliente existe e para, caso não haja
        if(!this.verificarConta()) {
            JOptionPane.showMessageDialog(null, "Faça o login ou cadastre-se antes.");
            return null;
        }
        //Converte ID para int e para, caso não seja numérico
        int ID;
        try {
            ID = Integer.parseInt(id);
        } catch(NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Digite um número para ID.");
            return null;
        }
        
        /*Busca todas as vendas*/
        try {
            BancoDados bd = new BancoDados();
            VendaDAO vDao = new VendaDAO(bd);
            EntregaDAO etDao = new EntregaDAO(bd);
            PedidoDAO pDao = new PedidoDAO(bd);
            ItemPedidoDAO iDao = new ItemPedidoDAO(bd);
            //Lista as vendas
            vList = (ArrayList<Venda>) vDao.listar();
            //Define os subjacentes
            Entrega etx;
            Pedido px;
            for(int i = 0;i < vList.size();i++) {
                etx = new Entrega();
                px = new Pedido();
                //Entrega
                etx.setID(vList.get(i).getID_ENTREGA());
                etx = etDao.buscarId(etx);
                vList.get(i).setEntrega(etx);
                //Pedido
                px.setID(vList.get(i).getID_PEDIDO());
                px = pDao.buscarId(px);
                vList.get(i).setPedido(px);
                //Itens do pedido
                vList.get(i).getPedido().setItensPedido((ArrayList<ItemPedido>) iDao.listarPedido(px));
            }
        } catch (BancoException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return null;
        }
            
        /*Filtra pelo cliente*/
        for(Venda vx : vList) {
            if(vx.getPedido().getID_CLIENTE() == Conta.getCliente().getID())
                result.add(vx);
        }

        /*Filtra pelo critério e calcula valores*/
        switch(criterio) {
            case 1: //Todos os pedidos
                for(Venda vx : result)
                    r2.add(vx);
                break;
            case 2: //Aguardando pagamento
                for(Venda vx : result)
                    if(vx.getStatus() == Venda.StatusVenda.AGUARDO)
                        r2.add(vx);
                break;
            case 3: //Em pagamento
                for(Venda vx : result)
                    if(vx.getPago() == Venda.PagoVenda.PAGANDO)
                    r2.add(vx);
                break;
            case 4: //Pagamento concluído
                for(Venda vx : result)
                    if(vx.getPago() == Venda.PagoVenda.SIM)
                        r2.add(vx);
                break;
            case 5: //Em processo de entrega
                for(Venda vx : result)
                    if(vx.getStatus() == Venda.StatusVenda.ANDAMENTO)
                        r2.add(vx);
                break;
            case 6: //Entregue
                for(Venda vx : result)
                    if(vx.getStatus() == Venda.StatusVenda.CONCLUIDO)
                        r2.add(vx);
                break;
            case 7: //Por ID
                for(Venda vx : result)
                    if(vx.getPedido().getID() == ID)
                        r2.add(vx);
                break;
            default: //Opção inválida
                return null;
        }
        //Definição dos valores
        NumberFormat formato_grana = new DecimalFormat ("#,##0.00", new DecimalFormatSymbols (new Locale ("pt", "BR")));
        ControleContaCliente cx;
        Date dh;
        float valT;
        resultados = new ArrayList<>();
        for(Venda vx : r2) {
            cx = new ControleContaCliente();
            //Id
            cx.campoId = String.valueOf(vx.getPedido().getID());
            //Data e hora
            dh = vx.getPedido().getDataHora();
            cx.data = dh.getDay() + "/" + dh.getMonth() + "/" + (1900 + dh.getYear());
            cx.data += " " + dh.getHours() + ":" + dh.getMinutes();
            //Valor
            valT = 0.0f;
            valT += vx.getEntrega().getPreco(); //Valor da entrega
            for(ItemPedido ix : vx.getPedido().getItensPedido()) //Valor dos itens do pedido
                valT += ix.getSubtotal();
            cx.valorTotal = formato_grana.format(valT);
        }
        return resultados;
    }
    /**
     * Verifica se o cliente está logado
     * @return 
     */
    private boolean verificarConta() {
        return (Conta.getCliente() != null);
    }
}