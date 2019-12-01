
import java.util.ArrayList;

public class ForcaBruta {

    private ArrayList<Integer> melhorCaminho;
    private int menorDistancia;
    private int verticeInicial;
    private int verticeFinal;
    private Grafo grafo;
    private ArrayList<Integer> Aux;
    private int numeroVertices;

    public ForcaBruta(int vertices, int verticeInicial, Grafo grafo) {
        this.Aux = new ArrayList<>();
        this.melhorCaminho = new ArrayList<>();
        this.grafo = grafo;
        this.verticeInicial = verticeInicial;
        this.verticeFinal = verticeFinal;
        this.menorDistancia = Integer.MAX_VALUE;
        this.numeroVertices = vertices;
    }

    public void bruteForce() {
        ArrayList<Integer> adjacencias = grafo.listaDeAdjacencia(verticeInicial);
        Aux.add(verticeInicial);

        for (int a : adjacencias) {
            // System.out.println("\nAnalisando o "+a);
            Aux.add(a);
            analisaCaminho(a);
            Aux.remove((Object) a);
        }
    }

    private void analisaCaminho(int vertice) {
        // System.out.print("Caminho até agora: ");
        // for(int a:Aux){
        // System.out.print(" "+a); 
        // }
        // System.out.println();

        if (Aux.size() == numeroVertices) {
            // System.out.print("Caminho:");
            // for(int a:Aux){
            // System.out.print(" "+a); 
            // }
            // System.out.println("");
            verifica();
        }
        ArrayList<Integer> adjacencias = grafo.listaDeAdjacencia(vertice);
        for (int a : adjacencias) {
            if (!Aux.contains((Object) a)) {
                Aux.add(a);
                analisaCaminho(a);
                Aux.remove((Object) a);
            }
        }
    }

    private void verifica() {
        // System.out.println("Numero vertices - "+numeroVertices);
        int distancia = 0;
        int vertice2 = 0;
        for (int a : Aux) {
            if (a != Aux.get(numeroVertices - 1)) {
                // System.out.println("A - "+a+" Ultimo - "+Aux.get(numeroVertices-1));
                // int vertice1 = ;
                vertice2 = Aux.get(Aux.indexOf((Object) a) + 1);
                distancia += grafo.getPeso(a, vertice2);
                // System.out.println("Distancia "+distancia+"\n");
            }

        }
        distancia += grafo.getPeso(vertice2, verticeInicial);
        // System.out.println("Distancia final: "+distancia);
        if (distancia < menorDistancia) {
            menorDistancia = distancia;
            Aux.add(verticeInicial);
            melhorCaminho.clear();
            for (int a : Aux) {
                melhorCaminho.add(a);
            }
            Aux.remove(Aux.lastIndexOf(verticeInicial));
        }
    }

    public int getMenorDistancia() {
        return menorDistancia;
    }

    public ArrayList<Integer> getCaminho() {
        return melhorCaminho;
    }
}
