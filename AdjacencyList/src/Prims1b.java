import java.util.*;

import java.time.*;

// Implementation of Prim's minimum spanning tree algorithm using min heap with adjacency-list
public class Prims1b {

    private TreeSet<Node> Q = new TreeSet<Node>(new comparator());  // This will act as our min-heap with comparator defined further down
    private Node[] v;
    private Boolean[] inMST;    // Keep track of which vertices we have added
    private ArrayList<LinkedList<Edge>> list = new ArrayList<LinkedList<Edge>>();
    private ArrayList<Node> MST = new ArrayList<Node>();

    public Prims1b(AdjList G, int root) {
        this.list = G.getList(); // Save the list from AdjList locally for easier handling
        v = new Node[G.getVertex()];
        inMST = new Boolean[G.getVertex()];

        for (int i = 0; i < G.getVertex(); i++) { // Initialize all the vertices, keys = inf
            v[i] = new Node(i+1);   // i+1 because we can't have 0th node   
            v[i].setKey(Integer.MAX_VALUE); 
            inMST[i] = false;
        }

        // -1 because 1st node is at index 0 etc.
        v[root-1].setKey(0);
        inMST[root-1] = true;
        
        // Add nodes to our queue
        for (int i = 0; i < G.getVertex(); i++) {
            Q.add(v[i]);
        }
    }

    class comparator implements Comparator<Node> {
        public int compare(Node node1, Node node2) {
            return node1.key - node2.key;
        }
    }

    public ArrayList<Node> getMST() { // Run to get MST
        Instant start = Instant.now();

        LinkedList<Edge> adj = new LinkedList<Edge>(); // Initalize list
        while (!Q.isEmpty()) { // Run until queue is empty
            Node u = Q.pollFirst(); // Remove u from queue, first place, minkey
            inMST[u.getVertex()-1] = true;
            MST.add(u);

            adj = list.get(u.getVertex()); // Get the list of edges connecting "u"

            for (Edge edge : adj) { // For each edge connected to u
                int dest = edge.getConnection();
                if(inMST[dest-1] == false) { // If not in MST

                    if ( v[dest-1].getKey() > edge.getWeight() ) {    // If the vertex weight needs update
                        Q.remove(v[dest-1]);
                        v[dest-1].setKey(edge.getWeight());
                        v[dest-1].setParent(u);
                        Q.add(v[dest-1]);
                    }
                }
            }
        }

        Instant end = Instant.now();
        Duration interval = Duration.between(start,end);
        System.out.println("Execution time for list, heap: " + interval.toMillis() + " ms.");

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

    public static void main(String[] args) {
        AdjList graph = new AdjList(8000);
        graph.fillGraph(1000);
        // AdjList graph = new AdjList(10);
        // graph.fillGraph(50);
        System.out.println("AdjList done");

        Prims1b prim1b = new Prims1b(graph, 1);
        prim1b.getMST();
    }
}
