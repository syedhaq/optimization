import java.util.ArrayList;

//This class contains the example links 
public class ExampleLinks {

	private String linkname,end1,end2,distance,delay;
	Double numberoflinks;
	String[] link;
	public ExampleLinks(ArrayList<String>exampleBB){
		linkname=exampleBB.get(0);
		end1=exampleBB.get(1);
		end2=exampleBB.get(2);
		distance=exampleBB.get(3);
		delay=exampleBB.get(4);
		numberoflinks=Double.valueOf(exampleBB.get(6));
		link=new String[numberoflinks.intValue()];
		for(int i=7;i<(7+link.length);i++){
			link[i-7]=exampleBB.get(i);
		}
		
		
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
		return Double.parseDouble(delay);
	}
	
	
}

