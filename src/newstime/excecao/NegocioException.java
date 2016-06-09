package newstime.excecao;

/**
 * Classe de exceção de negócio
 * @author Ian Melo
 */
public class NegocioException extends java.io.IOException {
    /**
     * Cria uma nova exceção de negócio, definindo a mensagem
     * @param mensagem 
     */
    public NegocioException(String mensagem) {
        super(mensagem);
    }
    
}
