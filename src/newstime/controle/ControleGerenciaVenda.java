package newstime.controle;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import newstime.DAO.BancoDados;
import newstime.DAO.EnderecoDAO;
import newstime.DAO.EntregaDAO;
import newstime.DAO.ItemPedidoDAO;
import newstime.DAO.LivroDAO;
import newstime.DAO.PagamentoDAO;
import newstime.DAO.PedidoDAO;
import newstime.DAO.VendaDAO;
import newstime.entidade.ContaRestrita;
import newstime.entidade.Endereco;
import newstime.entidade.Entrega;
import newstime.entidade.ItemPedido;
import newstime.entidade.Livro;
import newstime.entidade.Pagamento;
import newstime.entidade.Pedido;
import newstime.entidade.Venda;
import newstime.excecao.BancoException;
import newstime.excecao.NegocioException;

/**
 * Classe para controle das vendas pelo lado do funcionário
 * @author Fábio
 */
public class ControleGerenciaVenda {
    /**
     * Lista de vendas a interagir
     */
    private ArrayList<Venda> vendas = new ArrayList<>();
    
    /**
     * Realiza uma busca de todas as vendas / Atualiza o resultado de busca das vendas
     * <br/>Aqui os erros estão tratados
     * @return Lista com todas as vendas e seus dajos subjacentes 
     */
    public ArrayList<Venda> buscarVendas(){
        try {
            BancoDados bd = new BancoDados();
            VendaDAO vDao = new VendaDAO(bd);
            EnderecoDAO edDao = new EnderecoDAO(bd);
            PagamentoDAO pgDao = new PagamentoDAO(bd);
            EntregaDAO etDao = new EntregaDAO(bd);
            PedidoDAO pDao = new PedidoDAO(bd);
            ItemPedidoDAO iDao = new ItemPedidoDAO(bd);
            LivroDAO lDao = new LivroDAO(bd);
            //Lista as vendas
            vendas = (ArrayList<Venda>) vDao.listar();
            //Define os subjacentes
            Endereco edx = new Endereco();
            Pagamento pgx = new Pagamento();
            Entrega etx = new Entrega();
            Pedido px = new Pedido();
            Livro lx = new Livro();
            for(int i = 0;i < vendas.size();i++) {
                //Endereço
                edx.setID(vendas.get(i).getID_ENDERECO());
                edx = edDao.buscarId(edx);
                vendas.get(i).setEndereco(edx);
                //Pagamento
                pgx.setID(vendas.get(i).getID_PAGAMENTO());
                pgx = pgDao.buscarId(pgx);
                vendas.get(i).setPagamento(pgx);
                //Entrega
                etx.setID(vendas.get(i).getID_ENTREGA());
                etx = etDao.buscarId(etx);
                vendas.get(i).setEntrega(etx);
                //Pedido
                px.setID(vendas.get(i).getID_PEDIDO());
                px = pDao.buscarId(px);
                vendas.get(i).setPedido(px);
                //Itens do pedido
                vendas.get(i).getPedido().setItensPedido((ArrayList<ItemPedido>) iDao.listarPedido(px));
                //Livros de cada item do pedido
                for(int j = 0;j < vendas.get(i).getPedido().getItensPedido().size();j++) {
                    lx.setID(vendas.get(i).getPedido().getItensPedido().get(j).getID_LIVRO());
                    lx = lDao.buscarId(lx);
                    vendas.get(i).getPedido().getItensPedido().get(j).setLivro(lx);
                }
            }
            return vendas;
        } catch (BancoException ex) {
            JOptionPane.showMessageDialog(null,"Houve um erro ao buscar as vendas: " + ex.getMessage());
            return null;
        }
    }
    /**
     * Conclui a parcela de um livro escolhido e retorna a tabela de vendas atualizada
     * <br/>Também pode alterar informações do livro (ex.: quantidade...)
     * @param indice Índice da lista de vendas
     * @return Lista de vendas atualizadas
     */
    public ArrayList<Venda> concluirParcela(int indice){
        BancoDados bd = new BancoDados();
        VendaDAO vDao = new VendaDAO(bd);
        PagamentoDAO pgDao = new PagamentoDAO(bd);
        LivroDAO lDao = new LivroDAO(bd);
        Venda vx, vant;
        Pagamento pgx;
        Livro lx;
        try {
            //Obtém a venda
            vx = vendas.get(indice);
            vant = vendas.get(indice); //Anterior
            //Conclui sua parcela
            vx.concluirParcela();
            //Verifica se o status anterior era de aguardo e então a situação dos livros dos itens do pedido da venda (houve mudança do estado)
            if(vant.getStatus() == Venda.StatusVenda.AGUARDO) {
                for(ItemPedido ix : vx.getPedido().getItensPedido()) {
                    lx = ix.getLivro();
                    //Diminui a quantidade e altera no banco
                    lx.setQtdEstoque(lx.getQtdEstoque() - ix.getQuantidade());
                    lDao.alterar(lx);
                }
            }
            //Salva pagamento
            pgx = vx.getPagamento();
            pgDao.alterar(pgx);
            //Salva venda
            vDao.alterar(vx);
            //Atualiza a lista de vendas e retorna-a
            return this.buscarVendas();
        } catch(ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(null, "Não existe a venda na posição indicada.");
            return this.buscarVendas();
        } catch(BancoException ex) {
            JOptionPane.showMessageDialog(null,"Houve um erro ao concliur parcela: " + ex.getMessage());
            return this.buscarVendas();
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(null,"Não foi possível concliur parcela: " + ex.getMessage());
            return this.buscarVendas();
        }
    }
    /**
     * Conclui a entrega da venda
     * @param indice Índice da lista de vendas
     * @return Lista de vendas atualizadas
     */
    public ArrayList<Venda> concluirEntrega(int indice){
        BancoDados bd = new BancoDados();
        VendaDAO vDao = new VendaDAO(bd);
        Venda vx;
        try {
            //Obtém a venda
            vx = vendas.get(indice);
            //Realiza a entrega
            vx.concluirEntrega();
            //Salva venda
            vDao.alterar(vx);
            //Atualiza a lista de vendas e retorna-a
            return this.buscarVendas();
        } catch(ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(null, "Não existe a venda na posição indicada.");
            return this.buscarVendas();
        } catch(BancoException ex) {
            JOptionPane.showMessageDialog(null,"Houve um erro ao concliur entrega: " + ex.getMessage());
            return this.buscarVendas();
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(null,"Não foi possível concliur entrega: " + ex.getMessage());
            return this.buscarVendas();
        }
    }
    /**
     * Exclui a venda
     * @param indice Índice da lista de vendas
     * @return Lista de vendas atualizadas
     */
    public ArrayList<Venda> excluirVenda(int indice) {
        BancoDados bd = new BancoDados();
        VendaDAO vDao = new VendaDAO(bd);
        EnderecoDAO edDao = new EnderecoDAO(bd);
        PagamentoDAO pgDao = new PagamentoDAO(bd);
        EntregaDAO etDao = new EntregaDAO(bd);
        PedidoDAO pDao = new PedidoDAO(bd);
        ItemPedidoDAO iDao = new ItemPedidoDAO(bd);
        Venda vx;
        Endereco edx;
        Pagamento pgx;
        Entrega etx;
        Pedido px;
        try {
            //Obtém a venda
            vx = vendas.get(indice);
            //Obtém o endereço
            edx = vx.getEndereco();
            //Obtém o pagamento
            pgx = vx.getPagamento();
            //Obtém a entrega
            etx = vx.getEntrega();
            //Obtém pedido
            px = vx.getPedido();
            
            //Excluir o endereço
            edDao.excluir(edx);
            //Exclui o pagamento
            pgDao.excluir(pgx);
            //Exclui a entrega
            etDao.excluir(etx);
            //Exclui os itens do pedido
            iDao.excluirPedido(px);
            //Exclui o pedido
            pDao.excluir(px);
            //Exclui a venda
            vDao.excluir(vx);
            
            //Atualiza a lista de vendas e retorna-a
            return this.buscarVendas();
        } catch(ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(null, "Não existe a venda na posição indicada.");
            return this.buscarVendas();
        } catch(BancoException ex) {
            JOptionPane.showMessageDialog(null,"Houve um erro ao concliur parcela: " + ex.getMessage());
            return this.buscarVendas();
        }
    }
    /**
     * Verifica a conta do funcionário, indicando se este está logado ou não
     * <br/>Seria interessante utilizar no início do uso da classe
     * @return true, se estiver logado
     * <br/>false, caso contrário
     */
    private boolean verificarConta(){
        return (ContaRestrita.getFuncionario() != null);
    }
}
