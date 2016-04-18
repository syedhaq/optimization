import java.util.ArrayList;


public class Edge {
	private Node startnode;
	private Node end;
	private double cost;
	private double time;
	private ArrayList<Traffic> trafar=new ArrayList<Traffic>();
	private int capacity;
	private double trafficp1;
	private double trafficp2;
	
	
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

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public double getTrafficp1(){
		trafficp1=0; 
		for(Traffic tr:this.getTraffic()){
	    	//Get both priority 1 and priority two traffic
	    	trafficp1+=tr.getP1();
	    	
	    }
		return trafficp1/1000;
		
	}
	public double getTrafficp2(){
		trafficp2=0; 
		for(Traffic tr:this.getTraffic()){
	    	//Get both priority 1 and priority two traffic
	    	trafficp2+=tr.getP2();
	    	
	    }
		return trafficp2/1000;
	
	                           }
}
