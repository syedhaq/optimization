import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


//This class is the meat that finds sum of all links
//Takes a traffic object and assigns its traffic to capacity
public class sumlinks {
	
	private static double cost=0;
	static HashMap<String, Edge> usedEdges = new HashMap<>();
	
	
	public static HashMap<String,Edge> addtraffic(ArrayList<Traffic> allTraffic,
			ArrayList<edgetoBB> edgetoback,
			 HashMap<String, Node> allNodes,HashMap<String, Edge> allEdgelinks, int mode) {
		
		cost = 0;
		
		for(Traffic tr1:allTraffic){
			ArrayList<Node>srcnodes=new ArrayList<Node>();
			ArrayList<Node>dstnodes=new ArrayList<Node>();
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
				
				//System.out.println("Src"+src.getname()+" ,"+ src.getlocation());
				//System.out.println(src.getname());
				//System.out.println(src.getlocation());
				int srclcn=src.getlocation();
				
				Map<String,Node> map = allNodes;
				
				
				for(Node nodeit : map.values()){
					if(nodeit.getBlocation()==srclcn && srclcn!=0 && !srcnodes.contains(nodeit)){
				    	//one of the source nodes
				    	//System.out.println("yo");
				    	srcnodes.add(nodeit);
				    	
				    	//System.out.println(nodeit.getBlocation()+" " +srclcn);
				    	//System.out.println("Adding node"+nodeit.getName()+"for backbone"+src.getname());
				    	
					}
				}

//				for (Map.Entry<String,Node> entry : map.entrySet()) {
//				    String key = entry.getKey();
//				    Node nodeit = entry.getValue();
//				    
//				    if(nodeit.getBlocation()==srclcn && srclcn!=0 && !srcnodes.contains(nodeit)){
//				    	//one of the source nodes
//				    	System.out.println("yo");
//				    	srcnodes.add(nodeit);
//				    	
//				    	System.out.println(nodeit.getBlocation()+" " +srclcn);
//				    	System.out.println("Adding node"+nodeit.getName()+"for backbone"+src.getname());
//				    	
//					}
				  				    
				    
//				}
			
			}
						for(Backbone dst:dstBB){
							//System.out.println("Dst"+dst.getname()+" ,"+ dst.getlocation());

							//System.out.println(src.getname());
							//System.out.println(src.getlocation());
							int dstlcn=dst.getlocation();
							Map<String,Node> map = allNodes;
							
			
							for (Map.Entry<String,Node> entry : map.entrySet()) {
							    String key = entry.getKey();
							    Node nodeit = entry.getValue();
							    if(nodeit.getBlocation()==dstlcn && dstlcn!=0 && !dstnodes.contains(nodeit)){
							    	//one of the destination nodes
							    	dstnodes.add(nodeit);
							    	
								}
							  
							    
							    
							    
							    
							}
						
						}
						
			//System.out.println("sNodes for "+tr1.getTunit());			
//			for (Node scrnode:srcnodes){
//				
//				System.out.print(scrnode.getName());
//				
//			}
//			
//			System.out.println();
//			System.out.println("dNodes for "+tr1.getTunit());
//			for (Node dstnode:dstnodes){
//				
//				System.out.print(dstnode.getName());
//				
//			}
//			//System.out.println();
			mincost(srcnodes,dstnodes,allNodes,tr1,allEdgelinks);
			
				

			
			
			
			
			
		
	}
		//System.out.println(cost);
		//System.out.println(usedEdges.isEmpty());
		Map<String,Edge> allEdgemap = usedEdges;
		

		for (Map.Entry<String,Edge> entry : allEdgemap.entrySet()) {
		    String key = entry.getKey();
		    Edge edgeit = entry.getValue();
		    double Totaltraffic = 0;
		    for(Traffic tr : edgeit.getTraffic()){
		    	//Get both priority 1 and priority two traffic
		    	Totaltraffic += tr.getP1()+ 0.5 * tr.getP2();
		    	
		    }
		    
		    Totaltraffic=Totaltraffic/1000;//convert to Mbps and assign to nearest 100 gbps
		    int rounded=(int)Totaltraffic;
		    if (rounded==0){
		    	rounded+=1;
		    }
		    //Assign capacity for design,check capacity for test
		    if(mode==1){
		      edgeit.setCapacity(1 * rounded);	
		    }
		    
		    rounded =  (((rounded + 99) / 100 ) * 100);
		    cost+=(rounded/100)*(100+0.1*edgeit.getCost());
		    
		    
		    
		    //System.out.println(key);
		    //System.out.println("Traffic:"+Totaltraffic);
		    //System.out.println("Capacity assigned:"+rounded);
		    	
			}
//		if(mode==1){
//			System.out.println("Overall cost of network is:"+cost);
//		}
		System.out.println("Overall cost of network is:"+cost);
		
		return usedEdges;
		
     	
	}
	
	public static void mincost(ArrayList<Node>srcnodes,ArrayList<Node>dstnodes,HashMap<String, Node> allNodes,Traffic tr1, HashMap<String, Edge> allEdgelinks){
		
		double mincost=Double.POSITIVE_INFINITY;
		Node minstart=null;
		Node minend=null;
		double pathcost=0;
		for (Node srcnode:srcnodes){
			
			
			
			for(Node dstnode:dstnodes){
				//Check if within same location
				
				if(srcnode.getBlocation()==dstnode.getBlocation()){
					cost+=50;
					//System.out.println(srcnode.getName()+" "+dstnode.getName());
					return ;
				}
				
				try{
				 pathcost=Dijkstra.cost.get(allNodes.get(srcnode.getName())).get(allNodes.get(dstnode.getName()));
				} catch(NullPointerException n){
					System.out.println("No paths found error");
					System.out.println(srcnode.getName()+" "+dstnode.getName());
					
				}
				
				
				if (pathcost<mincost){
					mincost=pathcost;
					minstart=srcnode;
					minend=dstnode;
				}
				
				
				
			   
			}
			
		}
		//Nodes in different backbones, add all edges between two nodes to usedEdges
		
		ArrayList<Node>path=new ArrayList<Node>();
		path=Dijkstra.pathInfo.get(allNodes.get(minstart.getName())).get(allNodes.get(minend.getName()));
		for(int i=0;i<path.size()-1;i++){
			if (i==0){
				if (usedEdges.containsKey(minstart.getName()+path.get(i).getName())){
					//Get link and set Traffic in edge
					usedEdges.get(minstart.getName()+path.get(i).getName()).setTraffic(tr1);
				}
				else{
					//Assign traffic to edge and put in used edge
					
					usedEdges.put(minstart.getName()+path.get(i).getName(), allEdgelinks.get(minstart.getName()+path.get(i).getName()).setTraffic(tr1));	
					
				}
			}
			
			else{
				if (usedEdges.containsKey(path.get(i).getName()+path.get(i+1).getName())){
					//Get link and set Traffic in edge
					usedEdges.get(path.get(i).getName()+path.get(i+1).getName()).setTraffic(tr1);
				}
				else{
					//Add edge to used links
					
					usedEdges.put(path.get(i).getName()+path.get(i+1).getName(), allEdgelinks.get(path.get(i).getName()+path.get(i+1).getName()).setTraffic(tr1));
					
					
				}
				
			}
		}
		
		//System.out.println(usedEdges.isEmpty());
		
		return ;
	}

}
