import java.util.*;

import java.time.*;

// Implementation of Prim's minimum spanning tree algorithm using min heap with adjacency-list
public class Prims1b {

    private ArrayList<Node> Q = new ArrayList<Node>();
    private ArrayList<LinkedList<Edge>> list = new ArrayList<LinkedList<Edge>>();
    private ArrayList<Node> MST = new ArrayList<Node>();

    public Prims1b(AdjList G, int root) {
        this.list = G.getList(); // Save the list from AdjList locally for easier handling

        for (int i = 0; i < G.getVertex(); i++) { // Fill up the queue with all the vertices, keys = inf
            Q.add(new Node(i + 1)); // i+1 because we can't have 0th node
        }

        Q.get(root - 1).setKey(0); // Set the root node key to 0
        fixHeap(root - 1);
    }
    public ArrayList<Node> getMST() { // Run to get MST
        Instant start = Instant.now();
        Node u = new Node(0); // Temp initialization
        LinkedList<Edge> adj = new LinkedList<Edge>(); // Initalize list
        while (!Q.isEmpty()) { // Run until queue is empty
            u = extractMin();
            MST.add(u);
            adj = list.get(u.getVertex()); // Get the list of edges connecting "u"
            for (Edge edge : adj) {
                for (Node node : Q) {
                    if ((edge.getConnection() == node.getVertex()) && edge.getWeight() < node.getKey()) { // If node is in queue and weight is smaller                                                                                                        // in queu                                                                                                          // and its new                                                                                                         // weight is                                                                                                         // smaller
                        node.setKey(edge.getWeight()); // Change the weight of node
                        node.setParent(u); // And point it to the new parent
                        fixHeap(Q.indexOf(node));
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

    private Node extractMin() { // Get the node with smallest key in queue, remove and return it
        Node output = Q.get(0); // In min-heap structure it's the first element
        Q.remove(output);

        return output;
    }

    private void swap(int i1, int i2) { // Used for min-heap handling, swaps places of nodes at index i1, i2
        Node temp = Q.get(i1); // Save node at i1
        Q.set(i1, Q.get(i2)); // Replace with node from i2
        Q.set(i2, temp); // and put back node from i1 at new spot
    }

    private void fixHeap(int index) { // Used for min-heap handling, reorders the heap after adding element, input index of recently added element
        int parent;
        if (index != 0) { // No operation required if already at root
            parent = (int) ((index - 1) * 0.5); // Get the parent's index
            if (Q.get(parent).getKey() > Q.get(index).getKey()) { // Check if any reordering needs to be done
                swap(parent, index);
                fixHeap(parent); // Recursive call to check at parent's node (now swapped)
            }
        }
    }

    public static void main(String[] args) {
        AdjList graph = new AdjList(4);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 8);
        graph.addEdge(1, 4, 3);
        graph.addEdge(2, 3, 5);
        graph.addEdge(4, 2, 4);
        graph.addEdge(4, 3, 10);
        graph.printGraph();

        Prims1b prim1b = new Prims1b(graph, 1);
        prim1b.printMST();
    }
}
