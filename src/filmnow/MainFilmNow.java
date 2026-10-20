package filmnow;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Interface com menus texto para manipular o sistema FilmNow.
 *
 * @author eliane
 * @author Pedro Sarmento
 *
 */
public class MainFilmNow {

    /**
     * Inicia o sistema: carrega os filmes do arquivo inicial e exibe o menu
     * repetidamente até que o usuário escolha sair.
     *
     * @param args Argumentos da linha de comando (não utilizados).
     */
    public static void main(String[] args) {
        FilmNow fn = new FilmNow();

        System.out.println("Carregando filmes ...");
        try {
            /*
             * Essa é a maneira de lidar com possíveis erros por falta do arquivo.
             */
            carregaFilmes("filmes_inicial.csv", fn);
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erro lendo arquivo: " + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        String escolha = "";
        while (true) {
            escolha = menu(scanner);
            comando(escolha, fn, scanner);
        }

    }

    /**
     * Exibe o menu e captura a escolha do/a usuário/a.
     *
     * @param scanner Para captura da opção do usuário/a.
     * @return O comando escolhido.
     */
    private static String menu(Scanner scanner) {
        System.out.println(
                "\n---\nMENU\n" +
                        "(A)Adicionar filme\n" +
                        "(M)Mostrar todos\n" +
                        "(D)Detalhar filme\n" +
                        "(E)Exibir Hotlist\n" +
                        "(H)Atribuir Hot\n" +
                        "(R)Remover Hot\n" +
                        "(S)air\n" +
                        "\n" +
                        "Opção> ");
        return scanner.nextLine().toUpperCase();
    }

    /**
     * Interpreta a opção escolhida por quem está usando o sistema.
     *
     * @param opcao   Opção digitada.
     * @param fn  O sistema FilmNow que estamos manipulando.
     * @param scanner Objeto scanner para o caso do comando precisar de mais input.
     */
    private static void comando(String opcao, FilmNow fn, Scanner scanner) {
        switch (opcao) {
            case "A":
                adicionaFilme(fn, scanner);
                break;
            case "M":
                mostrarFilmes(fn);
                break;
            case "D":
                detalharFilme(fn, scanner);
                break;
            case "E":
                exibirHot(fn);
                break;
            case "H":
                cadastraHot(fn, scanner);
                break;
            case "R":
                removeHot(fn, scanner);
                break;
            case "S":
                sai();
                break;
            default:
                System.out.println("OPÇÃO INVÁLIDA!");
        }
    }

    /**
     * Imprime a lista de filmes, com a posição e o nome de cada um.
     *
     * @param fn O sistema FilmNow a ser manipulado.
     */
    private static void mostrarFilmes(FilmNow fn) {

        Filme[] filmes = fn.getFilmes();
        for (int i = 0; i < filmes.length; i++) {
            if (filmes[i] != null) {
                System.out.println(formataFilme(i + 1, filmes[i]));
            }
        }
    }

    /**
     * Imprime os detalhes de um dos filmes. Filmes que estão na Hotlist possuem 🔥
     * antes do título.
     *
     * @param fn O sistema FilmNow a ser manipulado.
     * @param scanner Scanner para capturar a posição do filme.
     */
    private static void detalharFilme(FilmNow fn, Scanner scanner) {
        System.out.print("\nQual filme> ");
        int posicao = Integer.parseInt(scanner.nextLine());
        Filme filme = fn.getFilme(posicao);
        if (filme == null) {
            System.out.println("POSIÇÃO INVÁLIDA!");
        } else if (fn.naHotlist(filme)) {
            System.out.println("Dados do filme:\n" + "\uD83D\uDD25 " + filme);
        } else {
            System.out.println("Dados do filme:\n" + filme);
        }
    }

    /**
     * Formata um filme para impressão.
     *
     * @param posicao A posição do filme (que é exibida).
     * @param filme O filme a ser impresso.
     * @return A String formatada.
     */
    private static String formataFilme(int posicao, Filme filme) {
        return posicao + " - " + filme.getNome();
    }

    /**
     * Formata um filme da Hotlist para impressão, com nome e ano.
     *
     * @param posicao A posição do filme na Hotlist (que é exibida).
     * @param filme O filme a ser impresso.
     * @return A String formatada.
     */
    private static String formataFilmeHot(int posicao, Filme filme) {
        return posicao + " - " + filme.getNome() + ", " + filme.getAnoDeLancamento();
    }

    /**
     * Cadastra um filme no sistema.
     *
     * @param fn O sistema FilmNow a ser manipulado.
     * @param scanner Scanner para pedir as informações do filme.
     */
    private static void adicionaFilme(FilmNow fn, Scanner scanner) {
        System.out.print("\nPosição no sistema> ");
        int posicao = Integer.parseInt(scanner.nextLine());
        System.out.print("\nNome> ");
        String nome = scanner.nextLine();
        System.out.print("\nAno> ");
        String ano = scanner.nextLine();
        System.out.print("\nLocal> ");
        String local = scanner.nextLine();
        System.out.println(fn.cadastraFilme(posicao, nome, ano, local));
    }

    /**
     * Adiciona um filme do sistema à Hotlist.
     *
     * @param fn O sistema FilmNow a ser manipulado.
     * @param scanner Scanner para pedir a posição do filme e a posição na Hotlist.
     */
    private static void cadastraHot(FilmNow fn, Scanner scanner){
        System.out.print("\nPosição do Filme> ");
        int posicao = Integer.parseInt(scanner.nextLine());
        System.out.print("\nPosição na Hotlist> ");
        int posicaoHot = Integer.parseInt(scanner.nextLine());
        System.out.println(fn.cadastraHot(posicaoHot, posicao));
    }

    /**
     * Imprime a Hotlist, com a posição, o nome e o ano de cada filme.
     *
     * @param fn O sistema FilmNow a ser manipulado.
     */
    private static void exibirHot(FilmNow fn) {
        Filme[] filmesHot = fn.getHotlist();
        for (int i = 0; i < filmesHot.length; i++) {
            if (filmesHot[i] != null) {
                System.out.println(formataFilmeHot(i + 1, filmesHot[i]));
            }
        }
    }

    /**
     * Remove um filme da Hotlist.
     *
     * @param fn O sistema FilmNow a ser manipulado.
     * @param scanner Scanner para pedir a posição na Hotlist.
     */
    private static void removeHot(FilmNow fn, Scanner scanner) {
        System.out.print("\nPosição na Hotlist> ");
        int posicaoHot = Integer.parseInt(scanner.nextLine());
        fn.removeHot(posicaoHot);
    }


    /**
     * Sai da aplicação.
     */
    private static void sai() {
        System.out.println("\nVlw flw o/");
        System.exit(0);
    }

    /**
     * Lê carga de filmes de um arquivo csv.
     *
     * @param arquivoFilmes O caminho para o arquivo.
     * @param fn O sistema FilmNow a ser populado com os dados.
     * @throws FileNotFoundException Caso o arquivo não exista.
     * @throws IOException Caso o arquivo não possa ser lido.
     */
    private static void carregaFilmes(String arquivoFilmes, FilmNow fn) throws FileNotFoundException, IOException {
        LeitorFilmNow leitor = new LeitorFilmNow();

        int carregados =  leitor.carregaContatos(arquivoFilmes, fn);
        System.out.println("Carregamos " + carregados + " registros.");
    }
}
