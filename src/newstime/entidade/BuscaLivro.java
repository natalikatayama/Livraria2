package newstime.entidade;

import java.util.Collections;
import java.util.ArrayList;
import newstime.DAO.AutorDAO;
import newstime.DAO.BancoDados;
import newstime.DAO.EditoraDAO;
import newstime.DAO.LivroDAO;
import newstime.excecao.BancoException;

/**
 * Classe de entidade que representa a atividade de busca de livros
 * @author Ian Melo
 */
public class BuscaLivro {
    /**
     * Lista de livros da busca de livros
     */
    private ArrayList<Livro> livros = new ArrayList<>();
    
    /**
     * Tipos de critério de organização de busca de livros
     */
    public static enum OrganizacaoBusca {
        /**
         * Ordem alfabética crescente
         */
        ALFA_ASC,
        /**
         * Ordem alfabética decrescente
         */
        ALFA_DESC,
        /**
         * Ordem de preço crescente
         */
        PRECO_ASC,
        /**
         * Ordem de preço decrescente
         */
        PRECO_DESC
    }
    /**
     * Tipos de critério de busca de livros
     */
    public static enum CriterioBusca {
        /**
         * Busca por categoria
         */
        CATEGORIA,
        /**
         * Busca por editora
         */
        EDITORA,
        /**
         * Busca por título
         */
        TITULO,
        /**
         * Busca por autor
         */
        AUTOR
    }
    
    /**
     * Quantidade máxima de livros a ser exibido em 'mais vendidos'
     */
    private static final int QTD_EXIBICAO = 20;
    
