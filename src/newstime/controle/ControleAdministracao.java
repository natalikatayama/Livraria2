//TODO: Testar;
package newstime.controle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import newstime.DAO.AutorDAO;
import newstime.DAO.BancoDados;
import newstime.DAO.EditoraDAO;
import newstime.DAO.LivroDAO;
import newstime.entidade.Autor;
import newstime.entidade.ContaRestrita;
import newstime.entidade.Editora;
import newstime.entidade.Funcionario;
import newstime.entidade.Livro;
import newstime.excecao.BancoException;
import newstime.excecao.FormatacaoIncorretaException;
import newstime.excecao.NegocioException;

/**
 * Classe de controle de CRUD de Livro, Autor e Editora
 * @author Ian Melo
 */
public class ControleAdministracao {
    public String L_titulo;
    public String L_anoPublicacao;
    public String L_resumo;
    public String L_sumario;
    public String L_formato;
    public String L_numPaginas;
    public String L_qtdEstoque;
    public String L_precoVenda;
    public String L_precoOferta;
    public String L_precoCusto;
    public String L_margemLucro;
    public String L_isbn;
    public int L_idAutor;
    public int L_idEditora;
    public String L_categoria;
    public boolean L_digital;
    public boolean L_oferta;
    
    private String A_nome;
    private String A_autor;
    private String A_localNasci;
    private String A_localMorte;
    private String A_dataNasci;
    private String A_dataMorte;
    private String E_cnpj;
    private String E_nome;
    private String E_endereco;
    private String E_teleone;
    
