public class Filmnow {

    private Filme[] filmes;

    private Filme[] hotList;

    private static final int TAMANHO_FILMES = 99;

    private static final int TAMANHO_HOT = 9;

    public Filmnow() {
        this.hotList = new Filme[10];
        this.filmes = new Filme[100];
    }

    public String addFilme(String nome, int anoDeLancamento, String ondeVer, int posicao) {
        if (posicao-1<0 || posicao-1>TAMANHO_FILMES) {
            return "POSIÇÃO INVÁLIDA";
        }
        else if (nome.equals("") || ondeVer.equals("")) {
            return "FILME INVÁLIDO";

        }
        else if (filmeJaAdicionado(nome, anoDeLancamento)) {
            return "FILME JÁ ADICIONADO";
        }
        this.filmes[posicao-1] = new Filme(nome, anoDeLancamento, ondeVer);
        return "FILME ADICIONADO";
    }

    public String mostrarFilmes() {
        String out;
        for (int i; i < this.TAMANHO_FILMES; i ++) {
            if (this.filmes[i] != null) {
                out += i + " - " + this.filmes[i].getNome();
            }
        }
        return out;
    }

    private boolean filmeJaAdicionado(String nomeBuscado, int anoBuscado) {
        for (Filme filme:this.filmes) {
            if (filme.getAnoDeLancamento() == anoBuscado && filme.getNome().equals(nomeBuscado)) {
                return true;
            }
        }
        return false;
    }

    public Filme getFilme(int posicao) {
        return this.filmes[posicao-1];
    }


    public String addHot(int posFilme, int posHot) {
        if (posHot-1 < 0 || posHot-1 > TAMANHO_HOT || posFilme-1 < 0 || posFilme-1 > TAMANHO_FILMES) {
            return "POSIÇÃO INVÁLIDA";
        }
        for (Filme filme: this.hotList) {
            if (filme.equals(this.filmes[posFilme-1])) {return "FILME JÁ ADICIONADO NA HOTLIST";}
        }
        this.hotList[posHot-1] = this.filmes[posFilme-1];
        return "ADICIONADO À HOTLIST NA POSIÇÃO " + posHot + "!";

    }

    public void removeHot(int posHot) {
        this.hotList[posHot-1] = null;

    }

    public String exibirHotList() {
        String out;
        for (int i; i < this.hotList; i ++) {
            if (this.hotList[i] != null) {
                out += i + " - " + this.hotList[i].getNome();
            }
        }
        return out;
    }


}
