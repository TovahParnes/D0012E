import java.util.*;

public class AdjList {
    private int vertex;
    private ArrayList<LinkedList<Edge>> list;

    public AdjList(int vertex) { // Constructor for graph class
        this.vertex = vertex;
        list = new ArrayList<LinkedList<Edge>>(); // Instanciate the list.

        for (int i = 0; i < (vertex+1); i++) {
            list.add(new LinkedList<Edge>()); // Initialize list
        }
    }

    public void addVertex() {   // Add a new vertex to the graph
        this.vertex++;
        list.add(new LinkedList<Edge>());
    }

    public String toString() {  // Check the number of vertices
        return "Vertices: " + this.vertex;
    }

    public int getVertex() {
        return this.vertex;
    }

    public ArrayList<LinkedList<Edge>> getList() {
        return list;
    }

    public void addEdge(int from, int to, int weight) { // Add a new edge between two vertices with weight
        if (weight < 0) {   // Check for invalid weight
            //System.out.println("Invalid weight.");
            return;
        }
        if (from > this.vertex || to > this.vertex) {   // Check if vertex exists
            //System.out.println("Invalid vertex/vertices.");
            return;
        }
        if (from == to) {   // Check for attempt to connect to self
            //System.out.println("Can't create edge to self.");
            return;
        }
        for (int i = 0; i < list.get(from).size(); i++) {   // Check if connection exists 
            if(list.get(from).get(i).getConnection() == to){
                //System.out.println("Edge already exists.");
                return;
            }
        }
        list.get(from).add(new Edge(to, weight));
        list.get(to).add(new Edge(from, weight));
    }

    public void addEdge(int from, int to) { // Alternate method to add edge without set weight (default weight = 1)
        if (from > this.vertex || to > this.vertex) {   // Check if vertex exists
            //System.out.println("Invalid vertex/vertices.");
            return;
        }
        if (from == to) {   // Check for attempt to connect to self
            //System.out.println("Can't create edge to self.");
            return;
        }
        for (int i = 0; i < list.get(from).size(); i++) {   // Check if connection exists 
            if(list.get(from).get(i).getConnection() == to){
                //System.out.println("Edge already exists.");
                return;
            }
        }
        list.get(from).add(new Edge(to));   
        list.get(to).add(new Edge(from));
    }

    public boolean setWeight(int from, int to, int weight) {    // Set the weight for a connection, calls findEdge to get the index of the edge in linkedlist
        if (weight < 0) {   // Check for invalid weight
            System.out.println("Invalid weight.");
            return false;
        }
        int index = findEdge(from, to);
        if (index == -1) {  // No matching edge was found
            return false;
        }
        list.get(from).get(index).setWeight(weight);
        index = findEdge(to, from);
        if (index == -1) {
            return false;
        }
        list.get(to).get(index).setWeight(weight);

        return true;
    }

    public void printGraph() {                  // Prints all connections between vertices with weights in parentheses
        for (int i = 1; i < vertex+1; i++) {
            if (list.get(i).size() > 0) {
                System.out.print(i + " -> ");
                for (int j = 0; j < list.get(i).size(); j++) {
                    System.out.print(list.get(i).get(j).getConnection() + "(" + list.get(i).get(j).getWeight() + ") ");
                }
                System.out.println();
            } else {
                System.out.println(i);
            }
        }
    }

    public void printGraph(int vertex) {        // Alternate to printGraph for a specific vertex
        if (list.get(vertex).size() > 0) {
            System.out.print(vertex + " -> ");
            for (int i = 0; i < list.get(vertex).size(); i++) {
                System.out.print(list.get(vertex).get(i).getConnection() + "(" + list.get(vertex).get(i).getWeight() + ") ");
            }
            System.out.println();
        } else {
            System.out.println("Vertex " + vertex + " has no connections.");
        }
    }

    public void checkConnection(int v1, int v2) {   // Check if two vertices are connected
        if(list.get(v1).size() < 0) {
            System.out.println("Vertex " + v1 + " has no connections.");
        } else if (list.get(v2).size() < 0) {
            System.out.println("Vertex " + v2 + " has no connections.");
        } else {
            for (int i = 0; i < list.get(v1).size(); i++) {
                if(list.get(v1).get(i).getConnection() == v2){
                    System.out.println(v1 + " and " + v2 + " are connected, with weight " + list.get(v1).get(i).getWeight() + ".");
                    return;
                } 
            }
            System.out.println(v1 + " and " + v2 + " are not connected.");
        }
    }

    private int findEdge(int from, int to) {    // Helper function to find index of an edge between two vertices within the linkedlist, -1 if it doesn't exist.
        int index = 0;

        while (list.get(from).get(index) != null) {
            if (list.get(from).get(index).getConnection() == to) {
                return index;
            }
            index++;
        }
        return -1;
    }

    public void fillGraph(int maxWeight) {      // Populates graph randomly with edges and weights
        int max = (int)(0.5 * vertex * (vertex-1));    // Max number of edges possible
        Random rand = new Random();             // Class to generate random numbers
        max = rand.nextInt(max+1);
        for (int i=1; i < max; i++) {
            addEdge(rand.nextInt(vertex)+1,rand.nextInt(vertex)+1,rand.nextInt(maxWeight)+1);
        }        
    }

    public static void main(String[] args) {
        AdjList graph = new AdjList(10);
        graph.printGraph();
        System.out.println();
        graph.fillGraph(10);
        graph.printGraph();
    }
}