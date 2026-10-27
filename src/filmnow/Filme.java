package filmnow;

import java.util.Arrays;
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
     * Locais onde o filme pode ser assistido (Netflix, Cinema, etc), até 5.
     */
    private String[] ondeVer;

    /**
     * Índice de onde dever ser colocado o próximo local onde ver um filme.
     */
    private int iOndeVer;

    /**
     * Cria um filme a partir do nome, do ano de lançamento e dos locais onde assisti-lo.
     *
     * @param nome            Nome do filme.
     * @param anoDeLancamento Ano de lançamento do filme.
     * @param ondeVer         Locais onde o filme pode ser assistido.
     * @throws IllegalArgumentException Se o nome, o ano ou o local estiverem vazios.
     */
    public Filme(String nome, String anoDeLancamento, String ondeVer) {
        if (nome.isBlank()) {
            throw new IllegalArgumentException("FILME INVÁLIDO");
        }
        if (nome == null) {
            throw new NullPointerException("FILME NULO");
        }
        this.nome = nome;
        this.anoDeLancamento = anoDeLancamento;
        this.ondeVer = new String[5];
        this.ondeVer[0] = ondeVer;
        this.iOndeVer = 1;
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
    public String[] getOndeVer() {
        return this.ondeVer;
    }

    /**
     * Adiciona um local onde ver o filme
     *
     * @param local É o local que será colocado.
     */

    public void addOndeVer( String local) {
        this.ondeVer[this.iOndeVer] = local;
        this.iOndeVer += 1;
        if (this.iOndeVer >=5) {
            this.iOndeVer = 0;
        }
    }

    /**
     * Remove um local onde ver o filme da lista de locais.
     * Só pode remover se tiver mais de um local na lista.
     *
     * @param local É o local que será retirado.
     */
    public void removeOndeVer(String local) {
        int cont = 0;
        for (String ondeVer : this.ondeVer) {
            if (ondeVer != null) {
                cont += 1;
            }
        }
        if (cont > 1) {
            for (int i = 0; i < ondeVer.length; i++) {
                if (local.equals(this.ondeVer[i])) {
                    this.ondeVer[i] = null;
                }
            }
        }
    }

    /**
     * Retorna a representação do filme em texto, no formato
     * "nome, ano" seguido do local em uma nova linha.
     *
     * @return A representação em String do filme.
     */
    @Override
    public String toString() {
        String locais = "";
        for (int i = 0; i < this.ondeVer.length; i++) {
         if (this.ondeVer[i] != null) {
             if (i == 0) {
                 locais += this.ondeVer[i];
             } else {
                 locais += ", " + this.ondeVer[i];
             }
         }
        }
        return this.nome + ", " + this.anoDeLancamento + "\n" + locais;
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

