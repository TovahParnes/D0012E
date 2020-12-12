import java.util.*;

import java.time.*;

// Implementation of Prim's minimum spanning tree algorithm using unsorted lists with adjacency-list
public class Prims1 {

    private ArrayList<Node> Q = new ArrayList<Node>();
    private Boolean[] inMST;
    private Node[] v;
    private ArrayList<LinkedList<Edge>> list = new ArrayList<LinkedList<Edge>>();
    private ArrayList<Node> MST = new ArrayList<Node>();

    public Prims1(AdjList G, int root) {
        this.list = G.getList(); // Save the list from AdjList locally for easier handling
        inMST = new Boolean[G.getVertex()];
        v = new Node[G.getVertex()];

        for (int i = 0; i < G.getVertex(); i++) { // Initalize nodes, keys = inf
            v[i] = new Node(i+1);
            v[i].setKey(Integer.MAX_VALUE);
            inMST[i] = false;
        }

        inMST[root - 1] = true;
        v[root - 1].setKey(0); // Set the root node key to 0

        for (int i = 0; i < G.getVertex(); i++) { // Fill up the queue with all the vertices, keys = inf
            Q.add(v[i]);
        }
    }

    public ArrayList<Node> getMST() { // Run to get MST
        Instant start = Instant.now();

        Node u = new Node(0); // Temp initialization
        LinkedList<Edge> adj = new LinkedList<Edge>(); // Initalize list

        while (!Q.isEmpty()) { // Run until queue is empty
            u = extractMin();
            MST.add(u);
            inMST[u.getVertex()-1] = true;

            adj = list.get(u.getVertex()); // Get the list of edges connecting "u"

            for (Edge edge : adj) {
                int dest = edge.getConnection();
                if (inMST[dest - 1] == false) { // If not in MST 

                    int index = Q.indexOf(v[dest-1]);   // Find node index in queue
                    if (edge.getWeight() < Q.get(index).getKey()) {
                        Q.get(index).setKey(edge.getWeight());
                        Q.get(index).setParent(u);
                    }
                }
            }
        }

        Instant end = Instant.now();
        Duration interval = Duration.between(start, end);
        System.out.println("Execution time for list, list: " + interval.toMillis() + " ms.");

        return this.MST;
    }

    public void printMST() {
        ArrayList<Node> graph = getMST();

        for (Node node : graph) {
            System.out.print(node.getVertex() + "(" + node.getKey() + ") ->");
            if (node.getParent() != null) {
                System.out.println(node.getParent().getVertex());
            } else {
                System.out.println("null");
            }
        }
    }

    private Node extractMin() { // Get the node with smallest key in queue, remove and return it
        Node output = Q.get(0); // Start with the node at the top of queue

        for (int i = 0; i < Q.size(); i++) { // Find the node with smallest key
            if (Q.get(i).getKey() < output.getKey()) {
                output = Q.get(i);
            }
        }
        Q.remove(output); // Find and remove the selected node from queue
        return output;
    }

    public static void main(String[] args) {
        AdjList graph = new AdjList(9250);
        graph.fillGraph(1000);
        System.out.println("AdjList done");

        Prims1 prim1 = new Prims1(graph, 1);
        prim1.getMST();
        Prims1b prim1b = new Prims1b(graph, 1);    
        prim1b.getMST();

    }
}
