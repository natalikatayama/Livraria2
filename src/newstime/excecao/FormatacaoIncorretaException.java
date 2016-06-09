package newstime.excecao;

/**
 * Classe de exceção de formatação incorreta de dado
 * @author Ian Melo
 */
public class FormatacaoIncorretaException extends java.io.IOException {
    /**
     * Cria uma nova exceção de formatação incorreta, definindo a mensagem
     * @param mensagem 
     */
    public FormatacaoIncorretaException(String mensagem) {
        super(mensagem);
    }
}
