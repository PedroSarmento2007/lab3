package filmnow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FilmeTest {
    private Filme filme;

    @BeforeEach
    public void setUp() {
        String nome = "Avatar";
        String ano = "2009";
        String ondeVer = "Disney Plus";
        this.filme = new Filme(nome, ano, ondeVer);
    }

    @Test
    void getNome() {
        assertEquals("Avatar", filme.getNome());
    }

    @Test
    void getAnoDeLancamento() {
        assertEquals("2009", filme.getAnoDeLancamento());
    }

    @Test
    void getOndeVer() {
        assertArrayEquals(new String[]{"Disney Plus"}, filme.getOndeVer());
    }

    @Test
    void addOndeVer() {
        filme.addOndeVer("Netflix");
        assertArrayEquals(new String[]{"Disney Plus", "Netflix"}, filme.getOndeVer());
        assertEquals("Avatar, 2009\nDisney Plus, Netflix", filme.toString());
    }

    @Test
    void removeOndeVer() {
        filme.addOndeVer("Netflix");
        filme.removeOndeVer("Disney Plus");
        assertArrayEquals(new String[]{"Netflix"}, filme.getOndeVer());
        assertEquals("Avatar, 2009\nNetflix", filme.toString());
    }

    @Test
    void removeOndeVerUnicoLocal() {
        filme.removeOndeVer("Disney Plus");
        assertArrayEquals(new String[]{"Disney Plus"}, filme.getOndeVer());
    }

    @Test
    void testToString() {
        assertEquals("Avatar, 2009\nDisney Plus", filme.toString());
    }

    @Test
    void testNotEquals() {
        Filme filmeDif = new Filme("Toy Story", "1995", "Disney Plus");
        assertNotEquals(filme, filmeDif);
    }

    @Test
    void testEquals() {
        Filme mesmoFilme = new Filme("Avatar", "2009", "Netflix");
        assertEquals(filme, mesmoFilme);
    }

    @Test
    void testEqualsMesmoNomeOutroAno() {
        Filme outroAno = new Filme("Avatar", "2022", "Disney Plus");
        assertNotEquals(filme, outroAno);
    }


    @Test
    void construtorNomeVazio() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> new Filme("", "2009", "Disney Plus"));
        assertEquals("FILME INVÁLIDO", e.getMessage());
    }

    @Test
    void construtorNomeNull() {
        NullPointerException e = assertThrows(NullPointerException.class,
                () -> new Filme(null, "2009", "Disney Plus"));
        assertEquals("FILME NULO", e.getMessage());
    }

}
