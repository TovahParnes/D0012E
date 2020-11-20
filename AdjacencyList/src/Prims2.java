import java.util.*;

// Implementation of Prim's minimum spanning tree algorithm using unsorted list with adjacency-matrix
public class Prims2 {

    private ArrayList<Node> Q = new ArrayList<Node>();
    private int[][] matrix;
    private ArrayList<Node> MST = new ArrayList<Node>();


    public Prims2(AdjMatrix G, int root) {       
        this.matrix = new int[G.getMatrix().length-1][G.getMatrix().length-1];    // Save the matrix from AdjMatrix locally for easier handling
        for (int i=0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = G.getMatrix()[i+1][j+1];
            }
        }
        for (int i=0; i < matrix.length; i++) { // Fill up the queue with all the vertices, keys = inf
            Q.add(new Node(i+1));               // i+1 because we can't have 0th node
        }
        
        Q.get(root-1).setKey(0); // Set the root node key to 0
    }

    public ArrayList<Node> getMST() {   // Run to get MST
        Node u = new Node(0);           // Temp initialization
        int[] adj = new int[matrix.length];  // Initialize a vector
        while (!Q.isEmpty()) {               // Run until queue is empty
            u = extractMin();                           
            MST.add(u);
            adj = matrix[u.getVertex()-1];      // Get the list of edges connecting "u" (one row)
            for (int i = 1; i < adj.length+1; i++) {
                for (Node node : Q) {
                    if (((i+1) == node.getVertex()) && adj[i] < node.getKey()) {   // If node is in queue and its new weight is smaller 
                        node.setKey(adj[i]);  // Change the weight of node
                        node.setParent(u);              // And point it to the new parent
                    }
                }                          
            }
        }
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
        AdjMatrix graph = new AdjMatrix(4);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 8);
        graph.addEdge(1, 4, 3);
        graph.addEdge(2, 3, 5);
        graph.addEdge(4, 2, 4);
        graph.addEdge(4, 3, 10);

        Prims2 mst = new Prims2(graph, 1);
        mst.printMST();

    }
}
