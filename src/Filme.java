import java.util.Objects;

public class Filme {

    private String nome;

    private int anoDeLancamento;

    private String ondeVer;

    public Filme(String nome, int anoDeLancamento, String OndeVer) {

    }

    public String getNome() {
        return this.nome;
    }

    public int getAnoDeLancamento() {
        return this.anoDeLancamento;
    }

    public String getOndeVer() {
        return this.ondeVer;
    }

    @Override
    public String toString() {
        return this.nome + ", " + this.anoDeLancamento + "\n" + this.ondeVer;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Filme outro)) return false;
        return anoDeLancamento == outro.anoDeLancamento && Objects.equals(nome, outro.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, anoDeLancamento);
    }

}
