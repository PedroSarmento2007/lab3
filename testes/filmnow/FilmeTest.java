package filmnow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FilmeTest {
    private Filme filme;

    @BeforeEach
    public void criaFilme() {
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
        assertEquals("Disney Plus", filme.getOndeVer());
    }

    @Test
    void testToString() {
        assertEquals("Avatar, 2009\nDisney Plus", filme.toString());
    }

    @Test
    void testEquals() {
        Filme filmeDif = new Filme("Toy Story", "1995", "Disney Plus");
        assertNotEquals(filme, filmeDif);
    }

    @Test
    void equalsMesmoNomeEAnoOutroLocal() {
        Filme mesmoFilme = new Filme("Avatar", "2009", "Netflix");
        assertEquals(filme, mesmoFilme);
    }

    @Test
    void equalsMesmoNomeOutroAno() {
        Filme outroAno = new Filme("Avatar", "2022", "Disney Plus");
        assertNotEquals(filme, outroAno);
    }

    @Test
    void equalsNull() {
        assertNotEquals(filme, null);
    }

    @Test
    void construtorNomeVazio() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> new Filme("", "2009", "Disney Plus"));
        assertEquals("FILME INVÁLIDO", e.getMessage());
    }

    @Test
    void construtorAnoVazio() {
        assertThrows(IllegalArgumentException.class, () -> new Filme("Avatar", "", "Disney Plus"));
    }

    @Test
    void construtorLocalVazio() {
        assertThrows(IllegalArgumentException.class, () -> new Filme("Avatar", "2009", ""));
    }

}
