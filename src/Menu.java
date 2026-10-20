import java.util.Scanner;

public class Menu {

    public void main(String[] args) {
        Filmnow filmnow = new Filmnow();
        Scanner sc = new Scanner(System.in);
        String opcao;
        while (true) {
            System.out.println("(A)Adicionar filme\n(M)Mostrar todos\n(D)Detalhar filme\n");
            System.out.println("(E)Exibir HotList\n(H)Atribuir HotList\n(R)Remover HotList\n(S)Sair\n");
            System.out.println("Opção>");
            opcao = sc.nextLine().toUpperCase();
            if (operacaoValida(opcao)); {
                switch (opcao) {
                    case "A" -> {
                        while (true) {
                            System.out.println("Posição>");
                            int pos = sc.nextInt();
                            System.out.println("Nome>");
                            String nome = sc.nextLine();
                            System.out.println("Ano>");
                            int ano = sc.nextInt();
                            System.out.println("Local>");
                            String local = sc.nextLine();
                            if (filmnow.addFilme(nome, ano, local, pos).equals("FILME ADICIONADO")) {break;}
                            }
                        }
                    case "D" -> {
                        System.out.println("Posição>");
                        int pos = sc.nextInt();
                        if (filmnow.getFilme(pos) == null) {
                            System.out.println("POSIÇAO INVÁLIDA");
                        }
                        else {
                            filmnow.getFilme(pos).toString();
                        }
                    }
                    case "M" -> filmnow.mostrarFilmes();
                    case "E" -> filmnow.exibirHotList();
                    case "H" -> {
                        System.out.println("Filme>");
                        int pos = sc.nextInt();
                        System.out.println("Posição>");
                        int posHot = sc.nextInt();
                        System.out.println(filmnow.addHot(pos, posHot));
                    }
                    case "R" -> {
                        System.out.println("Posição>");
                        int posHot = sc.nextInt();
                        filmnow.removeHot(posHot);
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

    public boolean operacaoValida(String opcao) {
        if (opcao.equals("A") || opcao.equals("M") || opcao.equals("D") || opcao.equals("S")) {
            return true;
        }
        return false;
    }

}