public class Filmnow {

    private Filme[] filmes;

    private Filme[] hotList;

    private static final int TAMANHO_FILMES = 100;

    private static final int TAMANHO_HOT = 10;

    public Filmnow() {
        this.hotList = new Filme[TAMANHO_HOT];
        this.filmes = new Filme[TAMANHO_FILMES];
    }

    public String addFilme(String nome, int anoDeLancamento, String ondeVer, int posicao) {
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
        this.filmes[posicao-1] = novo;
        return "FILME ADICIONADO";
    }

    public String mostrarFilmes() {
        String out = "";
        for (int i = 0; i < TAMANHO_FILMES; i ++) {
            if (this.filmes[i] != null) {
                out += (i+1) + " - " + this.filmes[i].getNome() + "\n";
            }
        }
        return out;
    }

    private boolean filmeJaAdicionado(Filme filmeBuscado) {
        for (Filme filme:this.filmes) {
            if (filmeBuscado.equals(filme)) {
                return true;
            }
        }
        return false;
    }

    public Filme getFilme(int posicao) {
        if (!posicaoValida(posicao, TAMANHO_FILMES)) {
            return null;
        }
        return this.filmes[posicao-1];
    }


    public String addHot(int posFilme, int posHot) {
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

    public String removeHot(int posHot) {
        if (!posicaoValida(posHot, TAMANHO_HOT)) {
            return "POSIÇÃO INVÁLIDA";
        }
        this.hotList[posHot-1] = null;
        return "REMOVIDO DA HOTLIST";
    }

    public String exibirHotList() {
        String out = "";
        for (int i =0; i < TAMANHO_HOT; i ++) {
            if (this.hotList[i] != null) {
                out += (i+1) + " - " + this.hotList[i].getNome() + "\n";
            }
        }
        return out;
    }

    private boolean posicaoValida(int posicao, int tamanho) {
        return posicao >= 1 && posicao <= tamanho;
    }

}
