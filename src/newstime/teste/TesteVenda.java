package newstime.teste;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import newstime.entidade.*;
import newstime.excecao.*;

/**
 * Classe de teste de venda
 * @author Ian Melo
 */
public class TesteVenda {
    public static void main(String[] args) {
        //Define uma conta
        Conta.setCliente(new Cliente());
        
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
    }
}
