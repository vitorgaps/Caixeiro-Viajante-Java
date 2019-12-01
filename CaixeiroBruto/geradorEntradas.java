
import java.io.*;
import java.util.*;

public class geradorEntradas {

    private final int[][] matrizAdjacencia;
    private int vertices;
    private String nomearquivo;

    public geradorEntradas(int vertices, String nomearquivo) {
        this.matrizAdjacencia = new int[vertices][vertices];
        this.vertices = vertices;
        this.nomearquivo = nomearquivo;
    }

    public void criaArquivo() throws FileNotFoundException, IOException {
        int peso = 0;
        Random gerador = new Random();
        FileWriter arq = new FileWriter(nomearquivo);
        PrintWriter gravaArq = new PrintWriter(arq);
        gravaArq.print(vertices + " " + "12");
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                if (i == j) {
                    this.matrizAdjacencia[j][i] = 0;
                    // System.out.println(i + " " + j + " " + 0);
                } else if (matrizAdjacencia[i][j] == 0) {
                    peso = gerador.nextInt(50) + 1;
                    this.matrizAdjacencia[j][i] = peso;
                    this.matrizAdjacencia[i][j] = peso;
                    // System.out.println(i + " " + j + " " + peso);
                    gravaArq.print("\n" + i + " " + j + " " + peso);
                }
            }
        }
        arq.close();
    }

    public static void main(String[] agrs) throws FileNotFoundException, IOException {
         java.util.Scanner ler = new Scanner(System.in);
         System.out.println("Digite o tamanho máximo da instancia n:");
         int vertices = ler.nextInt();
        for (int i = 2; i <= vertices; i++) {
            String nomearquivo = ("instancia" + i + ".txt");
            geradorEntradas gera = new geradorEntradas(i, nomearquivo);
            gera.criaArquivo();
        }

    }
}
