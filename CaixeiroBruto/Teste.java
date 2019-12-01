
import java.io.*;
import java.util.*;

public class Teste {

    public static void imprimir(String arq, int inicial, int finals) throws FileNotFoundException, IOException {
        //Declaração de variáveis referentes a abertura de documentos e leitura dos dados

        FileInputStream stream = new FileInputStream(arq);
        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader br = new BufferedReader(reader);
        String linha = br.readLine();

        int vertices = Integer.parseInt(linha.substring(0, linha.indexOf(' ')));
        int arestas = Integer.parseInt(linha.substring(linha.lastIndexOf(' ') + 1, linha.length()));
        linha = br.readLine();
        Grafo grafo = new Grafo(vertices);

        //Leitura dos dados
        while (linha != null) {
            int vert1 = Integer.parseInt(linha.substring(0, linha.indexOf(' ')));
            int vert2 = Integer.parseInt(linha.substring(linha.indexOf(' ') + 1, linha.lastIndexOf(' ')));
            int peso = Integer.parseInt(linha.substring(linha.lastIndexOf(' ') + 1, linha.length()));
            linha = br.readLine();

            //grafo.insereAresta(vert1, vert2, peso);  
            grafo.insereArestaNaoOrientada(vert1, vert2, peso);

        }
        // grafo.imprime();

        ForcaBruta forcaBruta = new ForcaBruta(vertices, 0, grafo);
        forcaBruta.bruteForce();
        System.out.println("Menor distancia = " + forcaBruta.getMenorDistancia());
        ArrayList<Integer> melhorCaminho = forcaBruta.getCaminho();
        System.out.print("Melhor caminho =");
        for (int a : melhorCaminho) {
            System.out.print(" " + a);
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        java.util.Scanner ler = new Scanner(System.in);
        System.out.println("De acordo com os arquivos criados pelo geradorEntradas.java qual o tamanho máximo das instancias que foram geradas?");
        int vertices = ler.nextInt();
        
        for (int i = 2; i <= vertices; i++) {
            String nomearquivo = ("instancia" + i + ".txt");
            long tempoInicial = System.currentTimeMillis();
            imprimir(nomearquivo, 0, 0);
            long tempoFinal = System.currentTimeMillis();
            System.out.println("Tempo de execução: " + (tempoFinal - tempoInicial) + " milisegundos\n");
        }

    }
}
