package filmnow;

/**
 * Sistema que reune os filmes. Podem existir 100 filmes na lista
 * e 10 na Hotlist.
 *
 * @author eliane
 * @author Pedro Sarmento
 *
 */
public class FilmNow {

    /**
     * Quantidade máxima de filmes no sistema.
     */
    private static final int TAMANHO_FILMES = 100;

    /**
     * Quantidade máxima de filmes na Hotlist.
     */
    private static final int TAMANHO_HOT = 10;

    /**
     * Lista de filmes do sistema. A posição N do usuário fica no índice N-1.
     */
    private Filme[] filmes;

    /**
     * Hotlist com os filmes marcados como "hot". A posição N do usuário fica no índice N-1.
     */
    private Filme[] hotlist;

    /**
     * Cria o FilmNow.
     */
    public FilmNow() {
        this.filmes = new Filme[TAMANHO_FILMES];
        this.hotlist = new Filme[TAMANHO_HOT];
    }

    /**
     * Acessa a lista de filmes mantida.
     *
     * @return Uma cópia do array de filmes. Posições sem filme são null.
     */
    public Filme[] getFilmes() {
        Filme[] out = new Filme[TAMANHO_FILMES];
        for (int i = 0; i < TAMANHO_FILMES; i++) {
            out[i] = this.filmes[i];
        }
        return out;
    }


    /**
     * Acessa os dados de um filme específico.
     *
     * @param posicao Posição do filme no sistema (de 1 a 100).
     * @return Dados do filme. Null se não há filme na posição.
     * @throws IndexOutOfBoundsException Se a posição estiver fora do intervalo de 1 a 100.
     */
    public Filme getFilme(int posicao) {
        if (!posicaoValida(posicao, TAMANHO_FILMES)) {
            throw new IndexOutOfBoundsException("POSIÇÃO INVÁLIDA");
        }
        return this.filmes[posicao-1];
    }

    /**
     * Adiciona um filme em uma posição. Se já existir filme na posição, sobrescreve o anterior.
     * A adição é negada se a posição for inválida, se o nome, o ano ou o local estiverem vazios,
     * ou se já existir no sistema um filme com o mesmo nome e ano.
     *
     * @param posicao         Posição do filme (de 1 a 100).
     * @param nome            Nome do filme.
     * @param anoDeLancamento Ano de lançamento do filme.
     * @param ondeVer         Local onde o filme pode ser assistido.
     * @return Mensagem de confirmação da adição.
     * @throws IndexOutOfBoundsException Se a posição estiver fora do intervalo de 1 a 100.
     * @throws IllegalArgumentException  Se o nome, o ano ou o local estiverem vazios,
     *                                   ou se o filme já estiver no sistema.
     */
    public String cadastraFilme(int posicao, String nome, String anoDeLancamento, String ondeVer) {
        if (!posicaoValida(posicao, TAMANHO_FILMES)) {
            throw new IndexOutOfBoundsException("POSIÇÃO INVÁLIDA");
        }
        if (nome.isBlank() || ondeVer.isBlank() || anoDeLancamento.isBlank()) {
            throw new IllegalArgumentException("FILME INVÁLIDO");

        }
        Filme novo = new Filme(nome, anoDeLancamento, ondeVer);
        if (filmeJaAdicionado(novo)) {
            throw new IllegalArgumentException("FILME JÁ ADICIONADO");
        }
        this.filmes[posicao-1] = novo;
        return "FILME ADICIONADO";
    }

    /**
     * Adiciona um filme do sistema à Hotlist. Se a posição da Hotlist já tiver um filme,
     * ele é substituído e deixa de ser "hot". Um filme só pode aparecer uma vez na Hotlist.
     *
     * @param posHot   Posição na Hotlist (de 1 a 10).
     * @param posFilme Posição do filme no sistema (de 1 a 100).
     * @return Mensagem de confirmação da adição.
     * @throws IndexOutOfBoundsException Se alguma das posições estiver fora do intervalo permitido.
     * @throws IllegalArgumentException  Se não houver filme na posição informada,
     *                                   ou se o filme já estiver na Hotlist.
     */
    public String cadastraHot(int posHot, int posFilme) {
        if (!posicaoValida(posHot, TAMANHO_HOT) || !posicaoValida(posFilme, TAMANHO_FILMES)) {
            throw new IndexOutOfBoundsException("POSIÇÃO INVÁLIDA");
        }
        Filme escolhido = this.filmes[posFilme-1];
        if (escolhido == null) {
            throw new IllegalArgumentException("SEM FILME NA POSIÇÃO INFORMADA");
        }
        for (Filme filmeHot : this.hotlist) {
            if (escolhido.equals(filmeHot)) {
                throw new IllegalArgumentException("FILME JÁ ADICIONADO NA HOTLIST");
            }
        }
        this.hotlist[posHot-1] = escolhido;
        return "ADICIONADO À HOTLIST NA POSIÇÃO " + posHot + "!";
    }

    /**
     * Acessa a Hotlist.
     *
     * @return Uma cópia do array da Hotlist. Posições sem filme são null.
     */
    public Filme[] getHotlist() {
        Filme[] out = new Filme[TAMANHO_HOT];
        for (int i = 0; i < TAMANHO_HOT; i++) {
            out[i] = this.hotlist[i];
        }
        return out;
    }

    /**
     * Remove o filme de uma posição da Hotlist. O filme continua no sistema.
     *
     * @param posHot Posição na Hotlist (de 1 a 10).
     * @throws IndexOutOfBoundsException Se a posição estiver fora do intervalo de 1 a 10.
     */
    public void removeHot(int posHot) {
        if (!posicaoValida(posHot, TAMANHO_HOT)) {
            throw new IndexOutOfBoundsException("POSIÇÃO INVÁLIDA");
        }
        this.hotlist[posHot-1] = null;
    }

    /**
     * Verifica se um filme está na Hotlist.
     *
     * @param filmeBuscado O filme a ser procurado. Não pode ser null.
     * @return true se o filme está na Hotlist, false caso contrário.
     */
    public boolean naHotlist(Filme filmeBuscado) {
        for (Filme hotFilme : this.hotlist) {
            if (filmeBuscado.equals(hotFilme)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica se uma posição informada pelo usuário está dentro dos limites de uma lista.
     *
     * @param posicao Posição informada, começando em 1.
     * @param tamanho Tamanho total da lista.
     * @return true se a posição está entre 1 e o tamanho, inclusive.
     */
    private boolean posicaoValida(int posicao, int tamanho) {
        return posicao >= 1 && posicao <= tamanho;
    }

    /**
     * Verifica se já existe no sistema um filme igual ao informado (mesmo nome e ano).
     *
     * @param filmeBuscado O filme a ser procurado.
     * @return true se o filme já está no sistema, false caso contrário.
     */
    private boolean filmeJaAdicionado(Filme filmeBuscado) {
        for (Filme filme : this.filmes) {
            if (filmeBuscado.equals(filme)) {
                return true;
            }
        }
        return false;
    }
}

