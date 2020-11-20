public class Node {
    int vertex;
    int key = Integer.MAX_VALUE;
    Node parent = null;
        
    public Node(int v) {
        this.vertex = v;
    }

    public Node(int v, int key, Node pi) {
        this.vertex = v;
        this.key = key;
        this.parent = pi;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getKey() {
        return key;
    }

    public int getVertex() {
        return vertex;
    }

    public Node getParent() {
        return parent;
    }

}
