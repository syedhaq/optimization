
public class Edge {
	
	private Node end;
	private double cost;
	private double time;
	
	
	public Edge(Node endnode,double costn,double time){
		setEnd(endnode);
		setCost(costn);
		setTime(time);
		
		
		
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public Node getEnd() {
		return end;
	}

	public void setEnd(Node end) {
		this.end = end;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}



}
