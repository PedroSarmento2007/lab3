package filmnow;

/**
 * Sistema que mantém os seus filmes prediletos. Podem existir 100 filmes.
 *
 * @author eliane
 *
 */
public class FilmNow {

    private static final int TAMANHO_FILMES = 100;
    private static final int TAMANHO_HOT = 10;

    private Filme[] filmes; //uma representacao simploria da lista de filmes
    private Filme[] hotList;

    /**
     * Cria o FilmNow.
     */
    public FilmNow() {
        this.filmes = new Filme[TAMANHO_FILMES];
        this.hotList = new Filme[TAMANHO_HOT];
    }

    /**
     * Acessa a lista de filmes mantida.
     *
     * @return O array de filmes.
     */
    public Filme[] getFilmes() {
        Filme[] out = new Filme[TAMANHO_FILMES];
        for (int i = 0; i < TAMANHO_FILMES; i++) {
            out [i] = getFilme(i);
        }
        return out;
    }


    /**
     * Acessa os dados de um filme específico.
     *
     * @param posicao Posição do filme no sistema.
     * @return Dados do filme. Null se não há filme na posição.
     */
    public Filme getFilme(int posicao) {
        if (!posicaoValida(posicao, TAMANHO_FILMES)) {
            return null;
        }
        return this.filmes[posicao - 1];
    }

    /**
     * Adiciona um filme em uma posição. Se já existir filme na posição, sobrescreve o anterior.
     *
     * @param posicao         Posição do filme.
     * @param nome            Nome do filme.
     * @param anoDeLancamento Ano de lançamento do filme.
     * @param ondeVer         Local onde o filme pode ser assitido.
     */
    public String cadastraFilme(int posicao, String nome, String anoDeLancamento, String ondeVer) {
        if (!posicaoValida(posicao, TAMANHO_FILMES)) {
            return "POSIÇÃO INVÁLIDA";
        }
        if (nome.isBlank() || ondeVer.isBlank()) {
            return "FILME INVÁLIDO";

        }
        Filme novo = new Filme(nome, anoDeLancamento, ondeVer);
        if (filmeJaAdicionado(novo)) {
            return "FILME JÁ ADICIONADO";
        }
        this.filmes[posicao - 1] = novo;
        return "FILME ADICIONADO";
    }

    public String cadastraHot(int posHot, int posFilme) {
        if (!posicaoValida(posHot, TAMANHO_HOT) || !posicaoValida(posFilme, TAMANHO_FILMES)) {
            return "POSIÇÃO INVÁLIDA";
        }
        if (this.filmes[posFilme-1] == null) {return "SEM FILME NA POSIÇÃO INFORMADA";}
        for (Filme filmeHot: this.hotList) {
            if (this.filmes[posFilme-1].equals(filmeHot)) {return "FILME JÁ ADICIONADO NA HOTLIST";}
        }
        this.hotList[posHot-1] = this.filmes[posFilme-1];
        return "ADICIONADO À HOTLIST NA POSIÇÃO " + posHot + "!";
    }

    public Filme[] getHotList() {
        Filme[] out = new Filme[TAMANHO_HOT];
        for (int i =0; i < TAMANHO_HOT; i ++) {
            if (this.hotList[i] != null) {
                out[i] = hotList[i];
            }
        }
        return out;
    }

    public String removeHot(int posHot) {
        if (!posicaoValida(posHot, TAMANHO_HOT)) {
            return "POSIÇÃO INVÁLIDA";
        }
        this.hotList[posHot-1] = null;
        return "REMOVIDO DA HOTLIST";
    }

    public boolean naHotlist(Filme filmeBuscado) {
        for (Filme hotFilme : this.hotList) {
            if (filmeBuscado.equals(hotFilme)) {
                return true;
            }
        }
        return false;
    }

    private boolean posicaoValida(int posicao, int tamanho) {
        return posicao >= 1 && posicao <= tamanho;
    }

    private boolean filmeJaAdicionado(Filme filmeBuscado) {
        for (Filme filme : this.filmes) {
            if (filmeBuscado.equals(filme)) {
                return true;
            }
        }
        return false;
    }
}

