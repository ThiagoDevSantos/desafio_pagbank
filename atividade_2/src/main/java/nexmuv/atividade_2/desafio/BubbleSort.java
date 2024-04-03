package nexmuv.atividade_2.desafio;

import java.util.Scanner;

public class BubbleSort {
    public static void bubbleSort(int[] vetor) {
        int n = vetor.length;
        boolean troca;
        for (int i = 0; i < n-1; i++) {
            troca = false;
            for (int j = 0; j < n-i-1; j++) {
                if (vetor[j] > vetor[j+1]) {
                    // troca os elementos
                    int temp = vetor[j];
                    vetor[j] = vetor[j+1];
                    vetor[j+1] = temp;
                    troca = true;
                }
            }
            // Se não houve trocas nesta iteração, o vetor está ordenado
            if (!troca) {
                break;
            }
            // Exibe o vetor após esta iteração
            System.out.print("Iteração " + (i+1) + ": ");
            for (int k = 0; k < n; k++) {
                System.out.print(vetor[k] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe o tamanho do vetor:");
        int tamanho = scanner.nextInt();

        int[] vetor = new int[tamanho];

        System.out.println("Digite os elementos do vetor:");
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = scanner.nextInt();
        }

        // Exibe o vetor inicial
        System.out.print("Vetor inicial: ");
        for (int i = 0; i < tamanho; i++) {
            System.out.print(vetor[i] + " ");
        }
        System.out.println();

        // Ordena o vetor usando Bubble Sort e exibe os passos intermediários
        bubbleSort(vetor);

        // Exibe o vetor ordenado
        System.out.print("Vetor ordenado: ");
        for (int i = 0; i < tamanho; i++) {
            System.out.print(vetor[i] + " ");
        }
        System.out.println();

        scanner.close();
    }
}
