package filmnow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FilmeTest {
    private Filme filme;

    @BeforeEach
    private void criaFilme() {
        String nome = "Avatar";
        String ano = "2009";
        String ondeVer = "Disney Plus";
        this.filme = new Filme(nome, ano, ondeVer);
    }
    @Test
    void getNome() {
        assert filme.getNome().equals("Avatar");
    }

    @Test
    void getAnoDeLancamento() {
        assert filme.getAnoDeLancamento().equals("2009");

    }

    @Test
    void getOndeVer() {
        assert filme.getOndeVer().equals("Disney Plus");

    }

    @Test
    void testToString() {
        assert filme.toString().equals(filme.getNome() + ", " + filme.getAnoDeLancamento() + "\n" + filme.getOndeVer());

    }

    @Test
    void testEquals() {
        Filme filmeDif = new Filme("Toy Story", "1995", "Disney Plus" );
        assert !filme.equals(filmeDif);
    }

}
