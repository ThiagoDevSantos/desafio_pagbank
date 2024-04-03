package nexmuv.atividade_3.desafio;

import java.util.Scanner;

public class CalculadoraFatorial {

    public static int calcularFatorial(int n) {
        if (n == 0) {
            return 1; // O fatorial de 0 é 1
        }
        int resultado = 1;
        for (int i = 1; i <= n; i++) {
            resultado *= i;
        }
        return resultado;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite um número para calcular o fatorial:");
        int numero = scanner.nextInt();

        // Verifica se o número é negativo
        if (numero < 0) {
            System.out.println("Erro: O número deve ser não negativo.");
        } else {
            int resultado = calcularFatorial(numero);
            System.out.println("O fatorial de " + numero + " é: " + resultado);
        }

        scanner.close();
    }
}