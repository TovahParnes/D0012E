import java.util.*;

import java.time.*;

// Implementation of Prim's minimum spanning tree algorithm using min heap with adjacency-matrix
public class Prims2b {

    private TreeSet<Node> Q = new TreeSet<Node>(new comparator());  // This will act as our min-heap with comparator defined further down
    private Node[] v;
    private Boolean[] inMST;
    private int[][] matrix;
    private ArrayList<Node> MST = new ArrayList<Node>();


    public Prims2b(AdjMatrix G, int root) {       
        this.matrix = new int[G.getMatrix().length-1][G.getMatrix().length-1];    // Save the matrix from AdjMatrix locally for easier handling
        for (int i=0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = G.getMatrix()[i+1][j+1];
            }
        }

        v = new Node[matrix.length];
        inMST = new Boolean[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            v[i] = new Node(i+1);   // i+1 because we can't have 0th node
            v[i].setKey(Integer.MAX_VALUE);
            inMST[i] = false;
        }

        v[root-1].setKey(0);
        inMST[root-1] = true;
        for (int i=0; i < matrix.length; i++) { // Fill up the queue with all the vertices
            Q.add(v[i]);                
        }
    }

    class comparator implements Comparator<Node> {
        public int compare(Node node1, Node node2) {
            return node1.key - node2.key;
        }
    }

    public ArrayList<Node> getMST() {   // Run to get MST
        Instant start = Instant.now();

        Node u = new Node(0);           // Temp initialization
        int[] adj = new int[matrix.length];  // Initialize a vector
        while (!Q.isEmpty()) {               // Run until queue is empty
            u = Q.pollFirst();
            inMST[u.getVertex()-1] = true;                           
            MST.add(u);

            adj = matrix[u.getVertex()-1];      // Get the list of edges connecting "u" (one row)
            for (int i = 0; i < adj.length; i++) {
                if (adj[i]!=0) { // If there is a connection
                    if (inMST[i] == false) { // If not in MST
                        if (adj[i] < v[i].getKey()) {
                            Q.remove(v[i]);
                            v[i].setKey(adj[i]);
                            v[i].setParent(u);
                            Q.add(v[i]);                                                     
                        }
                    }
                }                          
            }
        }
        Instant end = Instant.now();
        Duration interval = Duration.between(start, end);
        System.out.println("Execution time for matrix, heap: " + interval.toMillis() + " ms.");     
        
        return this.MST;
    }

    public void printMST() {
        ArrayList<Node> graph = getMST();

        for (Node node : graph) {
            System.out.print(node.getVertex() + "(" + node.getKey() + ") ->");
            if (node.getParent()!= null) {
                System.out.println(node.getParent().getVertex());                
            } else {
                System.out.println("null");
            }
        }
    }

    public static void main(String[] args) {
        AdjMatrix graph = new AdjMatrix(4);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 8);
        graph.addEdge(1, 4, 3);
        graph.addEdge(2, 3, 5);
        graph.addEdge(4, 2, 4);
        graph.addEdge(4, 3, 10);

        Prims2b prim2b = new Prims2b(graph,1);
        prim2b.printMST();
    }
}