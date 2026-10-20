import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {
        Filmnow filmnow = new Filmnow();
        Scanner sc = new Scanner(System.in);
        String opcao;
        while (true) {
            System.out.println("(A)Adicionar filme\n(M)Mostrar todos\n(D)Detalhar filme\n");
            System.out.println("(E)Exibir HotList\n(H)Atribuir HotList\n(R)Remover HotList\n(S)Sair\n");
            System.out.println("Opção>");
            opcao = sc.nextLine().toUpperCase();
            if (operacaoValida(opcao)) {
                switch (opcao) {
                    case "A" -> {
                        while (true) {
                            System.out.println("Posição>");
                            int pos = Integer.parseInt(sc.nextLine());
                            System.out.println("Nome>");
                            String nome = sc.nextLine();
                            System.out.println("Ano>");
                            int ano = Integer.parseInt(sc.nextLine());
                            System.out.println("Local>");
                            String local = sc.nextLine();
                            String resultado = filmnow.addFilme(nome, ano, local, pos);
                            System.out.println(resultado);
                            if (resultado.equals("FILME ADICIONADO")) {break;}
                            }
                        }
                    case "D" -> {
                        System.out.println("Posição>");
                        int pos = Integer.parseInt(sc.nextLine());
                        if (filmnow.getFilme(pos) == null) {
                            System.out.println("POSIÇÃO INVÁLIDA");
                        }
                        else {
                            filmnow.getFilme(pos).toString();
                        }
                    }
                    case "M" -> filmnow.mostrarFilmes();
                    case "E" -> filmnow.exibirHotList();
                    case "H" -> {
                        System.out.println("Filme>");
                        int pos = Integer.parseInt(sc.nextLine());
                        System.out.println("Posição>");
                        int posHot = Integer.parseInt(sc.nextLine());
                        System.out.println(filmnow.addHot(pos, posHot));
                    }
                    case "R" -> {
                        System.out.println("Posição>");
                        int posHot = Integer.parseInt(sc.nextLine());
                        System.out.println(filmnow.removeHot(posHot));
                    }
                }
            }
            else if (opcao.equals("S")) {
                break;
            }
            else {
                System.out.println("OPÇÃO INVÁLIDA!");
            }
        }
    }

    public static boolean operacaoValida(String opcao) {
        if (opcao.equals("A") || opcao.equals("M") || opcao.equals("D") || opcao.equals("S")) {
            return true;
        }
        return false;
    }

}