package newstime.controle;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import newstime.DAO.AutorDAO;
import newstime.DAO.BancoDados;
import newstime.DAO.EditoraDAO;
import newstime.DAO.LivroDAO;
import newstime.entidade.Autor;
import newstime.entidade.BuscaLivro;
import newstime.entidade.Editora;
import newstime.entidade.Livro;
import newstime.excecao.BancoException;

/**
 * Está classe gerencia a busca do livro
 *
 * @author Fábio M.
 */
public class ControleBusca {

    ArrayList<Livro> resultadosBusca = new ArrayList<>();

    public ArrayList<Livro> getResultadosBusca() {
        return resultadosBusca;
    }

    /**
     * Vai enviar os parametros para buscar um livro atravez do objeto
     * buscalivro e retornar o livro se caso encontrado
     *
     * @param palChave A palavra chave que será enviada
     * @param criterio O criterio da busca A forma que será organizado
     * @throws newstime.excecao.BancoException
     */
    public void fazerBusca(String palChave, int criterio) throws BancoException {
        BuscaLivro busca = new BuscaLivro();
        Livro l = new Livro();
        ArrayList<Livro> resultados = new ArrayList<>();
       
        /*
         0 = Filtros
         1 = Editora
         2 = Título
         3 = Autor
         4 = ISBN
         5 = Categoria
         */
        if (criterio == 1) {
            resultados = busca.buscarLivros(palChave, (BuscaLivro.CriterioBusca.EDITORA));
        } else if (criterio == 2) {
            resultados = busca.buscarLivros(palChave, (BuscaLivro.CriterioBusca.TITULO));
        } else if (criterio == 3) {
            resultados = busca.buscarLivros(palChave, (BuscaLivro.CriterioBusca.AUTOR));
        } else if (criterio == 4) {
            resultados = buscarPorISBN(palChave);
        } else if (criterio == 5) {
            resultados = busca.buscarLivros(palChave, (BuscaLivro.CriterioBusca.CATEGORIA));
        } else {
            JOptionPane.showMessageDialog(null, "ERRO - Criterio de busca");
        }

        //BuscaLivro.CriterioBusca.valueOf(valor) ===> Converte
        /*
         for(Livro x:resultados) {
         System.out.println("_____________________________________teste_");
         System.out.println(x.getTitulo());
         System.out.println(x.getAutor());
         System.out.println(x.getEditora());
         }
         */
        resultadosBusca = resultados;

        //JOptionPane.showMessageDialog(null, resultados);
    }

    /**
     * Busca o livro pelo ISBN
     * @param isbn
     * @return
     * @throws BancoException 
     */
    public ArrayList<Livro> buscarPorISBN(String isbn) throws BancoException {
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
        
        ArrayList<Livro> livros = new ArrayList<>();
        livros.add(liOb);
        
        return livros;
    }

