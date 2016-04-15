import java.util.ArrayList;


public class Edge {
	private Node startnode;
	private Node end;
	private double cost;
	private double time;
	private ArrayList<Traffic> trafar=new ArrayList<Traffic>();
	
	
	public Edge(Node startnode,Node endnode,double costn,double time){
		setStartnode(startnode);
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

	public Node getStartnode() {
		return startnode;
	}

	public void setStartnode(Node startnode) {
		this.startnode = startnode;
	}

	public ArrayList<Traffic> getTraffic() {
		return trafar;
	}

	public Edge setTraffic(Traffic tr) {
		trafar.add(tr);
		//Adds traffic to edge and returns edge
		return this;
		
	}
	
	



}
