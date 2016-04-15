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
		double cost=0;
		ArrayList<Node>srcnodes=new ArrayList<Node>();
		ArrayList<Node>dstnodes=new ArrayList<Node>();
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
					
					
				}
				if(eb1.getnode1().equals(dstname)){
					dstBB.add(eb1.getnode2());
					
				}
				
			}
			
			//Find connected nodes to backbone
			
			for(Backbone src:srcBB){
				//System.out.println(src.getname());
				//System.out.println(src.getlocation());
				int srclcn=src.getlocation();
				Map<String,Node> map = allNodes;
				

				for (Map.Entry<String,Node> entry : map.entrySet()) {
				    String key = entry.getKey();
				    Node nodeit = entry.getValue();
				    if(nodeit.getBlocation()==srclcn){
				    	//one of the source nodes
				    	srcnodes.add(nodeit);
				    	
					}
				  
				    
				    
				    
				    
				}
			
			}
			for(Backbone dst:dstBB){
				//System.out.println(src.getname());
				//System.out.println(src.getlocation());
				int dstlcn=dst.getlocation();
				Map<String,Node> map = allNodes;
				

				for (Map.Entry<String,Node> entry : map.entrySet()) {
				    String key = entry.getKey();
				    Node nodeit = entry.getValue();
				    if(nodeit.getBlocation()==dstlcn){
				    	//one of the destination nodes
				    	dstnodes.add(nodeit);
				    	
					}
				  
				    
				    
				    
				    
				}
			
			}
			
			
			cost+=mincost(srcnodes,dstnodes,allNodes);
			
			
			
			
			
			
			//Find route of traffic from example links and optical links
			//Assign traffic to optical links
			
			
			
				

			
			
			
			
			
		
	}
		System.out.println(cost);
		
     	
	}
	
	public static double mincost(ArrayList<Node>srcnodes,ArrayList<Node>dstnodes,HashMap<String, Node> allNodes){
		
		double mincost=Double.POSITIVE_INFINITY;
		double cost=0;
		for (Node srcnode:srcnodes){
			
			
			
			for(Node dstnode:dstnodes){
				//Check if within same location
				
				if(srcnode.getBlocation()==dstnode.getBlocation()){
					return 50;
				}
				
				try{
				 cost=Dijkstra.cost.get(allNodes.get(srcnode.getName())).get(allNodes.get(dstnode.getName()));
				} catch(NullPointerException n){
					System.out.println("No paths found error");
					System.out.println(srcnode.getName()+" "+dstnode.getName());
					
				}
				
				
				if (cost<mincost){
					mincost=cost;
				}
				
				
				
			   
			}
			
		}
		return (100+0.1*mincost);
	}

		
	
	

}
