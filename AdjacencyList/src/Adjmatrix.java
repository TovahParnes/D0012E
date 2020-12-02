import java.util.*;

public class AdjMatrix {
    private int vertex;
    private int [][] matrix;

    public AdjMatrix(int vertices) {
        this.vertex = vertices;
        this.matrix = new int[vertices+1][vertices+1];  // Instanciate the matrix

        for (int i = 0; i < (vertices+1); i++) {
            for (int j = 0; j < (vertices+1); j++) {
                this.matrix[i][j] = 0;  // Initialize the matrix to 0;
            }
        }
    }
    
    public int[][] getMatrix() {
        return this.matrix;
    }

    public void addEdge(int from, int to, int weight) { // Add a new edge between vertices
        if (weight < 0) {   // Check for invalid weight
            System.out.println("Invalid weight.");
            return;
        }
        if (from > this.vertex || to > this.vertex) {   // Check if vertex exists
            System.out.println("Invalid vertex/vertices.");
            return;
        }
        if (from == to) {   // Check for attempt to connect to self
            System.out.println("Can't create edge to self.");
            return;
        }
        if (this.matrix[from][to] > 0) {    // Check for duplicate edge
            System.out.println("Edge already exists with weight: " + this.matrix[from][to]);
            return;
        }
        this.matrix[from][to] = weight;
        this.matrix[to][from] = weight;
    }

    public void addEdge(int from, int to) { // Alternate method with default edge of 1;
        if (from > this.vertex || to > this.vertex) {   // Check if vertex exists
            System.out.println("Invalid vertex/vertices.");
            return;
        }
        if (from == to) {   // Check for attempt to connect to self
            System.out.println("Can't create edge to self.");
            return;
        }
        if (this.matrix[from][to] > 0) {    // Check for duplicate edge
            System.out.println("Edge already exists with weight: " + this.matrix[from][to]);
            return;
        }
        this.matrix[from][to] = 1;
        this.matrix[to][from] = 1;
    }

    public Boolean setWeight(int from, int to, int weight) {
        if (this.matrix[from][to] == 0) {
            System.out.println("Edge doesn't exist.");
            return false;
        }
        this.matrix[from][to] = weight;
        this.matrix[to][from] = weight;
        return true;
    }

    public void printGraph() {
        for (int i = 1; i < (vertex+1); i++) {
            for (int j = 1; j < (vertex+1); j++) {
                System.out.print(this.matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void fillGraph(int maxWeight) {              // Populates graph fully with random weights
        Random rand = new Random();                     // Class to generate random numbers
        for (int i=1; i < vertex; i++) {
            System.out.println(i);
            for (int j = i+1; j <= vertex; j++) {
                addEdge(i,j,rand.nextInt(maxWeight)+1);
            }           
        }        
    }

    public static void main(String[] args) {
        AdjMatrix adjmatrix = new AdjMatrix(5);
        adjmatrix.fillGraph(1000);
        adjmatrix.printGraph();

    }
}
