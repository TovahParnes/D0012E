import java.util.*;

import java.time.*;

// Implementation of Prim's minimum spanning tree algorithm using min heap with adjacency-matrix
public class Prims2b {

    private ArrayList<Node> Q = new ArrayList<Node>();
    private int[][] matrix;
    private ArrayList<Node> MST = new ArrayList<Node>();


    public Prims2b(AdjMatrix G, int root) {       
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
        fixHeap(root-1);
    }

    public ArrayList<Node> getMST() {   // Run to get MST
        Instant start = Instant.now();
        Node u = new Node(0);           // Temp initialization
        int[] adj = new int[matrix.length];  // Initialize a vector
        while (!Q.isEmpty()) {               // Run until queue is empty
            u = extractMin();                           
            MST.add(u);
            adj = matrix[u.getVertex()-1];      // Get the list of edges connecting "u" (one row)
            for (int i = 0; i < adj.length; i++) {
                for (Node node : Q) {
                    if (((i+1) == node.getVertex()) && adj[i] < node.getKey()) {   // If node is in queue and its new weight is smaller 
                        node.setKey(adj[i]);  // Change the weight of node
                        node.setParent(u);              // And point it to the new parent
                        fixHeap(Q.indexOf(node));
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

    private Node extractMin() {  // Get the node with smallest key in queue, remove and return it
        Node output = Q.get(0); // Start with the node at the top of queue
        Q.remove(output);   // Find and remove the selected node from queue
        return output;
    }

    private void swap(int i1, int i2) { // Used for min-heap handling, swaps places of nodes at index i1, i2
        Node temp = Q.get(i1);          // Save node at i1
        Q.set(i1, Q.get(i2));           // Replace with node from i2
        Q.set(i2, temp);                // and put back node from i1 at new spot
    }

    private void fixHeap(int index) {    // Used for min-heap handling, reorders the heap after adding element, input index of recently added element    
        int parent;
        if (index != 0){                                            // No operation required if already at root
            parent = (int)((index-1)*0.5);                          // Get the parent's index
            if(Q.get(parent).getKey() > Q.get(index).getKey()) {    // Check if any reordering needs to be done
                swap(parent, index);
                fixHeap(parent);                                    // Recursive call to check at parent's node (now swapped)
            }               
        }       
    }

    public static void main(String[] args) {

    }
}