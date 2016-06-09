package newstime.entidade;

import java.util.Objects;
import newstime.excecao.NegocioException;

/**
 * Classe de entidade que representa o livro
 * @author Ian Melo
 */
public class Livro {
    /**
     * Categorias de livro existentes
     */
    public static enum CategoriaLivro {
        /** Administração */                ADMINISTRACAO,
        /** Agropecuário */                 AGROPECUARIA,
        /** Artes */                        ARTES,
        /** Audiolivro */                   AUDIOLIVRO,
        /** Autoajuda */                    AUTOAJUDA,
        /** Ciências Biológicas */          CIENCIAS_BIO,
        /** Ciências Exatas */              CIENCIAS_EXA,
        /** Ciências Humanas e Sociais */   CIENCIAS_HUM,
        /** Contabilidade */                CONTABILIDADE,
        /** Idiomas */                      IDIOMA,
        /** Dicionário */                   DICIONARIO,
        /** Didáticos */                    DIDATICO,
        /** Direito */                      DIREITO,
        /** Economia */                     ECONOMIA,
        /** Engenharia e Tecnologia */      ENG_TEC,
        /** Esportes e Lazer */             ESPORTE,
        /** Gastronomia */                  GASTRONOMIA,
        /** Geografia e História */         GEO_HIST,
        /** Informática */                  INFORMATICA,
        /** Linguística */                  LINGUISTICA,
        /** Literatura Estrangeira */       LITER_ESTR,
        /** Literatura Infantil */          LITER_INFA,
        /** Literatura Nacional */          LITER_NACIO,
        /** Medicina */                     MEDICINA,
        /** Religião */                     RELIGIAO,
        /** Turismo */                      TURISMO
    }
    /**
     * Formatos de livro existentes
     */
    public static enum FormatoLivro {
        /**
         * Brochura
         */
        BROCHURA,
        /**
         * Grampo
         */
        GRAMPO,
        /**
         * Capa dura
         */
        CAPA_DURA,
        /**
         * Digital
         */
        DIGITAL
    }
    
    /**
     * ISBN do livro
     */
    private String isbn = "";
    /**
     * Título do livro
     */
    private String titulo = "";
    /**
     * Autor do livro
     */
    private Autor autor;
    /**
     * Editora do livro
     */
    private Editora editora;
    /**
     * Ano de publicação do livro
     */
    private int anoPublicacao;
    /**
     * Categoria do livro
     */
    private CategoriaLivro categoria;
    /**
     * Resumo do livro
     */
    private String resumo = "";
    /**
     * Sumário do livro
     */
    private String sumario = "";
    /**
     * Formato do livro
     */
    private FormatoLivro formato;
    /**
     * Número de páginas do livro
     */
    private int numPaginas;
    /**
     * Quantidade em estoque do livro
     */
    private int qtdEstoque;
    /**
     * Preço de venda do livro
     */
    private float precoVenda;
    /**
     * Preço de oferta do livro
     */
    private float precoOferta;
    /**
     * Preço de custo do livro
     */
    private float precoCusto;
    /**
     * Margem de lucro do livro
     */
    private float margemLucro;
    /**
     * Indica se o livro está em oferta - true (ou não - false)
     */
    private boolean oferta;
    /**
     * Indica se o livro é digital - true (ou não - false)
     */
    private boolean digital;
    /**
     * Identificador do livro
     */
    private int ID;
    /**
     * Identifidador do autor do livro
     */
    private int ID_AUTOR;
    /**
     * Identificador da editora do livro
     */
    private int ID_EDITORA;
    