    /**
     * Organiza por ordem definida
     * @param palChave
     * @param criterio
     * @param organizacao
     * @deprecated Tem falha
     * @throws newstime.excecao.BancoException
     */
    public void fazerBusca(String palChave, int criterio, int organizacao) throws BancoException {
        BuscaLivro busca = new BuscaLivro();
        Livro l = new Livro();
        ArrayList<Livro> resultados = new ArrayList<>();

        /*
         0 = CATEGORIA 
         1 = EDITORA   
         2 = TITULO    
         3 = AUTOR     
         */
        /*
         FORMA COMO SERÁ ORGANIZADO  
         0 = ALFA_ASC
         1 = ALFA_DESC,
         2 = PRECO_ASC,
         3 = PRECO_DESC
         */
        /*ERRADO E MUITO FEIO*/
        if (criterio == 0) {
            if (organizacao == 0) {
                resultados = busca.buscarLivros(palChave, BuscaLivro.CriterioBusca.CATEGORIA, BuscaLivro.OrganizacaoBusca.ALFA_ASC);
            } else if (organizacao == 1) {
                resultados = busca.buscarLivros(palChave, BuscaLivro.CriterioBusca.CATEGORIA, BuscaLivro.OrganizacaoBusca.ALFA_DESC);
            } else if (organizacao == 2) {
                resultados = busca.buscarLivros(palChave, BuscaLivro.CriterioBusca.CATEGORIA, BuscaLivro.OrganizacaoBusca.PRECO_ASC);
            } else if (organizacao == 3) {
                resultados = busca.buscarLivros(palChave, BuscaLivro.CriterioBusca.CATEGORIA, BuscaLivro.OrganizacaoBusca.PRECO_DESC);
            } else {
                JOptionPane.showMessageDialog(null, "ERRO - Organização de busca");
            }

        } else if (criterio == 1) {
            if (organizacao == 0) {
                resultados = busca.buscarLivros(palChave, BuscaLivro.CriterioBusca.EDITORA, BuscaLivro.OrganizacaoBusca.ALFA_ASC);
            } else if (organizacao == 1) {
                resultados = busca.buscarLivros(palChave, BuscaLivro.CriterioBusca.EDITORA, BuscaLivro.OrganizacaoBusca.ALFA_DESC);
            } else if (organizacao == 2) {
                resultados = busca.buscarLivros(palChave, BuscaLivro.CriterioBusca.EDITORA, BuscaLivro.OrganizacaoBusca.PRECO_ASC);
            } else if (organizacao == 3) {
                resultados = busca.buscarLivros(palChave, BuscaLivro.CriterioBusca.EDITORA, BuscaLivro.OrganizacaoBusca.PRECO_DESC);
            } else {
                JOptionPane.showMessageDialog(null, "ERRO - Organização de busca");
            }
        } else if (criterio == 2) {
            if (organizacao == 0) {
                resultados = busca.buscarLivros(palChave, BuscaLivro.CriterioBusca.TITULO, BuscaLivro.OrganizacaoBusca.ALFA_ASC);
            } else if (organizacao == 1) {
                resultados = busca.buscarLivros(palChave, BuscaLivro.CriterioBusca.TITULO, BuscaLivro.OrganizacaoBusca.ALFA_DESC);
            } else if (organizacao == 2) {
                resultados = busca.buscarLivros(palChave, BuscaLivro.CriterioBusca.TITULO, BuscaLivro.OrganizacaoBusca.PRECO_ASC);
            } else if (organizacao == 3) {
                resultados = busca.buscarLivros(palChave, BuscaLivro.CriterioBusca.TITULO, BuscaLivro.OrganizacaoBusca.PRECO_DESC);
            } else {
                JOptionPane.showMessageDialog(null, "ERRO - Organização de busca");
            }
        } else if (criterio == 3) {
            if (organizacao == 0) {
                resultados = busca.buscarLivros(palChave, BuscaLivro.CriterioBusca.AUTOR, BuscaLivro.OrganizacaoBusca.ALFA_ASC);
            } else if (organizacao == 1) {
                resultados = busca.buscarLivros(palChave, BuscaLivro.CriterioBusca.AUTOR, BuscaLivro.OrganizacaoBusca.ALFA_DESC);
            } else if (organizacao == 2) {
                resultados = busca.buscarLivros(palChave, BuscaLivro.CriterioBusca.AUTOR, BuscaLivro.OrganizacaoBusca.PRECO_ASC);
            } else if (organizacao == 3) {
                resultados = busca.buscarLivros(palChave, BuscaLivro.CriterioBusca.AUTOR, BuscaLivro.OrganizacaoBusca.PRECO_DESC);
            } else {
                JOptionPane.showMessageDialog(null, "ERRO - Organização de busca");
            }
        } else {
            JOptionPane.showMessageDialog(null, "ERRO - Criterio de busca");
        }
        
        resultadosBusca = resultados;
        /*
         for(Livro x:resultadosBusca) {
         System.out.println("_____________________________________teste_");
         System.out.println(x.getTitulo());
         System.out.println(x.getAutor());
         System.out.println(x.getEditora());
         }
         */

        //JOptionPane.showMessageDialog(null, resultados);
    }
    
    /**
     * Vai retornar o livro q foi encontrado no buscalivro
     * @deprecated Não tem uso
     */
    public void mostrarLivros() {

    }
    
    /**
     * Busca por categoria
     * @deprecated Não tem uso
     * @param categoria 
     */
    public void buscarCategoria(String categoria) {
        BuscaLivro busca = new BuscaLivro();
    }
}
