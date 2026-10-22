package filmnow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

class FilmNowTest {
    private Filme filme;
    private FilmNow fn;
    private Filme[] filmes;
    private Filme[] hotList;

    @BeforeEach
    private void criaFilmeNow() {
        this.fn = new FilmNow();
        String nome = "Avatar";
        String ano = "2009";
        String ondeVer = "Disney Plus";
        this.filmes = new Filme[100];
        this.hotList = new Filme[10];
        this.filme = new Filme(nome, ano, ondeVer);
    }

    @Test
    void getFilmes() {
        fn.cadastraFilme(0, this.filme.getNome(), this.filme.getAnoDeLancamento(), this.filme.getOndeVer());
        Filme[] teste = new Filme[100];
        teste[0] = this.filme;
        assert Arrays.equals(fn.getFilmes(), teste);
    }

    @Test
    void getFilme(int i) {
        assert fn.getFilme(0).equals(this.filme);
    }

    @Test
    void cadastraFilme() {
        assert fn.cadastraFilme(0, this.filme.getNome(), this.filme.getAnoDeLancamento(), this.filme.getOndeVer()).equals("FILME ADICIONADO");
    }

    @Test
    void cadastraHot() {
    }

    @Test
    void getHotlist() {
    }

    @Test
    void removeHot() {
    }

    @Test
    void naHotlist() {
    }
}