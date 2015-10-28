import java.util.ArrayList;


public class edgetoBB {
	private String linkname,end1,end2,distance,time;
	private double traffic;
	
	public edgetoBB(ArrayList<String> edgebb){
		linkname=edgebb.get(0);
		end1=edgebb.get(1);
		end2=edgebb.get(2);
		distance=edgebb.get(3);
		time=edgebb.get(4);
		traffic=0;
	}
	public String getLinkname(){
		return linkname;
	}
	
	public String getnode1(){
		return end1;
	}
	
	public String getnode2(){
		return end2;
	}
	
	public double getdistance(){
		//Convert String to number and return
		return Double.parseDouble(distance);
	}
	
	public double gettime(){
		return Double.parseDouble(time);
	}
	
	public void assigntraffic(double intraffic){
		this.traffic=traffic+intraffic;
		
	}
	
	

}
