package newstime.excecao;

/**
 * Classe de exceção de banco
 * @author Ian Melo
 */
public class BancoException extends java.io.IOException {
    /**
     * Cria uma nova exceção de banco, definindo a mensagem
     * @param mensagem 
     */
    public BancoException(String mensagem) {
        super(mensagem);
    }
}
