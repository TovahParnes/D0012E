public class Adjmatrix {
    private int vertex;
    private int [][] matrix;

    public Adjmatrix(int vertices) {
        this.vertex = vertices;
        this.matrix = new int[vertices+1][vertices+1];  // Instanciate the matrix

        for (int i = 0; i < (vertices+1); i++) {
            for (int j = 0; j < (vertices+1); j++) {
                this.matrix[i][j] = 0;  // Initialize the matrix to 0;
            }
        }
    }

    public String toString() {
        return "Vertices: " + this.vertex;
    }

    public void addEdge(int from, int to, int weight) {
        if (weight < 0) {
            System.out.println("Invalid weight.");
            return;
        }
        if (from > this.vertex || to > this.vertex) {
            System.out.println("Invalid vertex/vertices.");
            return;
        }
        if (from == to) {
            System.out.println("Can't create edge to self.");
            return;
        }
        if (this.matrix[from][to] > 0) {
            System.out.println("Edge already exists with weight: " + this.matrix[from][to]);
            return;
        }
        this.matrix[from][to] = weight;
        this.matrix[to][from] = weight;
    }

    public void addEdge(int from, int to) { // Alternate method with default edge of 1;
        if (from > this.vertex || to > this.vertex) {
            System.out.println("Invalid vertex/vertices.");
            return;
        }
        if (from == to) {
            System.out.println("Can't create edge to self.");
            return;
        }
        if (this.matrix[from][to] > 0) {
            System.out.println("Edge already exists with weight: " + this.matrix[from][to]);
            return;
        }
        this.matrix[from][to] = 1;
        this.matrix[to][from] = 1;
    }

    public boolean setWeight(int from, int to, int weight) {
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

    public static void main(String[] args) {
        Adjmatrix adjmatrix = new Adjmatrix(5);

        adjmatrix.addEdge(1, 2, 8);
        adjmatrix.addEdge(1, 3);

        adjmatrix.printGraph();
    }
}