    //LOGIN-LOGOUT
    /**
     * Verifica se o login e senha do administrador batem, e faz login
     * @param login Login para acesso restrito
     * @param senha Senha para acesso restrito
     */
    public void realizarLogin(String login, String senha) {
        try {
            Funcionario f = new Funcionario();
            f.setLogin(login);
            f.setSenha(senha);
            ContaRestrita.logar(f);
        } catch (BancoException | NegocioException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    /**
     * Sai da conta de funcionário
     */
    public void deslogar() {
        ContaRestrita.deslogar();
    }
    /**
     * Verifica se funcionário está logado
     * @return 
     */
    private boolean verificarConta() {
        return (ContaRestrita.getFuncionario() != null);
    }
    
    //CRUD Livro
    /**
     * Cadastra o livro
     * <br/>Deve ser feito depois dos cadastros de autor e editora
     * @param isbn ISBN do livro
     * @param idAutor Identificador do autor do livro
     * @param idEditora Identificador da editora do livro
     * @param titulo Título do livro
     * @param anoPublicacao Ano de publicação do livro
     * @param categoria Categoria do livro
     * @param resumo Resumo do livro
     * @param sumario Sumário do livro
     * @param formato Formato do livro
     * @param numPaginas Número de páginas do livro
     * @param qtdEstoque Quantidade de estoque do livro
     * @param precoVenda Preço de venda do livro
     * @param precoOferta Preço de oferta do livro
     * @param precoCusto Preço de custo do livro
     * @param margemLucro Margem de lucro do livro
     * @param oferta Indica se o livro está em oferta
     * @param digital Indica se o livro é digital
     */
    public void inserirLivro(String isbn, String titulo, int idAutor, int idEditora, String anoPublicacao, String categoria, String resumo, String sumario, String formato, String numPaginas, String qtdEstoque, String precoVenda, String precoOferta, String precoCusto, String margemLucro, boolean oferta, boolean digital) {
        //Verifica se funcionário está logado, barrando caso não esteja
        if(!this.verificarConta()) {
            JOptionPane.showMessageDialog(null,"Acesso negado.");
            return;
        }
        
        BancoDados bd = new BancoDados();
        LivroDAO lDao = new LivroDAO(bd);
        AutorDAO aDao = new AutorDAO(bd);
        EditoraDAO eDao = new EditoraDAO(bd);
        
        Livro livro = new Livro();
        //Busca livro, caso encontre, pára
        try {
            livro.setIsbn(isbn);
            lDao.buscar(livro);
            JOptionPane.showMessageDialog(null,"Esse livro de ISBN definido já existe.");
            return;
        } catch (BancoException ex) { }
        
        Autor autor = new Autor();
        //Busca por autor
        try {
            autor.setID(idAutor);
            autor = aDao.buscarId(autor);
        } catch (BancoException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return;
        }
        
        Editora editora = new Editora();
        //Busca por editora
        try {
            editora.setID(idEditora);
            editora = eDao.buscarId(editora);
        } catch (BancoException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return;
        }

        //Define e insere livro
        try {
            livro.setIsbn(isbn);
            livro.setTitulo(titulo);
            livro.setAnoPublicacao(Integer.parseInt(anoPublicacao));
            livro.setCategoria(Livro.CategoriaLivro.valueOf(categoria));
            livro.setResumo(resumo);
            livro.setSumario(sumario);
            livro.setFormato(Livro.FormatoLivro.valueOf(formato));
            livro.setNumPaginas(Integer.parseInt(numPaginas));
            livro.setQtdEstoque(Integer.parseInt(qtdEstoque));
            livro.setPrecoVenda(Float.parseFloat(precoVenda));
            livro.setPrecoOferta(Float.parseFloat(precoOferta));
            livro.setPrecoCusto(Float.parseFloat(precoCusto));
            livro.setMargemLucro(Float.parseFloat(margemLucro));
            livro.setOferta(oferta);
            livro.setDigital(digital);
            livro.setAutor(autor); //Autor
            livro.setEditora(editora); //Editora
            
            lDao.inserir(livro);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null,"Não coloque texto em campo numérico. Utilize '.' como divisor decimal.");
        } catch (NegocioException | BancoException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
    }
    /**
     * Altera o livro
     * <br/>Deve ser feito depois dos cadastros de autor e editora
     * @param isbn ISBN do livro
     * @param idAutor Identificador do autor do livro
     * @param idEditora Identificador da editora do livro
     * @param titulo Título do livro
     * @param anoPublicacao Ano de publicação do livro
     * @param categoria Categoria do livro
     * @param resumo Resumo do livro
     * @param sumario Sumário do livro
     * @param formato Formato do livro
     * @param numPaginas Número de páginas do livro
     * @param qtdEstoque Quantidade de estoque do livro
     * @param precoVenda Preço de venda do livro
     * @param precoOferta Preço de oferta do livro
     * @param precoCusto Preço de custo do livro
     * @param margemLucro Margem de lucro do livro
     * @param oferta Indica se o livro está em oferta
     * @param digital Indica se o livro é digital
     */
    public void alterarLivro(String isbn, String titulo, int idAutor, int idEditora, String anoPublicacao, String categoria, String resumo, String sumario, String formato, String numPaginas, String qtdEstoque, String precoVenda, String precoOferta, String precoCusto, String margemLucro, boolean oferta, boolean digital) {
        //Verifica se funcionário está logado, barrando caso não esteja
        if(!this.verificarConta()) {
            JOptionPane.showMessageDialog(null,"Acesso negado.");
            return;
        }
        
        BancoDados bd = new BancoDados();
        LivroDAO lDao = new LivroDAO(bd);
        AutorDAO aDao = new AutorDAO(bd);
        EditoraDAO eDao = new EditoraDAO(bd);
        
        Livro livro = new Livro();
        //Busca livro, caso não encontre, pára
        try {
            livro.setIsbn(isbn);
            livro = lDao.buscar(livro);
        } catch (BancoException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
            return;
        }
        
        Autor autor = new Autor();
        //Busca por autor
        try {
            autor.setID(idAutor);
            autor = aDao.buscarId(autor);
        } catch (BancoException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return;
        }
        
        Editora editora = new Editora();
        //Busca por editora
        try {
            editora.setID(idEditora);
            editora = eDao.buscarId(editora);
        } catch (BancoException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return;
        }
        
        //Define e altera livro
        try {
            livro.setIsbn(isbn);
            livro.setTitulo(titulo);
            livro.setAnoPublicacao(Integer.parseInt(anoPublicacao));
            livro.setCategoria(Livro.CategoriaLivro.valueOf(categoria));
            livro.setResumo(resumo);
            livro.setSumario(sumario);
            livro.setFormato(Livro.FormatoLivro.valueOf(formato));
            livro.setNumPaginas(Integer.parseInt(numPaginas));
            livro.setQtdEstoque(Integer.parseInt(qtdEstoque));
            livro.setPrecoVenda(Float.parseFloat(precoVenda));
            livro.setPrecoOferta(Float.parseFloat(precoOferta));
            livro.setPrecoCusto(Float.parseFloat(precoCusto));
            livro.setMargemLucro(Float.parseFloat(margemLucro));
            livro.setOferta(oferta);
            livro.setDigital(digital);
            livro.setAutor(autor); //Autor
            livro.setEditora(editora); //Editora
            
            lDao.alterar(livro);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null,"Não coloque texto em campo numérico. Utilize '.' como divisor decimal.");
        } catch (NegocioException | BancoException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
    }
    /**
     * Exclui o livro
     * @param isbn ISBN do livro
     */
    public void removerLivro(String isbn) {
        //Verifica se funcionário está logado, barrando caso não esteja
        if(!this.verificarConta()) {
            JOptionPane.showMessageDialog(null,"Acesso negado.");
            return;
        }
        
        BancoDados bd = new BancoDados();
        LivroDAO lDao = new LivroDAO(bd);
        
        Livro livro = new Livro();
        //Busca livro e exclui, caso não encontre, pára
        try {
            livro.setIsbn(isbn);
            livro = lDao.buscar(livro);
            
            lDao.excluir(livro);
        } catch (BancoException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
    }
    /**
     * Busca o livro
     * @param isbn ISBN do livro
     */
    public void buscarLivro(String isbn) {
        //Verifica se funcionário está logado, barrando caso não esteja
        if(!this.verificarConta()) {
            JOptionPane.showMessageDialog(null,"Acesso negado.");
            return;
        }
        
        BancoDados bd = new BancoDados();
        LivroDAO livroDAO = new LivroDAO(bd);
        
        Livro livro = new Livro();
        //Busca livro, caso não encontre, pára
        try {
            livro.setIsbn(isbn);
            livro = livroDAO.buscar(livro);
        } catch(BancoException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            this.L_titulo = "";
            this.L_anoPublicacao = "";
            this.L_resumo = "";
            this.L_sumario = "";
            this.L_formato = "";
            this.L_numPaginas = "";
            this.L_qtdEstoque = "";
            this.L_precoVenda = "";
            this.L_precoOferta = "";
            this.L_precoCusto = "";
            this.L_margemLucro = "";
            this.L_isbn = "";
            this.L_categoria = "";
            this.L_idAutor = 0;
            this.L_idEditora = 0;
            this.L_oferta = false;
            this.L_digital = false;
            return;
        }
        
        //Define resultados
        this.L_titulo = livro.getTitulo();
        this.L_anoPublicacao = String.valueOf(livro.getAnoPublicacao());
        this.L_resumo = livro.getResumo();
        this.L_sumario = livro.getSumario();
        this.L_formato = livro.getFormato().toString();
        this.L_numPaginas = String.valueOf(livro.getNumPaginas());
        this.L_qtdEstoque = String.valueOf(livro.getQtdEstoque());
        this.L_precoVenda = String.valueOf(livro.getPrecoVenda());
        this.L_precoOferta = String.valueOf(livro.getPrecoOferta());
        this.L_precoCusto = String.valueOf(livro.getPrecoCusto());
        this.L_margemLucro = String.valueOf(((int)livro.getMargemLucro()));
        this.L_isbn = livro.getIsbn();
        this.L_categoria = livro.getCategoria().toString();
        this.L_idAutor = livro.getID_AUTOR();
        this.L_idEditora = livro.getID_EDITORA();
        this.L_oferta = livro.isOferta();
        this.L_digital = livro.isDigital();
    }
    
    //CRUD Autor
    /**
     * Insere o autor
     * @param nome Nome do autor
     * @param codigo Código ALTERNATIVO do autor
     * @param localNasc Local de nascimento do autor
     * @param localMorte Local de morte do autor
     * @param dataNasc Data de nascimento do autor
     * @param dataMorte Data de morte do autor
     */
    public void inserirAutor(String nome, String codigo, String localNasc, String localMorte, String dataNasc, String dataMorte) {
        //Verifica se funcionário está logado, barrando caso não esteja
        if(!this.verificarConta()) {
            JOptionPane.showMessageDialog(null,"Acesso negado.");
            return;
        }
        
        BancoDados bd = new BancoDados();
        AutorDAO aDao = new AutorDAO(bd);
        
        //Busca autor, caso encontre, pára
        Autor autor = new Autor();
        try {
            autor.setCodigo(codigo);
            aDao.buscar(autor);
            JOptionPane.showMessageDialog(null,"Autor de código indicado já existente.");
            return;
        } catch (BancoException ex) { }
        
        SimpleDateFormat formaData = new SimpleDateFormat("dd/MM/yyyy");
        Date dataMort;
        Date dataNasce;
        try {
            autor.setNome(nome);
            autor.setCodigo(codigo);
            autor.setLocalNasci(localNasc);
            autor.setLocalMorte(localMorte);
            //Data
            dataMort = formaData.parse(dataMorte);
            autor.setDataMorte(dataMort);
            dataNasce = formaData.parse(dataNasc);
            autor.setDataNasci(dataNasce);
            
            aDao.inserir(autor);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Houve um problema de formatação de data ao inserir o autor.");
        } catch (BancoException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    /**
     * Altera o autor
     * @param nome Nome do autor
     * @param codigo Código ALTERNATIVO do autor
     * @param localNasc Local de nascimento do autor
     * @param localMorte Local de morte do autor
     * @param dataNasc Data de nascimento do autor
     * @param dataMorte Data de morte do autor
     */
    public void alterarAutor(String nome, String codigo, String localNasc, String localMorte, String dataNasc, String dataMorte) {
        //Verifica se funcionário está logado, barrando caso não esteja
        if(!this.verificarConta()) {
            JOptionPane.showMessageDialog(null,"Acesso negado.");
            return;
        }
        
        BancoDados bd = new BancoDados();
        AutorDAO aDao = new AutorDAO(bd);
        
        //Busca autor, caso não encontre, pára
        Autor autor = new Autor();
        try {
            autor.setCodigo(codigo);
            autor = aDao.buscar(autor);
        } catch (BancoException ex) {
            JOptionPane.showMessageDialog(null,"Não existe autor de código indicado.");
            return;
        }
        
        SimpleDateFormat formaData = new SimpleDateFormat("dd/MM/yyyy");
        Date dataMort;
        Date dataNasce;
        try {
            autor.setNome(nome);
            autor.setCodigo(codigo);
            autor.setLocalNasci(localNasc);
            autor.setLocalMorte(localMorte);
            //Data
            dataMort = formaData.parse(dataMorte);
            autor.setDataMorte(dataMort);
            dataNasce = formaData.parse(dataNasc);
            autor.setDataNasci(dataNasce);
            
            aDao.alterar(autor);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Houve um problema de formatação de data ao alterar o autor.");
        } catch (BancoException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    /**
     * Exclui o autor
     * @param codigo Código ALTERNATIVO do autor
     */
    public void removerAutor(String codigo) {
        //Verifica se funcionário está logado, barrando caso não esteja
        if(!this.verificarConta()) {
            JOptionPane.showMessageDialog(null,"Acesso negado.");
            return;
        }
        
        BancoDados bd = new BancoDados();
        AutorDAO aDao = new AutorDAO(bd);
        
        //Busca autor e exclui, caso não encontre, pára
        Autor autor = new Autor();
        try {
            autor.setCodigo(codigo);
            autor = aDao.buscar(autor);
            
            aDao.excluir(autor);
        } catch (BancoException ex) {
            JOptionPane.showMessageDialog(null,"Não existe autor de código indicado.");
        }
    }
    /**
     * Busca o autor
     * @param codigo Código ALTERNATIVO do autor
     */
    public void buscarAutor(String codigo) {
        //Verifica se funcionário está logado, barrando caso não esteja
        if(!this.verificarConta()) {
            JOptionPane.showMessageDialog(null,"Acesso negado.");
            return;
        }
        
        BancoDados bd = new BancoDados();
        AutorDAO aDao = new AutorDAO(bd);
        
        //Busca autor, caso não encontre, pára
        Autor autor = new Autor();
        try {
            autor.setCodigo(codigo);
            autor = aDao.buscar(autor);
        } catch (BancoException ex) {
            JOptionPane.showMessageDialog(null,"Não existe autor de código indicado.");
            this.A_nome = "";
            this.A_autor = "";
            this.A_localNasci = "";
            this.A_localMorte = "";
            this.A_dataNasci = "";
            this.A_dataMorte = "";
            return;
        }
        
        SimpleDateFormat formaData = new SimpleDateFormat("dd/MM/yyyy");
        
        this.A_nome = autor.getNome();
        this.A_autor = autor.getCodigo();
        this.A_localNasci = autor.getLocalNasci();
        this.A_localMorte = autor.getLocalMorte();
        this.A_dataNasci = formaData.format(autor.getDataNasci());
        this.A_dataMorte = formaData.format(autor.getDataMorte());
    }
    
    //CRUD Editora
    /**
     * Insere a editora
     * @param cnpj CNPJ da editora
     * @param nome Nome da editora
     * @param endereco Endereco da editora
     * @param telefone Telefone da editora
     */
    public void inserirEditora(String cnpj, String nome, String endereco, String telefone) {
        //Verifica se funcionário está logado, barrando caso não esteja
        if(!this.verificarConta()) {
            JOptionPane.showMessageDialog(null,"Acesso negado.");
            return;
        }
        
        EditoraDAO eDao = new EditoraDAO(new BancoDados());
        Editora editora = new Editora();
        //Busca editora, caso encontre, pára
        try {
            editora.setCnpj(cnpj);
            eDao.buscar(editora);
            JOptionPane.showMessageDialog(null, "Já existe editora de CNPJ indicado.");
            return;
        } catch (BancoException ex) {
        } catch (FormatacaoIncorretaException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return;
        }
        
        try {
            editora.setCnpj(cnpj);
            editora.setNome(nome);
            editora.setEndereco(endereco);
            editora.setTelefone(telefone);
            
            eDao.inserir(editora);
        } catch (FormatacaoIncorretaException | BancoException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    /**
     * Altera a editora
     * @param cnpj CNPJ da editora
     * @param nome Nome da editora
     * @param endereco Endereco da editora
     * @param telefone Telefone da editora
     */
    public void alterarEditora(String cnpj, String nome, String endereco, String telefone) {
        //Verifica se funcionário está logado, barrando caso não esteja
        if(!this.verificarConta()) {
            JOptionPane.showMessageDialog(null,"Acesso negado.");
            return;
        }
        
        EditoraDAO eDao = new EditoraDAO(new BancoDados());
        Editora editora = new Editora();
        //Busca editora, caso não encontre, pára
        try {
            editora.setCnpj(cnpj);
            editora = eDao.buscar(editora);
        } catch (BancoException ex) {
            JOptionPane.showMessageDialog(null, "Não existe editora de CNPJ indicado.");
            return;
        } catch (FormatacaoIncorretaException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return;
        }
        
        try {
            editora.setCnpj(cnpj);
            editora.setNome(nome);
            editora.setEndereco(endereco);
            editora.setTelefone(telefone);
            
            eDao.alterar(editora);
        } catch (FormatacaoIncorretaException | BancoException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    /**
     * Exclui a editora
     * @param cnpj CNPJ da editora
     */
    public void removerEditora(String cnpj) {
        //Verifica se funcionário está logado, barrando caso não esteja
        if(!this.verificarConta()) {
            JOptionPane.showMessageDialog(null,"Acesso negado.");
            return;
        }
        
        EditoraDAO eDao = new EditoraDAO(new BancoDados());
        Editora editora = new Editora();
        //Busca editora e exclui, caso não encontre, pára
        try {
            editora.setCnpj(cnpj);
            editora = eDao.buscar(editora);
            
            eDao.excluir(editora);
        } catch (BancoException ex) {
            JOptionPane.showMessageDialog(null, "Não existe editora de CNPJ indicado.");
        } catch (FormatacaoIncorretaException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    /**
     * Busca a editora
     * @param cnpj CNPJ da editora
     */
    public void buscarEditora(String cnpj) {
        //Verifica se funcionário está logado, barrando caso não esteja
        if(!this.verificarConta()) {
            JOptionPane.showMessageDialog(null,"Acesso negado.");
            return;
        }
        
        EditoraDAO eDao = new EditoraDAO(new BancoDados());
        Editora editora = new Editora();
        //Busca editora, caso não encontre, pára
        try {
            editora.setCnpj(cnpj);
            editora = eDao.buscar(editora);
        } catch (BancoException ex) {
            JOptionPane.showMessageDialog(null, "Não existe editora de CNPJ indicado.");
            this.E_cnpj = "";
            this.E_nome = "";
            this.E_endereco = "";
            this.E_teleone = "";
            return;
        } catch (FormatacaoIncorretaException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            this.E_cnpj = "";
            this.E_nome = "";
            this.E_endereco = "";
            this.E_teleone = "";
            return;
        }
        
        this.E_cnpj = editora.getCnpj();
        this.E_nome = editora.getNome();
        this.E_endereco = editora.getEndereco();
        this.E_teleone = editora.getTelefone();
    }
}