    //GETTERS SETTERS
    /**
     * Retorna o ISBN do livro
     * @return ISBN do livro
     */
    public String getIsbn() {
        return isbn;
    }
    /**
     * Define o ISBN do livro
     * @param isbn ISBN do livro
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    /**
     * Retorna o título do livro
     * @return Título do livro
     */
    public String getTitulo() {
        return titulo;
    }
    /**
     * Define o título do livro
     * @param titulo Título do livro
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    /**
     * Retorna o autor do livro
     * @return Autor do livro
     */
    public Autor getAutor() {
        return autor;
    }
    /**
     * Define o autor do livro
     * @param autor Autor do livro
     */
    public void setAutor(Autor autor) {
        this.autor = autor;
    }
    /**
     * Retorna a editora do livro
     * @return Editora do livro
     */
    public Editora getEditora() {
        return editora;
    }
    /**
     * Define a editora do livro
     * @param editora Editora do livro
     */
    public void setEditora(Editora editora) {
        this.editora = editora;
    }
    /**
     * Retorna o ano de publicação do livro
     * @return Ano de publicação do livro
     */
    public int getAnoPublicacao() {
        return anoPublicacao;
    }
    /**
     * Define o ano de publicação do livro
     * @param anoPublicacao Ano de publicação do livro
     */
    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }
    /**
     * Retorna a categoria do livro
     * @return Categoria do livro
     */
    public CategoriaLivro getCategoria() {
        return categoria;
    }
    /**
     * Define a categoria do livro
     * @param categoria Categoria do livro
     */
    public void setCategoria(CategoriaLivro categoria) {
        this.categoria = categoria;
    }
    /**
     * Retorna o resumo do livro
     * @return Resumo do livro
     */
    public String getResumo() {
        return resumo;
    }
    /**
     * Define o resumo do livro
     * @param resumo Resumo do livro
     */
    public void setResumo(String resumo) {
        this.resumo = resumo;
    }
    /**
     * Retorna o sumário do livro
     * @return Sumário do livro
     */
    public String getSumario() {
        return sumario;
    }
    /**
     * Define o sumário do livro
     * @param sumario Sumário do livro
     */
    public void setSumario(String sumario) {
        this.sumario = sumario;
    }
    /**
     * Retorna o formato do livro
     * @return Formato do livro
     */
    public FormatoLivro getFormato() {
        return formato;
    }
    /**
     * Define o formato do livro
     * @param formato Formato do livro
     */
    public void setFormato(FormatoLivro formato) {
        this.formato = formato;
    }
    /**
     * Retorna o número de páginas do livro
     * @return Número de páginas do livro
     */
    public int getNumPaginas() {
        return numPaginas;
    }
    /**
     * Define o número de páginas do livro
     * @param numPaginas Número de páginas do livro
     */
    public void setNumPaginas(int numPaginas) {
        this.numPaginas = numPaginas;
    }
    /**
     * Retorna a quantidade em estoque do livro
     * @return Quantidade em estoque do livro
     */
    public int getQtdEstoque() {
        return qtdEstoque;
    }
    /**
     * Define a quantidade em estoque do livro
     * <br/>Deve ser 0 ou acima
     * @param qtdEstoque Quantidade em estoque do livro
     * @throws newstime.excecao.NegocioException Caso não atenda a regra definida
     */
    public void setQtdEstoque(int qtdEstoque) throws NegocioException {
        if(this.validarQtdEstoque(qtdEstoque))
            this.qtdEstoque = qtdEstoque;
        else
            throw new NegocioException("A quantidade em estoque deve ser 0 ou acima.");
    }
    /**
     * Retorna o preço de venda do livro
     * @return Preço de venda do livro
     */
    public float getPrecoVenda() {
        return precoVenda;
    }
    /**
     * Define o preço de venda do livro
     * <br/>Deve ser um valor acima de R$ 0,00
     * @param precoVenda Preço de venda do livro
     * @throws newstime.excecao.NegocioException Caso não siga a regra definida
     */
    public void setPrecoVenda(float precoVenda) throws NegocioException {
        if(this.validarPreco(precoVenda))
            this.precoVenda = precoVenda;
        else
            throw new NegocioException("O preço de venda deve ser acima de R$ 0,00.");
        
    }
    /**
     * Retorna o preço de oferta do livro
     * @return Preço de oferta do livro
     */
    public float getPrecoOferta() {
        return precoOferta;
    }
    /**
     * Define o preço de venda do livro
     * <br/>Deve ser um valor acima de R$ 0,00
     * @param precoOferta Preço de oferta do livro
     * @throws newstime.excecao.NegocioException Caso não siga a regra definida
     */
    public void setPrecoOferta(float precoOferta) throws NegocioException {
        if(this.validarPreco(precoOferta))
            this.precoOferta = precoOferta;
        else
            throw new NegocioException("O preço de oferta deve ser acima de R$ 0,00.");
    }
    /**
     * Retorna o preço de custo do livro
     * @return Preço de custo do livro
     */
    public float getPrecoCusto() {
        return precoCusto;
    }
    /**
     * Define o preço de custo do livro
     * <br/>Deve ser um valor acima de R$ 0,00
     * @param precoCusto Preço de custo do livro
     * @throws newstime.excecao.NegocioException Caso não siga a regra definida
     */
    public void setPrecoCusto(float precoCusto) throws NegocioException {
        if(this.validarPreco(precoCusto))
            this.precoCusto = precoCusto;
        else
            throw new NegocioException("O preço de custo deve ser acima de R$ 0,00.");
    }
    /**
     * Retorna a margem de lucro do livro
     * @return Margem de lucro do livro
     */
    public float getMargemLucro() {
        return margemLucro;
    }
    /**
     * Define a margem de lucro do livro
     * <br/>Deve se encontrar entre 0% e 10%
     * @param margemLucro Margem de lucro do livro
     * @throws newstime.excecao.NegocioException Caso não atenda as regras definidas
     */
    public void setMargemLucro(float margemLucro) throws NegocioException {
        if(this.validarMargemLucro(margemLucro))
            this.margemLucro = margemLucro;
        else
            throw new NegocioException("Valor da margem de lucro deve estar entre 0% e 100%.");
    }
    /**
     * Retorna se o livro está em oferta (ou não)
     * @return true, se o livro está em oferta
     * <br/>false, caso contrário
     */
    public boolean isOferta() {
        return oferta;
    }
    /**
     * Define se o livro está em oferta - true (ou não - false)
     * @param oferta Se o livro está em oferta - true (ou não - false)
     */
    public void setOferta(boolean oferta) {
        this.oferta = oferta;
    }
    /**
     * Retorna se o livro é digital (ou não)
     * @return true, se o livro é digital
     * <br/>false, caso contrário
     */
    public boolean isDigital() {
        return digital;
    }
    /**
     * Define se o livro é digital - true (ou não - false)
     * @param digital Se o livro é digital - true (ou não - false)
     */
    public void setDigital(boolean digital) {
        this.digital = digital;
    }
    
    //IDENTIFICADORES
    /**
     * Retorna o identificador do livro
     * @return Identificador do livro
     */
    public int getID() {
        return ID;
    }
    /**
     * Define o identificador do livro
     * @param ID Identificador do livro
     */
    public void setID(int ID) {
        this.ID = ID;
    }
    /**
     * Retorna o identificador do livro do autor
     * @return Identificador do livro do autor
     */
    public int getID_AUTOR() {
        return ID_AUTOR;
    }
    /**
     * Define o identificador do livro do autor
     * @param ID_AUTOR Identificador do livro do autor
     */
    public void setID_AUTOR(int ID_AUTOR) {
        this.ID_AUTOR = ID_AUTOR;
    }
    /**
     * Retorna o identificador da editora do autor
     * @return Identificador da editora do autor
     */
    public int getID_EDITORA() {
        return ID_EDITORA;
    }
    /**
     * Define o identificador da editora do autor
     * @param ID_EDITORA Identificador da editora do autor
     */
    public void setID_EDITORA(int ID_EDITORA) {
        this.ID_EDITORA = ID_EDITORA;
    }
    
    
    //VALIDADORES
    /**
     * Valida preço em geral do livro
     * @param preco Preço de geral do produto
     * @return true, se o preço for positivo
     * <br/>false, caso contrário
     */
    private boolean validarPreco(float preco) {
        return (preco > 0f);
    }
    /**
     * Valida margem de lucro do produto
     * @param margemLucro Margem de lucro 
     * @return true, se encontrar entre 0% e 100%
     * <br/>false, caso contrário
     */
    private boolean validarMargemLucro(float margemLucro) {
        return (margemLucro >= 0f && margemLucro <= 100f);
    }
    /**
     * Valida quantidade de estoque do produto
     * @param qtdEstoque Quantidade de estoque
     * @return true, se for acima ou igual a 0
     * <br/>false, caso contrário
     */
    private boolean validarQtdEstoque(int qtdEstoque) {
        return (qtdEstoque >= 0);
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.isbn);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Livro other = (Livro) obj;
        if (!this.isbn.equals(other.isbn)) {
            return false;
        }
        return true;
    }
}
