import java.util.*;

// Implementation of Prim's minimum spanning tree algorithm using unsorted lists with adjacency-list
public class Prims1 {

    private ArrayList<Node> Q = new ArrayList<Node>();
    private ArrayList<LinkedList<Edge>> list = new ArrayList<LinkedList<Edge>>();
    private ArrayList<Node> MST = new ArrayList<Node>();


    public Prims1(AdjList G, int root) {
        this.list = G.getList();    // Save the list from AdjList locally for easier handling

        for (int i=0; i < G.getVertex(); i++) { // Fill up the queue with all the vertices, keys = inf
            Q.add(new Node(i+1));               // i+1 because we can't have 0th node
        }
        
        Q.get(root-1).setKey(0); // Set the root node key to 0
    }

    public ArrayList<Node> getMST() {   // Run to get MST
        Node u = new Node(0);           // Temp initialization
        LinkedList<Edge> adj = new LinkedList<Edge>();  // Initalize list
        while (!Q.isEmpty()) {                          // Run until queue is empty
            u = extractMin();                           
            MST.add(u);
            adj = list.get(u.getVertex());      // Get the list of edges connecting "u"
            for (Edge edge : adj) {
                for (Node node : Q) {
                    if ((edge.getConnection() == node.getVertex()) && edge.getWeight() < node.getKey()) {   // If node is in queue and its new weight is smaller 
                        node.setKey(edge.getWeight());  // Change the weight of node
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
        AdjList graph = new AdjList(4);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 8);
        graph.addEdge(1, 4, 3);
        graph.addEdge(2, 3, 5);
        graph.addEdge(4, 2, 4);
        graph.addEdge(4, 3, 10);
        
        Prims1 mst = new Prims1(graph, 1);
        mst.printMST();

    }
}
