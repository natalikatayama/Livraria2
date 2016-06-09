package newstime.teste;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import newstime.DAO.*;
import newstime.entidade.*;
import newstime.excecao.*;

/**
 * Classe de teste de DAO de Venda e adjacentes
 * <br/>Cuidado, está instável
 * @author Ian Melo
 */
public class TesteVendaAdjDAO {
    public static void main(String[] args) {
        //Define uma conta
        Conta.setCliente(new Cliente());
        Conta.getCliente().setID(1);
        
        Autor a = new Autor();
        //Definição do autor
        a.setNome("Floriano Marquendes");
        a.setDataNasci(new Date(1920 - 1900,11,21)); //O Date começa a contar à partir de 1900, portanto sendo necessário a subtração desse valor
        a.setDataMorte(new Date(1997 - 1900,03,15)); //Ex.: 1920 --> 20; 1997 --> 97; 2005 --> 105
        a.setLocalNasci("São Pedro, MG");
        a.setLocalMorte("Rio de Janeiro, RJ");
        
        Editora e = new Editora();
        //Definição da editora
        try {
            e.setCnpj("12.123.123/1234-56");
            e.setEndereco("R. dos Lençóis, 142, São Beto, São Paulo, SP");
            e.setNome("Editora Manzollini");
            e.setTelefone("(11)1234-1234");
        } catch (FormatacaoIncorretaException ex) {
            Logger.getLogger(TesteEditora.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
        Livro l = new Livro();
        //Definição do livro
        try {
            l.setAutor(a);
            l.setEditora(e);
            l.setIsbn("1234567890123");
            l.setTitulo("A Classe Insecta");
            l.setResumo("Aqui vai o resumo...");
            l.setSumario("Aqui vai o sumário...");
            l.setAnoPublicacao(1982);
            l.setCategoria(Livro.CategoriaLivro.CIENCIAS_BIO);
            l.setMargemLucro(25.0f);
            l.setPrecoCusto(25.0f);
            l.setPrecoVenda(50.0f);
            l.setPrecoOferta(35.0f);
            l.setQtdEstoque(500);
            l.setDigital(false);
            l.setOferta(true);
        } catch (NegocioException ex) {
            Logger.getLogger(TesteLivro.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
        ItemPedido i = new ItemPedido();
        ItemPedido i2 = new ItemPedido();
        ItemPedido i3 = new ItemPedido();
        //Definição do item
        try {
            i.definirItemPedido(l, 20);
            i2.definirItemPedido(l, 5);
            i3.definirItemPedido(l, 22);
        } catch (NegocioException ex) {
            Logger.getLogger(TesteItem.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
        //Definição da lista de itens
        ArrayList<ItemPedido> itens = new ArrayList<>();
        itens.add(i);
        itens.add(i2);
        itens.add(i3);
        
        //Define pedido
        Pedido p= new Pedido();
        try {
            p.abrirPedido();
            p.setCliente(Conta.getCliente());
            p.setID_CLIENTE(1);
            p.setDataHora(new Date());
            p.setItensPedido(itens);
        } catch (NegocioException ex) {
            Logger.getLogger(TestePedido.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
        Entrega e1 = new Entrega();
        //Cria entrega não agendada
        try {
            e1.criarEntrega(Entrega.TipoEntrega.ECONOMICA);
        } catch (NegocioException ex) {
            Logger.getLogger(TesteEntrega.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
        Pagamento pg = new Pagamento();
        //Definição de valores
        try {
            pg.setForma(Pagamento.TipoPagamento.DEBITO);
            pg.setNumParcelas(3);
            pg.setParcelaRestante(3);
        } catch (NegocioException ex) {
            Logger.getLogger(TestePagamento.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
        Endereco en = new Endereco();
        //Definição do endereço
        try {
            en.setLogradouro("Rua Martinez");
            en.setNumero("223");
            en.setComplemento("Apt. 27");
            en.setCep("01223-998");
            en.setBairro("Andrade");
            en.setCidade("São Roque");
            en.setEstado("RJ");
            en.setReferencia("Uma quadra antes do estádio");
        } catch (FormatacaoIncorretaException ex) {
            Logger.getLogger(TesteEndereco.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
        Venda v = new Venda();
        //Definição de venda
        v.abrirVenda(p);
        v.definirEndereco(en);
        v.definirEntrega(e1);
        v.definirPagamento(pg);
        
        
        //Exibir situação da venda
        System.out.println("--------------------------");
        //Soma total
        float soma = 0f;
        for(ItemPedido x : v.getPedido().getItensPedido())
            soma += x.getSubtotal();
        soma += v.getEntrega().getPreco();
        System.out.println(soma);
        //Parcelas restantes
        System.out.println(v.getPagamento().getParcelaRestante());
        //Status de pago
        System.out.println(v.getPago().toString());
        //Status de entrega
        System.out.println(v.getStatus().toString());
        
        //Ação 1
        try {
            v.concluirParcela();
            //v.concluirEntrega();
        } catch (NegocioException ex) {
            Logger.getLogger(TesteVenda.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
        //Exibir situação da venda
        System.out.println("--------------------------");
        //Soma total
        soma = 0f;
        for(ItemPedido x : v.getPedido().getItensPedido())
            soma += x.getSubtotal();
        soma += v.getEntrega().getPreco();
        System.out.println(soma);
        //Parcelas restantes
        System.out.println(v.getPagamento().getParcelaRestante());
        //Status de pago
        System.out.println(v.getPago().toString());
        //Status de entrega
        System.out.println(v.getStatus().toString());
        
        //Ação 2
        try {
            v.concluirParcela();
            v.concluirEntrega();
        } catch (NegocioException ex) {
            Logger.getLogger(TesteVenda.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
        //Exibir situação da venda
        System.out.println("--------------------------");
        //Soma total
        soma = 0f;
        for(ItemPedido x : v.getPedido().getItensPedido())
            soma += x.getSubtotal();
        soma += v.getEntrega().getPreco();
        System.out.println(soma);
        //Parcelas restantes
        System.out.println(v.getPagamento().getParcelaRestante());
        //Status de pago
        System.out.println(v.getPago().toString());
        //Status de entrega
        System.out.println(v.getStatus().toString());
        
        //Ação 3
        try {
            v.concluirParcela();
        } catch (NegocioException ex) {
            Logger.getLogger(TesteVenda.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
        //Exibir situação da venda
        System.out.println("--------------------------");
        //Soma total
        soma = 0f;
        for(ItemPedido x : v.getPedido().getItensPedido())
            soma += x.getSubtotal();
        soma += v.getEntrega().getPreco();
        System.out.println(soma);
        //Parcelas restantes
        System.out.println(v.getPagamento().getParcelaRestante());
        //Status de pago
        System.out.println(v.getPago().toString());
        //Status de entrega
        System.out.println(v.getStatus().toString());
        
        /*DAO*/
        BancoDados banco = new BancoDados();
        AutorDAO aDao = new AutorDAO(banco);
        EditoraDAO edDao = new EditoraDAO(banco);
        LivroDAO lDao = new LivroDAO(banco);
        ItemPedidoDAO iDao = new ItemPedidoDAO(banco);
        PedidoDAO peDao = new PedidoDAO(banco);
        EnderecoDAO enDao = new EnderecoDAO(banco);
        EntregaDAO etDao = new EntregaDAO(banco);
        PagamentoDAO paDao = new PagamentoDAO(banco);
        VendaDAO vDao = new VendaDAO(banco);
        ArrayList<ItemPedido> ixxx = null;
        ArrayList<Pedido> pedidos = null;
        ArrayList<Endereco> enderecos = null;
        ArrayList<Entrega> entregas = null;
        ArrayList<Pagamento> pagamentos = null;
        ArrayList<Venda> vendas = null;
        
        //INSERÇÃO
        Pedido p2;
        try {
            //Endereço
            enDao.inserir(en);
            en = enDao.buscar(en);
            //Pagamento
            paDao.inserir(pg);
            pg = paDao.buscar(pg);
            //Entrega
            etDao.inserir(e1);
            e1 = etDao.buscar(e1);
            //Pedido
            peDao.inserir(p);
            p2 = peDao.buscar(p);
            //ItemPedido
            for(ItemPedido xx : p.getItensPedido()) {
                xx.setID_LIVRO(1);
                xx.setID_PEDIDO(1);
                iDao.inserir(xx);
                break;
            }
            //Venda
            v.setEndereco(en);
            v.setPagamento(pg);
            v.setEntrega(e1);
            v.setPedido(p);
            vDao.inserir(v);
            v = vDao.buscar(v);
            
            ixxx = (ArrayList<ItemPedido>) iDao.listar();
            pedidos = (ArrayList<Pedido>) peDao.listar();
            enderecos = (ArrayList<Endereco>) enDao.listar();
            entregas = (ArrayList<Entrega>) etDao.listar();
            pagamentos = (ArrayList<Pagamento>) paDao.listar();
            vendas = (ArrayList<Venda>) vDao.listar();
            System.out.println("\nInserido");
        } catch (BancoException ex) {
            Logger.getLogger(TesteVendaAdjDAO.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
        //ItemPedido
        for(ItemPedido x: ixxx) {
            System.out.println("---------------------------");
            System.out.println(x.getQuantidade());
            System.out.println(x.getSubtotal());
        }
        
        //Venda
        for(Venda x : vendas) {
            System.out.println("---------------------------");
            System.out.println(x.getPago());
            System.out.println(x.getStatus());
        }
        
        //Pedido
        for(Pedido x: pedidos) {
            System.out.println("---------------------------");
            System.out.println(x.getDataHora());
        }
        
        //Endereco
        for(Endereco x: enderecos) {
            System.out.println("---------------------------");
            System.out.println(x.getBairro());
            System.out.println(x.getCep());
            System.out.println(x.getCidade());
            System.out.println(x.getComplemento());
            System.out.println(x.getEstado());
            System.out.println(x.getLogradouro());
            System.out.println(x.getNumero());
            System.out.println(x.getReferencia());
        }
        
        //Entregas
        for(Entrega x : entregas) {
            System.out.println("---------------------------");
            System.out.println(x.getDataEntrega());
            System.out.println(x.getPreco());
            System.out.println(x.getTipo());
        }
        
        //Pagamentos
        for(Pagamento x : pagamentos) {
            System.out.println("---------------------------");
            System.out.println(x.getForma());
            System.out.println(x.getNumParcelas());
            System.out.println(x.getParcelaRestante());
        }
        
        //ALTERAÇÃO
        try {
            //Endereço
            en = enDao.buscarId(en);
            enDao.alterar(en);
            //Pagamento
            pg = paDao.buscarId(pg);
            paDao.alterar(pg);
            //Entrega
            e1 = etDao.buscarId(e1);
            etDao.alterar(e1);
            //Pedido
            p2 = peDao.buscarId(p2);
            peDao.alterar(p2);
            //Venda
            v.setEndereco(en);
            v.setPagamento(pg);
            v.setEntrega(e1);
            v.setPedido(p2);
            vDao.alterar(v);
            v = vDao.buscar(v);
            
            ixxx = (ArrayList<ItemPedido>) iDao.listarPedido(p); //Novo esquema
            pedidos = (ArrayList<Pedido>) peDao.listar();
            enderecos = (ArrayList<Endereco>) enDao.listar();
            entregas = (ArrayList<Entrega>) etDao.listar();
            pagamentos = (ArrayList<Pagamento>) paDao.listar();
            vendas = (ArrayList<Venda>) vDao.listar();
            System.out.println("\nAlterado");
        } catch (BancoException ex) {
            Logger.getLogger(TesteVendaAdjDAO.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
        //ItemPedido
        for(ItemPedido x: ixxx) {
            System.out.println("---------------------------");
            System.out.println(x.getQuantidade());
            System.out.println(x.getSubtotal());
        }
        
        //Venda
        for(Venda x : vendas) {
            System.out.println("---------------------------");
            System.out.println(x.getPago());
            System.out.println(x.getStatus());
        }
        
        //Pedido
        for(Pedido x: pedidos) {
            System.out.println("---------------------------");
            System.out.println(x.getDataHora());
        }
        
        //Endereco
        for(Endereco x: enderecos) {
            System.out.println("---------------------------");
            System.out.println(x.getBairro());
            System.out.println(x.getCep());
            System.out.println(x.getCidade());
            System.out.println(x.getComplemento());
            System.out.println(x.getEstado());
            System.out.println(x.getLogradouro());
            System.out.println(x.getNumero());
            System.out.println(x.getReferencia());
        }
        
        //Entregas
        for(Entrega x : entregas) {
            System.out.println("---------------------------");
            System.out.println(x.getDataEntrega());
            System.out.println(x.getPreco());
            System.out.println(x.getTipo());
        }
        
        //Pagamentos
        for(Pagamento x : pagamentos) {
            System.out.println("---------------------------");
            System.out.println(x.getForma());
            System.out.println(x.getNumParcelas());
            System.out.println(x.getParcelaRestante());
        }
        
        //EXCLUSÃO
        try {
            //Endereço
            enDao.excluir(en);
            //Pagamento
            paDao.excluir(pg);
            //Entrega
            etDao.excluir(e1);
            //Pedido
            peDao.excluir(p2);
            //ItemPedido
            iDao.excluirPedido(p);
            //Venda
            vDao.excluir(v);
            
            ixxx = (ArrayList<ItemPedido>) iDao.listar();
            pedidos = (ArrayList<Pedido>) peDao.listar();
            enderecos = (ArrayList<Endereco>) enDao.listar();
            entregas = (ArrayList<Entrega>) etDao.listar();
            pagamentos = (ArrayList<Pagamento>) paDao.listar();
            vendas = (ArrayList<Venda>) vDao.listar();
            System.out.println("\nExcluido");
        } catch (BancoException ex) {
            Logger.getLogger(TesteVendaAdjDAO.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
        //ItemPedido
        for(ItemPedido x: ixxx) {
            System.out.println("---------------------------");
            System.out.println(x.getQuantidade());
            System.out.println(x.getSubtotal());
        }
        
        //Venda
        for(Venda x : vendas) {
            System.out.println("---------------------------");
            System.out.println(x.getPago());
            System.out.println(x.getStatus());
        }
        
        //Pedido
        for(Pedido x: pedidos) {
            System.out.println("---------------------------");
            System.out.println(x.getDataHora());
        }
        
        //Endereco
        for(Endereco x: enderecos) {
            System.out.println("---------------------------");
            System.out.println(x.getBairro());
            System.out.println(x.getCep());
            System.out.println(x.getCidade());
            System.out.println(x.getComplemento());
            System.out.println(x.getEstado());
            System.out.println(x.getLogradouro());
            System.out.println(x.getNumero());
            System.out.println(x.getReferencia());
        }
        
        //Entregas
        for(Entrega x : entregas) {
            System.out.println("---------------------------");
            System.out.println(x.getDataEntrega());
            System.out.println(x.getPreco());
            System.out.println(x.getTipo());
        }
        
        //Pagamentos
        for(Pagamento x : pagamentos) {
            System.out.println("---------------------------");
            System.out.println(x.getForma());
            System.out.println(x.getNumParcelas());
            System.out.println(x.getParcelaRestante());
        }
        
        try {
            v = vDao.buscar(v);
        } catch (BancoException ex) {
            Logger.getLogger(TesteVendaAdjDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
