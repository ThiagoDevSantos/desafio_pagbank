package nexmuv.atividade_1.desafio;

import java.util.Scanner;

public class VerificadorVotos {
    private int totalEleitores;
    private int votosValidos;
    private int votosBrancos;
    private int votosNulos;

    // Construtor para inicializar os atributos
    public VerificadorVotos(int totalEleitores, int votosValidos, int votosBrancos, int votosNulos) {
        this.totalEleitores = totalEleitores;
        this.votosValidos = votosValidos;
        this.votosBrancos = votosBrancos;
        this.votosNulos = votosNulos;
    }

    // Método para calcular a porcentagem de votos válidos
    public double calcularPorcentagemVotosValidos() {
        return (double) votosValidos / totalEleitores * 100;
    }

    // Método para calcular a porcentagem de votos brancos
    public double calcularPorcentagemVotosBrancos() {
        return (double) votosBrancos / totalEleitores * 100;
    }

    // Método para calcular a porcentagem de votos nulos
    public double calcularPorcentagemVotosNulos() {
        return (double) votosNulos / totalEleitores * 100;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe o total de eleitores:");
        int totalEleitores = scanner.nextInt();

        System.out.println("Informe a quantidade de votos válidos:");
        int votosValidos = scanner.nextInt();

        System.out.println("Informe a quantidade de votos brancos:");
        int votosBrancos = scanner.nextInt();

        System.out.println("Informe a quantidade de votos nulos:");
        int votosNulos = scanner.nextInt();

        VerificadorVotos verificador = new VerificadorVotos(totalEleitores, votosValidos, votosBrancos, votosNulos);

        // Exibindo os resultados
        System.out.println("Porcentagem de votos válidos em relação ao total de eleitores: " + verificador.calcularPorcentagemVotosValidos() + "%");
        System.out.println("Porcentagem de votos brancos em relação ao total de eleitores: " + verificador.calcularPorcentagemVotosBrancos() + "%");
        System.out.println("Porcentagem de votos nulos em relação ao total de eleitores: " + verificador.calcularPorcentagemVotosNulos() + "%");

        scanner.close();
    }

}