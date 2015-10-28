import java.util.ArrayList;

//This class takes in an ArrayList of OpticalLinks


public class Optical_backbone_links {
	private String linkname,node1,node2, distance,time;
	private double traffic;
	
	
	public Optical_backbone_links (ArrayList<String> rowcell){
		//Get all elements of the node
	linkname=rowcell.get(0);
	node1=rowcell.get(1);
	node2=rowcell.get(2);
	distance=rowcell.get(3);
	time=rowcell.get(4);
	traffic=0;
		       }
	
	public String getLinkname(){
		return linkname;
	}
	
	public String getnode1(){
		return node1;
	}
	
	public String getnode2(){
		return node2;
	}
	
	public double getdistance(){
		//Convert String to number and return
		return Double.parseDouble(distance);
	}
	
	public double gettime(){
		return Double.parseDouble(time);
	}
	
	//Assign traffic to link
	public void assignTraffic(double intraffic){
		this.traffic=traffic+intraffic;
	}
	
	public double getTraffic(){
		return this.traffic;
	}
	
	
	}
	
	

