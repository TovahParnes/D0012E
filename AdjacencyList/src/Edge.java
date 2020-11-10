public class Edge {
	private int weight;
	private int connection;
	
	public Edge(int to) {
		this.connection = to;
		this.weight = 0;
	}
	
	public Edge(int to, int weight) {
		this.connection = to;
		this.weight = weight;
	}
	
	public boolean setWeight(int weight) {
		if(weight<0) {
			return false;
		}
		this.weight = weight;
		return true;
	}
	
	public int getWeight() {
		return this.weight;
	}
	
	public int getConnection() {
		return this.connection;
	}
	
}
