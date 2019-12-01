
import java.io.*;
import java.util.*;

public class leitorArquivos {

    public static void imprimir(String arq, int numerocidades) throws FileNotFoundException, IOException {
        //Declaração de variáveis referentes a abertura de documentos e leitura dos dados

        FileInputStream stream = new FileInputStream(arq);
        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader br = new BufferedReader(reader);
        String linha;
        int palavraLinha = 0;
        int linhaAtual = 0;
        int tipo = 0;
        Grafo grafo = new Grafo(numerocidades);
        System.out.println("Arquivo " + arq);
        do {
            linha = br.readLine();
            if (linha.equals("EDGE_WEIGHT_FORMAT: UPPER_DIAG_ROW")) { //Dados dispostos na diagonal superior
                tipo = 0;
            }

            if (linha.equals("EDGE_WEIGHT_FORMAT : LOWER_DIAG_ROW")) { //Dados dispostos na diagonal inferior
                tipo = 1;
            }
        } while (!linha.equals("EDGE_WEIGHT_SECTION"));

        //Leitura e insercao de dados do tipo 1
        if (tipo == 1) {
            do {
                linha = br.readLine();
                if (!linha.equals("DISPLAY_DATA_SECTION")) {
                    String[] pesos = linha.split(" ");
                    for (String a : pesos) {
                        if (!a.equals(" ") && !a.equals("") && linhaAtual < numerocidades) {
                            // System.out.println("["+linhaAtual+"]["+palavraLinha+"] = "+a+" ");    
                            grafo.insereArestaNaoOrientada(linhaAtual, palavraLinha, Integer.valueOf(a));
                            palavraLinha++;
                        }

                        if (palavraLinha > linhaAtual) {
                            linhaAtual++;
                            palavraLinha = 0;
                        }
                    }
                }
            } while (!linha.equals("DISPLAY_DATA_SECTION"));
        }

        //Leitura e insercao de dados do tipo 0
        if (tipo == 0) {
            do {
                linha = br.readLine();
                String[] pesos = linha.split(" ");
                for (String a : pesos) {
                    if (!a.equals(" ") && !a.equals("")) {
                        // System.out.println("["+linhaAtual+"]["+palavraLinha+"] = "+a+" ");    
                        grafo.insereArestaNaoOrientada(linhaAtual, palavraLinha, Integer.valueOf(a));
                        palavraLinha++;
                    }

                    if (palavraLinha == (numerocidades)) {
                        linhaAtual++;
                        palavraLinha = linhaAtual;
                    }
                }
            } while (linhaAtual != numerocidades);
        }

        caixeiroGuloso caixeiroGuloso = new caixeiroGuloso(numerocidades, 0, grafo);
        caixeiroGuloso.iniciaBusca();
        System.out.println("Menor distancia = " + caixeiroGuloso.getDistanciaGulosa());
        ArrayList<Integer> melhorCaminho = caixeiroGuloso.getCaminhoGuloso();
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        long tempoInicial = System.currentTimeMillis();
        imprimir("si535.tsp", 535);
        long tempoFinal = System.currentTimeMillis();
        System.out.println("Tempo de execução: " + (tempoFinal - tempoInicial) + " milisegundos\n");
        tempoInicial = System.currentTimeMillis();
        imprimir("pa561.tsp", 561);
        tempoFinal = System.currentTimeMillis();
        System.out.println("Tempo de execução: " + (tempoFinal - tempoInicial) + " milisegundos\n");
        tempoInicial = System.currentTimeMillis();
        imprimir("si1032.tsp", 1032);
        tempoFinal = System.currentTimeMillis();
        System.out.println("Tempo de execução: " + (tempoFinal - tempoInicial) + " milisegundos\n");
    }
}
