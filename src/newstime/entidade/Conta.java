package newstime.entidade;

import java.util.Date;
import newstime.DAO.*;
import newstime.excecao.*;

/**
 * Classe de entidade que representa a conta de um cliente da livraria
 * @author Ian Melo
 */
public class Conta {
    /**
     * Cliente da conta
     */
    private static Cliente cliente;
    /**
     * Data e hora de entrada
     */
    private static Date dataHoraEntrada;
    /**
     * Data e hora de saída
     */
    private static Date dataHoraSaida;
    
    /**
     * Cadastra o cliente e o loga automaticamente
     * <br/>É necessário que ele não tenha conta (definido pelo e-mail)
     * @param cliente Cliente da conta
     * @throws newstime.excecao.NegocioException Caso o já tenha conta
     * @throws newstime.excecao.BancoException Caso dê algum erro no cadastro
     */
    public static void cadastrar(Cliente cliente) throws NegocioException, BancoException {
        BancoDados banco = new BancoDados();
        ClienteDAO cDao = new ClienteDAO(banco);
        EnderecoDAO eDao = new EnderecoDAO(banco);
        Endereco en = cliente.getEndereco();
        //Buscar o cliente
        try {
            cliente = cDao.buscar(cliente);
            //Foi encontrado o cliente, portanto não poderá cadastrar
            throw new NegocioException("Não é possível cadastrar, pois já existe uma conta com esse e-mail.");
        } catch(BancoException ex) {}
        /*Não foi encontrado, prossegue*/
        //Insere endereço e busca ID
        eDao.inserir(en);
        en = eDao.buscar(en);
        //Agrega endereço com ID ao cliente e o insere
        cliente.setEndereco(en);
        cDao.inserir(cliente);
        //Busca cliente com o ID
        cliente = cDao.buscar(cliente);
        //Reagrega endereço
        cliente.setEndereco(en);
        //Define cliente na conta e horário (login)
        Conta.setCliente(cliente);
        Conta.setDataHoraEntrada(new Date());
    }
    /**
     * Loga o cliente na conta
     * @param cliente Cliente da conta
     * @throws newstime.excecao.NegocioException Caso não bata o usuário e senha
     * @throws newstime.excecao.BancoException Caso dê algum erro na busca
     */
    public static void logar(Cliente cliente) throws NegocioException, BancoException {
        BancoDados banco = new BancoDados();
        ClienteDAO cDao = new ClienteDAO(banco);
        EnderecoDAO eDao = new EnderecoDAO(banco);
        Endereco en = new Endereco();
        String senha = cliente.getSenha();
        //Verifica se já está cadastrado
        if(Conta.getCliente()!= null)
            throw new NegocioException("Você já está logado");
        
        //Busca o cliente
        try {
            cliente = cDao.buscar(cliente);
        } catch(BancoException ex) {
            //Não foi encontrado o cliente, portanto não poderá logar
            throw new NegocioException("Usuário ou senha incorretos.");
        }
        /*Foi encontrado, prossegue*/
        //Verifica se senha bate, se não interrompe
        if(!senha.equals(cliente.getSenha()))
            throw new NegocioException("Usuário ou senha incorretos.");
        //Busca endereço por ID
        en.setID(cliente.getID_ENDERECO());
        en = eDao.buscarId(en);
        //Agrega endereço com ID ao cliente (também com ID)
        cliente.setEndereco(en);
        //Define cliente na conta e horário (login)
        Conta.setCliente(cliente);
        Conta.setDataHoraEntrada(new Date());
    }
    /**
     * Desloga o cliente da conta
     */
    public static void deslogar() {
        Conta.cliente = null;
        Conta.dataHoraEntrada = null;
        Conta.dataHoraSaida = null;
    }
    
    //GETTERS SETTERS
    /**
     * Retorna o cliente da conta
     * @return Cliente da conta
     */
    public static Cliente getCliente() {
        return cliente;
    }
    /**
     * Define o cliente da conta
     * @param cliente Cliente da conta
     */
    public static void setCliente(Cliente cliente) {
        Conta.cliente = cliente;
    }
    /**
     * Retorna a data e hora de entrada
     * @return Data e hora de entrada
     */
    public static Date getDataHoraEntrada() {
        return dataHoraEntrada;
    }
    /**
     * Define a data e hora de entrada
     * @param dataHoraEntrada Data e hora de entrada
     */
    public static void setDataHoraEntrada(Date dataHoraEntrada) {
        Conta.dataHoraEntrada = dataHoraEntrada;
    }
    /**
     * Retorna a data e hora de saída
     * @return Data e hora de saída
     */
    public static Date getDataHoraSaida() {
        return dataHoraSaida;
    }
    /**
     * Define a data e hora de saída
     * @param dataHoraSaida Data e hora de saída
     */
    public static void setDataHoraSaida(Date dataHoraSaida) {
        Conta.dataHoraSaida = dataHoraSaida;
    }
}
