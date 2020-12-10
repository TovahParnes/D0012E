import java.util.*;

import java.time.*;

// Implementation of Prim's minimum spanning tree algorithm using unsorted list with adjacency-matrix
public class Prims2 {

    private ArrayList<Node> Q = new ArrayList<Node>();
    private int[][] matrix;
    private Boolean[] inMST;
    private Node[] v;
    private ArrayList<Node> MST = new ArrayList<Node>();


    public Prims2(AdjMatrix G, int root) {       
        this.matrix = new int[G.getMatrix().length-1][G.getMatrix().length-1];    // Save the matrix from AdjMatrix locally for easier handling
        for (int i=0; i < matrix.length; i++) {                                   // Removes zeros from adjmatrix matrix 
            for (int j = 0; j < matrix.length; j++) {                           
                matrix[i][j] = G.getMatrix()[i+1][j+1];
            }
        }

        inMST = new Boolean[matrix.length];
        v = new Node[matrix.length];

        for (int i=0; i < matrix.length; i++) { // Initalize nodes, keys = inf
            v[i] = new Node(i+1);               // i+1 because we can't have 0th node
            v[i].setKey(Integer.MAX_VALUE);
            inMST[i] = false;
        }

        inMST[root-1] = true;
        v[root-1].setKey(0);

        for (int i = 0; i < matrix.length; i++) {   // FIll queue with nodes
            Q.add(v[i]);
        }
    }

    public ArrayList<Node> getMST() {   // Run to get MST
        Instant start = Instant.now();

        Node u = new Node(0);           // Temp initialization
        int[] adj = new int[matrix.length];  // Initialize a vector

        while (!Q.isEmpty()) {               // Run until queue is empty
            u = extractMin();                           
            MST.add(u);
            inMST[u.getVertex()-1] = true;

            adj = matrix[u.getVertex()-1];      // Get the list of edges connecting "u" (one row)

            for (int i = 0; i < adj.length; i++) {
                if (adj[i] != 0) {  // If there is a connection its >0
                    if (inMST[i] == false) {
                        int index = Q.indexOf(v[i]);   // Find node index in queue
                        if (adj[i] < Q.get(index).getKey()) {
                            Q.get(index).setKey(adj[i]);    // Change the weight of node
                            Q.get(index).setParent(u);  // And point it to the new parent
                        }
                    }
                }                          
            }
        }
        Instant end = Instant.now();
        Duration interval = Duration.between(start, end);
        System.out.println("Execution time for matrix, list: " + interval.toMillis() + " ms.");     
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

    private Node extractMin() {  // Get the node with smallest key in queue, remove and return it
        Node output = Q.get(0); // Start with the node at the top of queue

        for (int i = 0; i < Q.size(); i++) {    // Find the node with smallest key
            if (Q.get(i).getKey() < output.getKey()) {  
                output = Q.get(i);                      
            }
        }
        Q.remove(output);   // Find and remove the selected node from queue
        return output;
    }

    public static void main(String[] args) {
        AdjMatrix graph = new AdjMatrix(9000);
        graph.fillGraph(1000);
        System.out.println("AdjMatrix done");

        //Prims2 prim2 = new Prims2(graph, 1);
        Prims2b prim2b = new Prims2b(graph, 1);

        //prim2.getMST();
        prim2b.getMST();
    }
}
