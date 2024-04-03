package nexmuv.atividade_4.desafio;

import java.util.Scanner;
import java.util.stream.IntStream;

public class SomaMultiplos {

    public static int somaMultiplos(int numero) {
        return IntStream.rangeClosed(1, numero)
                .filter(value -> value % 3 == 0 || value % 5 == 0)
                .sum();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite um número para calcular a soma dos múltiplos de 3 e 5:");
        int numero = scanner.nextInt();

        // Verifica se o número é negativo
        if (numero < 0) {
            System.out.println("Erro: O número deve ser não negativo.");
        } else {
            int resultado = somaMultiplos(numero);
            System.out.println("A soma dos múltiplos de 3 e 5 até " + numero + " é: " + resultado);
        }

        scanner.close();
    }
}