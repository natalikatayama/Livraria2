//TODO: Implementar; Controle de conta; Documentar
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
    public String L_nomeAutor;
    public String L_nomeEditora;
    public String L_categoria;
    public boolean L_digital;
    public boolean L_oferta;
    
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
     * @param autor - Objeto autor
     * @param titulo - o L_titulo do livro
     * @param editora - a editora com qual o livro pertence
     * @param anoPublicacao
     * @param categoria
     * @param resumo
     * @param sumario
     * @param formato
     * @param numPaginas
     * @param qtdEstoque
     * @param precoVenda
     * @param precoOferta
     * @param precoCusto
     * @param margemLucro
     * @param oferta
     * @param digital
     * @return
     */
    public boolean inserirLivro(String isbn, String titulo, String autor, String editora, String anoPublicacao, String categoria, String resumo, String sumario, String formato, String numPaginas, String qtdEstoque, String precoVenda, String precoOferta, String precoCusto, String margemLucro, boolean oferta, boolean digital) {
        //Verifica se funcionário está logando, barrando caso não esteja
        if(!this.verificarConta()) {
            JOptionPane.showMessageDialog(null,"Acesso negado.");
            return false;
        }
        
        
        BancoDados bd = new BancoDados();
        AutorDAO autorDAO = new AutorDAO(bd);
        EditoraDAO editoraDAO = new EditoraDAO(bd);
        LivroDAO livroDAO = new LivroDAO(bd);

        //Busca autor, caso não encontre, insere e busca
        Autor au = new Autor();
        au.setNome(autor);
        try {
            au = autorDAO.buscar(au);
        } catch (BancoException ex) {
            autorDAO.inserir(au);
            au = autorDAO.buscar(au);
        }

        //Busca editora, caso não encontre, insere e busca
        Editora ed = new Editora();
        ed.setNome(editora);
        try {
            ed = editoraDAO.buscar(ed);
        } catch (BancoException ex) {
            editoraDAO.inserir(ed);
            ed = editoraDAO.buscar(ed);
        }

        //Define e insere livro
        Livro livro = new Livro();
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
            livro.setAutor(au);// seta o objeto autor dentro de livro
            livro.setEditora(ed);//seta o objeto editora dentro de livro
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null,"Não coloque texto em campo numérico. Utilize '.' como divisor decimal.");
            return false;
        }
        livroDAO.inserir(livro);
        return true;
    }
    /**
     * Altera o livro
     * @param isbn
     * @param titulo
     * @param autor
     * @param editora
     * @param anoPublicacao
     * @param categoria
     * @param resumo
     * @param sumario
     * @param formato
     * @param numPaginas
     * @param qtdEstoque
     * @param precoVenda
     * @param precoOferta
     * @param precoCusto
     * @param margemLucro
     * @param oferta
     * @param digital
     * @return
     */
    public boolean alterarLivro(String isbn, String titulo, String autor, String editora, String anoPublicacao, String categoria, String resumo, String sumario, String formato, String numPaginas, String qtdEstoque, String precoVenda, String precoOferta, String precoCusto, String margemLucro, boolean oferta, boolean digital) {
        //Verifica se funcionário está logando, barrando caso não esteja
        if(!this.verificarConta()) {
            JOptionPane.showMessageDialog(null,"Acesso negado.");
            return false;
        }
        
        
        BancoDados bd1 = new BancoDados();
        LivroDAO livroDAO = new LivroDAO(bd1);
        AutorDAO autorDAO = new AutorDAO(bd1);
        EditoraDAO editoraDAO = new EditoraDAO(bd1);

        //Busca livro, se não existir, pára
        Livro livro = new Livro();
        livro.setIsbn(isbn);
        livro = livroDAO.buscar(livro);

        //Altera autor
        Autor au = new Autor();
        au.setID(livro.getID_AUTOR());
        au.setNome(autor);
        autorDAO.alterar(au);

        //Altera editora
        Editora ed = new Editora();
        ed.setID(livro.getID_EDITORA());
        ed.setNome(editora);
        editoraDAO.alterar(ed);
        
        //Altera livro
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
            livro.setID_AUTOR(au.getID());
            livro.setID_EDITORA(ed.getID());
            livro.setAutor(au);
            livro.setEditora(ed);
        } catch(NumberFormatException ex) {
            JOptionPane.showMessageDialog(null,"Não coloque texto em campo numérico. Utilize '.' como divisor decimal.");
            return false;
        }
        livroDAO.alterar(livro);
        return true;
    }
    /**
     * Exclui o livro
     * @param isbn
     */
    public void removerLivro(String isbn) {
        //Verifica se funcionário está logando, barrando caso não esteja
        if(!this.verificarConta()) {
            JOptionPane.showMessageDialog(null,"Acesso negado.");
            return;
        }
        
        
        BancoDados bd = new BancoDados();
        LivroDAO livroDAO = new LivroDAO(bd);

        //Busca livro, se não existir, pára
        Livro livro = new Livro();
        livro.setIsbn(isbn);
        livro = livroDAO.buscar(livro);

        //Exclui
        livroDAO.excluir(livro);
    }
    /**
     * Busca o livro
     * @param isbn
     */
    public void buscarLivro(String isbn) {
        //Verifica se funcionário está logando, barrando caso não esteja
        if(!this.verificarConta()) {
            JOptionPane.showMessageDialog(null,"Acesso negado.");
            return;
        }
        
        
        BancoDados bd = new BancoDados();
        LivroDAO livroDAO = new LivroDAO(bd);
        AutorDAO autorDAO = new AutorDAO(bd);
        EditoraDAO editoraDAO = new EditoraDAO(bd);

        //Busca livro (ISBN)
        Livro livro = new Livro();
        livro.setIsbn(isbn);
        livro = livroDAO.buscar(livro);

        //Puxa autor
        Autor autor = new Autor();
        autor.setID(livro.getID_AUTOR());
        autor = autorDAO.buscarId(autor);

        //Puxa editora
        Editora editora = new Editora();
        editora.setID(livro.getID_EDITORA());
        editora = editoraDAO.buscarId(editora);

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
        this.L_nomeAutor = autor.getNome();
        this.L_nomeEditora = editora.getNome();
        this.L_oferta = livro.isOferta();
        this.L_digital = livro.isDigital();
    }
    
    //CRUD Autor
    /**
     * Insere o autor
     * @param nome
     * @param codigo
     * @param localNasc
     * @param localMorte
     * @param dataNasc
     * @param dataMorte
     */
    public void inserirAutor(String nome, String codigo, String localNasc, String localMorte, String dataNasc, String dataMorte) {
        //Verifica se funcionário está logando, barrando caso não esteja
        if(!this.verificarConta()) {
            JOptionPane.showMessageDialog(null,"Acesso negado.");
            return;
        }
        
        
        SimpleDateFormat formaData = new SimpleDateFormat("dd/MM/yyyy");
        
        Autor autor = new Autor();
        autor.setNome(nome);
        autor.setCodigo(codigo);
        autor.setLocalNasci(localNasc);
        autor.setLocalMorte(localMorte);

        Date dataMort = formaData.parse(dataMorte);
        autor.setDataMorte(dataMort);
        Date dataNasce = formaData.parse(dataNasc);
        autor.setDataNasci(dataNasce);

        BancoDados bd = new BancoDados();
        AutorDAO autorDAO = new AutorDAO(bd);
        autorDAO.inserir(autor);
    }
    /**
     * Altera o autor
     * @param nome
     * @param codigo
     * @param localNasc
     * @param localMorte
     * @param dataNasc
     * @param dataMorte
     */
    public void alterarAutor(String nome, String codigo, String localNasc, String localMorte, String dataNasc, String dataMorte) {
        //Verifica se funcionário está logando, barrando caso não esteja
        if(!this.verificarConta()) {
            JOptionPane.showMessageDialog(null,"Acesso negado.");
            return;
        }
        
        //IMPLEMENTAR
    }
    /**
     * Exclui o autor
     * @param codigo 
     */
    public void removerAutor(String codigo) {
        //Verifica se funcionário está logando, barrando caso não esteja
        if(!this.verificarConta()) {
            JOptionPane.showMessageDialog(null,"Acesso negado.");
            return;
        }
        
        //IMPLEMENTAR
    }
    /**
     * Busca o autor
     * @param codigo 
     */
    public void buscarAutor(String codigo) {
        //Verifica se funcionário está logando, barrando caso não esteja
        if(!this.verificarConta()) {
            JOptionPane.showMessageDialog(null,"Acesso negado.");
            return;
        }
        
        //IMPLEMENTAR
    }
    
    //CRUD Editora
    /**
     * Insere a editora
     * @param cnpj
     * @param nomeEdi
     * @param endereco
     * @param telefone
     */
    public void inserirEditora(String cnpj, String nomeEdi, String endereco, String telefone) {
        //Verifica se funcionário está logando, barrando caso não esteja
        if(!this.verificarConta()) {
            JOptionPane.showMessageDialog(null,"Acesso negado.");
            return;
        }
        
        
        Editora editora = new Editora();
        editora.setCnpj(cnpj);
        editora.setNome(nomeEdi);
        editora.setEndereco(endereco);
        editora.setTelefone(telefone);

        BancoDados bd = new BancoDados();
        EditoraDAO editoraD = new EditoraDAO(bd);
        editoraD.inserir(editora);
    }
    /**
     * Altera a editora
     * @param cnpj
     * @param nomeEdi
     * @param endereco
     * @param telefone
     */
    public void alterarEditora(String cnpj, String nomeEdi, String endereco, String telefone) {
        //Verifica se funcionário está logando, barrando caso não esteja
        if(!this.verificarConta()) {
            JOptionPane.showMessageDialog(null,"Acesso negado.");
            return;
        }
        
        
        //IMPLEMENTAR
    }
    /**
     * Exclui a editora
     * @param cnpj 
     */
    public void removerEditora(String cnpj) {
        //Verifica se funcionário está logando, barrando caso não esteja
        if(!this.verificarConta()) {
            JOptionPane.showMessageDialog(null,"Acesso negado.");
            return;
        }
        
        
        //IMPLEMENTAR
    }
    /**
     * Busca a editora
     * @param cnpj 
     */
    public void buscarEditora(String cnpj) {
        //Verifica se funcionário está logando, barrando caso não esteja
        if(!this.verificarConta()) {
            JOptionPane.showMessageDialog(null,"Acesso negado.");
            return;
        }
        
        
        //IMPLEMENTAR
    }
}
