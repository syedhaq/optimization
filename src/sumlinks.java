import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


//This class is the meat that finds sum of all links
//Takes a traffic object and assigns its traffic to capacity
public class sumlinks {
	
	
	public static void addtraffic(ArrayList<Traffic> allTraffic,
			ArrayList<edgetoBB> edgetoback,
			 HashMap<String, Node> allNodes) {
		int k=0;
		for(Traffic tr1:allTraffic){
			//Find BackBone Location of Source and destination
			ArrayList<Backbone>srcBB=new ArrayList<Backbone>();
			ArrayList<Backbone>dstBB=new ArrayList<Backbone>();
			Edgerouter sourcename=tr1.getSource();
			Edgerouter dstname=tr1.getDestination();
			//Find connecting backbone routers
			for(edgetoBB eb1:edgetoback){
				
				if(eb1.getnode1().equals(sourcename)){
					srcBB.add(eb1.getnode2());
					//System.out.println(eb1.getnode2().getname());
					k++;
					
				}
				if(eb1.getnode1().equals(dstname)){
					dstBB.add(eb1.getnode2());
					
				}
				
			}
			
			//Find connected nodes
			
			for(Backbone src:srcBB){
				//System.out.println(src.getname());
				//System.out.println(src.getlocation());
				int srclcn=src.getlocation();
				Map<String,Node> map = allNodes;

				for (Map.Entry<String,Node> entry : map.entrySet()) {
				    String key = entry.getKey();
				    Node srcnode = entry.getValue();
				    if(srcnode.getBlocation()==srclcn){
					}
				    
				    
				}
			
			}
			
			
			//Find route of traffic from example links and optical links
			//Assign traffic to optical links
			
			
				

			
			
			
			
			
		
	}
		
		System.out.println(k);
	
	}

		
	
	

}
