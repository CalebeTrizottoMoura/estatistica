import java.util.Locale;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.print("Sua lista tera quantos numeros? ");
        int tamanho = sc.nextInt();

        double[] vetor = new double[tamanho];

        System.out.print("\nPara separar a parte inteira da decimal, use o ponto (.)!\n");
        System.out.print("Exemplo: 5.985\n\n");

        for (int i = 0; i < tamanho; i ++) {
            System.out.printf("Digite o %dº valor: ", (i + 1));
            vetor[i] = sc.nextDouble();
        }

        for (int i = 1; i < tamanho; i ++) {
            int j = i;
    
            while (j > 0 && vetor[j] < vetor[j - 1]) {
                double temp = vetor[j];
                vetor[j] = vetor[j - 1];
                vetor[j - 1] = temp;
                
                if (j == 1) {
                    break;
                } else {
                    j --;
                }
            }
        }

        double soma = 0;
        System.out.print("\n======= RESULTADOS ESTATISTICOS =======\n");
        System.out.print("Lista ordenada: ");

        for (int i = 0; i < tamanho; i ++) {
            System.out.printf("%.2f", vetor[i]);

            if (i == (tamanho - 2)) {
                System.out.print(" e ");
            } else if (i == (tamanho - 1)) {
                System.out.print(".\n");
            } else {
                System.out.print(", ");
            }
            
            soma += vetor[i];
        }

        double media = soma / tamanho;
        
        System.out.printf("Menor valor: %.2f\n", vetor[0]);
        System.out.printf("Maior valor: %.2f\n", vetor[tamanho - 1]);
        System.out.printf("Media: %.2f\n", media);

        int indice_mediana = tamanho / 2;
        double mediana_par = (vetor[indice_mediana] + vetor[indice_mediana - 1]) / 2;
        double mediana_impar = vetor[indice_mediana];

        if ((tamanho % 2) == 0) {
            System.out.printf("Mediana: %.2f\n", mediana_par);
        } else {
            System.out.printf("Mediana: %.2f\n", mediana_impar);
        }

        int[] vetor_contagem = new int[tamanho];

        for (int i = 0; i < tamanho; i ++) {
            vetor_contagem[i] = 0;
        }

        for (int i = 1; i < tamanho; i ++) {
            int j = i;
            int contar = 1;

            while (vetor[j] == vetor[j - 1]) {
                contar ++;
                vetor_contagem[j] = contar;

                if (j == (tamanho - 1)) {
                    break;
                } else {
                    j ++;
                }
            }

            if (contar > 1) {
                i = j;
            }
        }

        int maior = 0;

        for (int i = 0; i < tamanho; i ++) {
            if (vetor_contagem[i] > maior) {
                maior = vetor_contagem[i];
            }
        }

        int contar_moda = 0;
        System.out.print("Moda: ");

        for (int i = 0; i < tamanho; i ++) {
            if (vetor_contagem[i] == maior && vetor_contagem[i] != 0) {
                if (contar_moda > 0) {
                    System.out.printf(", %.2f", vetor[i]);
                } else {
                    System.out.printf("{%.2f", vetor[i]);
                }

                contar_moda ++;
            }
        }

        if (contar_moda == 0) {
            System.out.print("conjunto sem valor modal.");
        } else {
            System.out.print("}");
        }

        double calculo = 0;

        for (int i = 0; i < tamanho; i ++) {
            calculo += Math.pow(vetor[i] - media, 2);
        }

        double variancia = calculo / tamanho;
        System.out.printf("\nVariancia populacional: %.2f", variancia);

        double desvio_padrao = Math.sqrt(variancia);
        System.out.printf("\nDesvio padrao populacional: %.2f", desvio_padrao);

        sc.close();
    }
}