package filmnow;

import java.util.Objects;

/**
 * Representação de um filme. Todo filme tem nome, ano de lançamento
 * e o local onde pode ser assistido.
 *
 * @author Pedro Sarmento
 */
public class Filme {

    /**
     * Nome do filme.
     */
    private String nome;

    /**
     * Ano de lançamento do filme.
     */
    private String anoDeLancamento;

    /**
     * Local onde o filme pode ser assistido (Netflix, Cinema, etc).
     */
    private String ondeVer;

    /**
     * Cria um filme a partir do nome, do ano de lançamento e do local onde assisti-lo.
     *
     * @param nome            Nome do filme.
     * @param anoDeLancamento Ano de lançamento do filme.
     * @param ondeVer         Local onde o filme pode ser assistido.
     */
    public Filme(String nome, String anoDeLancamento, String ondeVer) {
        this.nome = nome;
        this.anoDeLancamento = anoDeLancamento;
        this.ondeVer = ondeVer;
    }

    /**
     * Retorna o nome do filme.
     *
     * @return O nome do filme.
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Retorna o ano de lançamento do filme.
     *
     * @return O ano de lançamento do filme.
     */
    public String getAnoDeLancamento() {
        return this.anoDeLancamento;
    }

    /**
     * Retorna o local onde o filme pode ser assistido.
     *
     * @return O local onde o filme pode ser assistido.
     */
    public String getOndeVer() {
        return this.ondeVer;
    }

    /**
     * Retorna a representação do filme em texto, no formato
     * "nome, ano" seguido do local em uma nova linha.
     *
     * @return A representação em String do filme.
     */
    @Override
    public String toString() {
        return this.nome + ", " + this.anoDeLancamento + "\n" + this.ondeVer;
    }

    /**
     * Verifica se este filme é igual a outro objeto. Dois filmes são iguais
     * se têm o mesmo nome e o mesmo ano de lançamento, independente do local.
     *
     * @param o O objeto a ser comparado.
     * @return true se o objeto for um filme com mesmo nome e ano, false caso contrário.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Filme outro)) return false;
        return anoDeLancamento.equals(outro.anoDeLancamento) && Objects.equals(nome, outro.nome);
    }

    /**
     * Gera o código hash do filme a partir do nome e do ano de lançamento,
     * os mesmos atributos usados no equals.
     *
     * @return O código hash do filme.
     */
    @Override
    public int hashCode() {
        return Objects.hash(nome, anoDeLancamento);
    }

}