    /**
     * Busca uma lista de livros que condizem com os parâmetros
     * @param palChave Palavra chave
     * @param criterio Critério de busca
     * @return Lista de livros que condizem com os parâmetros
     * @throws newstime.excecao.BancoException Caso dê algum erro ao buscar
     */
    public ArrayList<Livro> buscarLivros(String palChave, CriterioBusca criterio) throws BancoException {
        ArrayList<Livro> retorno = new ArrayList<>();
        BancoDados bd = new BancoDados();
        LivroDAO lDao = new LivroDAO(bd);
        AutorDAO aDao = new AutorDAO(bd);
        EditoraDAO eDao = new EditoraDAO(bd);
        Editora e = new Editora();
        Autor a = new Autor();
        //Busca os livros
        livros = (ArrayList<Livro>) lDao.listar();
        for(int i = 0;i < livros.size();i++) {
            //Busca respectiva editora
            e.setID(livros.get(i).getID_EDITORA());
            e = eDao.buscarId(e);
            livros.get(i).setEditora(e);
            //Busca respectivo autor
            a.setID(livros.get(i).getID_AUTOR());
            a = aDao.buscarId(a);
            livros.get(i).setAutor(a);
        }
        //Verifica critério e percorre com palavra chave, adicionando ao resultado
        switch(criterio) {
            case CATEGORIA:
                for(Livro x : livros)
                    if(x.getCategoria().toString().toLowerCase().contains(palChave.toLowerCase()))
                        retorno.add(x);
                break;
            case EDITORA:
                for(Livro x : livros)
                    if(x.getEditora().getNome().toLowerCase().contains(palChave.toLowerCase()))
                        retorno.add(x);
                break;
            case TITULO:
                for(Livro x : livros)
                    if(x.getTitulo().toLowerCase().contains(palChave.toLowerCase()))
                        retorno.add(x);
                break;
            case AUTOR:
                for(Livro x : livros)
                    if(x.getAutor().getNome().toLowerCase().contains(palChave.toLowerCase()))
                        retorno.add(x);
                break;
        }
        return retorno;
    }
    /**
     * Busca uma lista de livros que condizem com os parâmetros
     * @param palChave Palavra chave
     * @param criterio Critério de busca
     * @param organizacao Critério de organização de busca
     * @return Lista de livros que condizem com os parâmetros
     * @throws newstime.excecao.BancoException Caso dê algum erro ao buscar 
     */
    public ArrayList<Livro> buscarLivros(String palChave, CriterioBusca criterio, OrganizacaoBusca organizacao) throws BancoException {
        ArrayList<Livro> retorno = new ArrayList<>();
        BancoDados bd = new BancoDados();
        LivroDAO lDao = new LivroDAO(bd);
        AutorDAO aDao = new AutorDAO(bd);
        EditoraDAO eDao = new EditoraDAO(bd);
        Editora e = new Editora();
        Autor a = new Autor();
        //Busca os livros
        livros = (ArrayList<Livro>) lDao.listar();
        for(int i = 0;i < livros.size();i++) {
            //Busca respectiva editora
            e.setID(livros.get(i).getID_EDITORA());
            e = eDao.buscarId(e);
            livros.get(i).setEditora(e);
            //Busca respectivo autor
            a.setID(livros.get(i).getID_AUTOR());
            a = aDao.buscarId(a);
            livros.get(i).setAutor(a);
        }
        //Verifica critério e percorre com palavra chave, adicionando ao resultado
        switch(criterio) {
            case CATEGORIA:
                for(Livro x : livros)
                    if(x.getCategoria().toString().toLowerCase().contains(palChave.toLowerCase()))
                        retorno.add(x);
                break;
            case EDITORA:
                for(Livro x : livros)
                    if(x.getEditora().getNome().toLowerCase().contains(palChave.toLowerCase()))
                        retorno.add(x);
                break;
            case TITULO:
                for(Livro x : livros)
                    if(x.getTitulo().toLowerCase().contains(palChave.toLowerCase()))
                        retorno.add(x);
                break;
            case AUTOR:
                for(Livro x : livros)
                    if(x.getAutor().getNome().toLowerCase().contains(palChave.toLowerCase()))
                        retorno.add(x);
                break;
        }
        //Organiza por critério
        switch(organizacao) {
            case ALFA_ASC:
                Collections.sort(retorno,(g,h) -> g.getTitulo().compareToIgnoreCase(h.getTitulo()));
                break;
            case ALFA_DESC:
                Collections.sort(retorno,(g,h) -> h.getTitulo().compareToIgnoreCase(g.getTitulo()));
                break;
            case PRECO_ASC:
                Collections.sort(retorno,(g,h) -> ((int)(g.getPrecoVenda() - h.getPrecoVenda())));
                break;
            case PRECO_DESC:
                Collections.sort(retorno,(g,h) -> ((int)(h.getPrecoVenda() - g.getPrecoVenda())));
                break;
        }
        return retorno;
    }
    /**
     * Busca o livro pelo ISBN
     * @param isbn ISBN do livro
     * @return Livro que corresponda ao ISBN indicado
     * @throws BancoException Caso dê algum erro ao buscar
     */
    public ArrayList<Livro> buscarISBN(String isbn) throws BancoException {
        BancoDados bd = new BancoDados();
        LivroDAO livroDAO = new LivroDAO(bd);
        AutorDAO autorDAO = new AutorDAO(bd);
        EditoraDAO editoraDAO = new EditoraDAO(bd);
        
        Livro liOb = new Livro();
        liOb.setIsbn(isbn);
        liOb = livroDAO.buscar(liOb);
        
        Autor autor = new Autor();
        autor.setID(liOb.getID_AUTOR());
        autor = autorDAO.buscarId(autor);
            
        Editora editora = new Editora();
        editora.setID(liOb.getID_EDITORA());
        editora = editoraDAO.buscarId(editora);
        
        liOb.setAutor(autor);
        liOb.setEditora(editora);
        
        ArrayList<Livro> lvs = new ArrayList<>();
        lvs.add(liOb);
        
        return lvs;
    }
    /**
     * Busca uma lista de livros que estejam em oferta
     * @return Lista de livros que estejam em oferta
     * @throws newstime.excecao.BancoException Caso dê algum erro ao buscar
     */
    public ArrayList<Livro> buscarOfertas() throws BancoException {
        ArrayList<Livro> retorno = new ArrayList<>();
        BancoDados bd = new BancoDados();
        LivroDAO lDao = new LivroDAO(bd);
        AutorDAO aDao = new AutorDAO(bd);
        EditoraDAO eDao = new EditoraDAO(bd);
        Editora e = new Editora();
        Autor a = new Autor();
        //Busca os livros
        livros = (ArrayList<Livro>) lDao.listar();
        for(int i = 0;i < livros.size();i++) {
            //Busca respectiva editora
            e.setID(livros.get(i).getID_EDITORA());
            e = eDao.buscarId(e);
            livros.get(i).setEditora(e);
            //Busca respectivo autor
            a.setID(livros.get(i).getID_AUTOR());
            a = aDao.buscarId(a);
            livros.get(i).setAutor(a);
        }
        //Verifica se está em oferta, adicionando ao resultado
        for(Livro x : livros)
            if(x.isOferta())
                retorno.add(x);
        return retorno;
    }
    /**
     * Busca uma lista de lista de livros mais vendidos
     * @return Lista de lista de livros mais vendidos
     * @throws newstime.excecao.BancoException Caso dê algum erro ao buscar
     */
    public ArrayList<Livro> buscarMaisVendidos() throws BancoException {
        ArrayList<Livro> retorno = new ArrayList<>();
        BancoDados bd = new BancoDados();
        LivroDAO lDao = new LivroDAO(bd);
        AutorDAO aDao = new AutorDAO(bd);
        EditoraDAO eDao = new EditoraDAO(bd);
        Editora e = new Editora();
        Autor a = new Autor();
        //Busca os livros
        livros = (ArrayList<Livro>) lDao.listar();
        for(int i = 0;i < livros.size();i++) {
            //Busca respectiva editora
            e.setID(livros.get(i).getID_EDITORA());
            e = eDao.buscarId(e);
            livros.get(i).setEditora(e);
            //Busca respectivo autor
            a.setID(livros.get(i).getID_AUTOR());
            a = aDao.buscarId(a);
            livros.get(i).setAutor(a);
        }
        //Ordena o resultado, colocando os livros com quantidades de venda em primeiro
        Collections.sort(livros,(g,h) -> ((h.getQtdVendida()- g.getQtdVendida())));
        //Verifica os n livros mais vendidos (de acordo com a QTD_EXIBICAO), com quantidade de vendas acima de 0
        int cont = 0;
        for(Livro x : livros) {
            if(x.getQtdVendida() > 0 && cont < BuscaLivro.QTD_EXIBICAO) {
                retorno.add(x);
                cont++;
            } else //Para, se a quantidade do livro for 0 (os seguintes terão a mesma quantidade) ou chegar à QTD_EXIBICAO
                break;
        }
        return retorno;
    
    }
    /**
     * Busca uma lista de lista de livros que sejam físicos
     * @return Lista de lista de livros que sejam físicos
     * @throws newstime.excecao.BancoException Caso dê algum erro ao buscar
     */
    public ArrayList<Livro> buscarFisicos() throws BancoException {
        ArrayList<Livro> retorno = new ArrayList<>();
        BancoDados bd = new BancoDados();
        LivroDAO lDao = new LivroDAO(bd);
        AutorDAO aDao = new AutorDAO(bd);
        EditoraDAO eDao = new EditoraDAO(bd);
        Editora e = new Editora();
        Autor a = new Autor();
        //Busca os livros
        livros = (ArrayList<Livro>) lDao.listar();
        for(int i = 0;i < livros.size();i++) {
            //Busca respectiva editora
            e.setID(livros.get(i).getID_EDITORA());
            e = eDao.buscarId(e);
            livros.get(i).setEditora(e);
            //Busca respectivo autor
            a.setID(livros.get(i).getID_AUTOR());
            a = aDao.buscarId(a);
            livros.get(i).setAutor(a);
        }
        //Verifica se está em oferta, adicionando ao resultado
        for(Livro x : livros)
            if(!x.isDigital())
                retorno.add(x);
        return retorno;
    }
    /**
     * Busca uma lista de lista de livros que sejam digitais
     * @return Lista de lista de livros que sejam digitais
     * @throws newstime.excecao.BancoException Caso dê algum erro ao buscar
     */
    public ArrayList<Livro> buscarDigitais() throws BancoException {
        ArrayList<Livro> retorno = new ArrayList<>();
        BancoDados bd = new BancoDados();
        LivroDAO lDao = new LivroDAO(bd);
        AutorDAO aDao = new AutorDAO(bd);
        EditoraDAO eDao = new EditoraDAO(bd);
        Editora e = new Editora();
        Autor a = new Autor();
        //Busca os livros
        livros = (ArrayList<Livro>) lDao.listar();
        for(int i = 0;i < livros.size();i++) {
            //Busca respectiva editora
            e.setID(livros.get(i).getID_EDITORA());
            e = eDao.buscarId(e);
            livros.get(i).setEditora(e);
            //Busca respectivo autor
            a.setID(livros.get(i).getID_AUTOR());
            a = aDao.buscarId(a);
            livros.get(i).setAutor(a);
        }
        //Verifica se está em oferta, adicionando ao resultado
        for(Livro x : livros)
            if(x.isDigital())
                retorno.add(x);
        return retorno;
    }
}
