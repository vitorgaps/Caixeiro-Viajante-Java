
import java.util.*;

public class caixeiroGuloso {

    private int menorDistancia;
    private int verticeInicial;
    private Grafo grafo;
    private ArrayList<Integer> melhorCaminho;
    private int numeroVertices;

    public caixeiroGuloso(int vertices, int verticeInicial, Grafo grafo) {
        this.melhorCaminho = new ArrayList<>();
        this.grafo = grafo;
        this.verticeInicial = verticeInicial;
        this.menorDistancia = 0;
        this.numeroVertices = vertices;
    }

    //Inicia a busca gulosa do caminho do Caixeiro Viajante
    public void iniciaBusca() {
        int vertice = verticeInicial;
        int verticeMenorDistancia = 0;
        int distancia = Integer.MAX_VALUE;
        melhorCaminho.add(verticeInicial);

        do {
            distancia = Integer.MAX_VALUE;
            ArrayList<Integer> adjacentes = grafo.listaDeAdjacencia(vertice);
            for (int a : adjacentes) {

                if (!melhorCaminho.contains((Object) a) && a != verticeInicial && grafo.getPeso(vertice, a) < distancia) {
                    distancia = grafo.getPeso(vertice, a);
                    verticeMenorDistancia = a;
                }
            }
            vertice = verticeMenorDistancia;
            melhorCaminho.add(vertice);
            menorDistancia += distancia;
        } while (melhorCaminho.size() != numeroVertices);
        menorDistancia += grafo.getPeso(vertice, verticeInicial);
        melhorCaminho.add(verticeInicial);
    }

    //Retorna a menor distancia 
    public int getDistanciaGulosa() {
        return menorDistancia;
    }

    //Retorna o caminho guloso
    public ArrayList<Integer> getCaminhoGuloso() {
        return melhorCaminho;
    }
}
