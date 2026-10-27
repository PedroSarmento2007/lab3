package filmnow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FilmNowTest {
    private Filme filme1;
    private Filme filme2;
    private FilmNow fn;

    @BeforeEach
    public void setUp() {
        this.fn = new FilmNow();
        this.filme1 = new Filme("Avatar", "2009", "Disney Plus");
        this.filme2 = new Filme("Toy Story", "1995", "Disney Plus");
        fn.cadastraFilme(1, "Avatar", "2009", "Disney Plus");
        fn.cadastraHot(1, 1);
    }

    @Test
    void getFilmes() {
        Filme[] arrayTeste = new Filme[100];
        arrayTeste[0] = this.filme1;
        assertArrayEquals(arrayTeste, fn.getFilmes());
    }

    @Test
    void getFilme() {
        assertEquals(this.filme1, fn.getFilme(1));
    }

    @Test
    void getFilmePosicaoVazia() {
        assertNull(fn.getFilme(50));
    }

    @Test
    void getFilmePosicaoInvalida() {
        assertThrows(IndexOutOfBoundsException.class, () -> fn.getFilme(0));
    }

    @Test
    void cadastraFilme() {
        assertEquals("FILME ADICIONADO", fn.cadastraFilme(2, this.filme2.getNome(),
                this.filme2.getAnoDeLancamento(), this.filme2.getOndeVer()[0]));
    }


    @Test
    void cadastraFilmeNomeVazio() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> fn.cadastraFilme(2, "", "2021", "Cinema"));
        assertEquals("FILME INVÁLIDO", e.getMessage());
    }


    @Test
    void cadastraFilmeDuplicado() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> fn.cadastraFilme(2, "Avatar", "2009", "Netflix"));
        assertEquals("FILME JÁ ADICIONADO", e.getMessage());
    }


    @Test
    void cadastraHot() {
        fn.cadastraFilme(2, this.filme2.getNome(), this.filme2.getAnoDeLancamento(), this.filme2.getOndeVer()[0]);
        assertEquals("ADICIONADO À HOTLIST NA POSIÇÃO 5!", fn.cadastraHot(5, 2));
    }

    @Test
    void cadastraHotPosicaoInvalida() {
        IndexOutOfBoundsException e = assertThrows(IndexOutOfBoundsException.class,
                () -> fn.cadastraHot(0, 1));
        assertEquals("POSIÇÃO INVÁLIDA", e.getMessage());
    }

    @Test
    void cadastraHotSemFilme() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> fn.cadastraHot(2, 50));
        assertEquals("SEM FILME NA POSIÇÃO INFORMADA", e.getMessage());
    }

    @Test
    void cadastraHotFilmeRepetido() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> fn.cadastraHot(2, 1));
        assertEquals("FILME JÁ ADICIONADO NA HOTLIST", e.getMessage());
    }


    @Test
    void getHotlist() {
        Filme[] arrayTeste = new Filme[10];
        arrayTeste[0] = this.filme1;
        assertArrayEquals(arrayTeste, fn.getHotlist());
    }

    @Test
    void removeHot() {
        fn.removeHot(1);
        assertArrayEquals(new Filme[10], fn.getHotlist());
        assertFalse(fn.naHotlist(this.filme1));
    }


    @Test
    void naHotlist() {
        assertTrue(fn.naHotlist(this.filme1));
    }
}
