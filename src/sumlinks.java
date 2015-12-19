import java.util.ArrayList;

//This class is the meat that finds sum of all links
//Takes a traffic object and assigns its traffic to capacity
public class sumlinks {
	static int k = 0;
	public static void addtraffic(ArrayList<Traffic>allTraffic,ArrayList<ExampleLinks>links,ArrayList<edgetoBB>edgetoback,ArrayList<Optical_backbone_links>allLinks){

		for(Traffic tr1:allTraffic){
			//Find BackBone Location of Source and destination
			ArrayList<String>srcBB=new ArrayList<String>();
			ArrayList<String>dstBB=new ArrayList<String>();
			String sourcename=tr1.getSource();
			String dstname=tr1.getDestination();
			//Find connecting backbone routers
			for(edgetoBB eb1:edgetoback){
				
				if(eb1.getnode1().equalsIgnoreCase(sourcename)){
					srcBB.add(eb1.getnode2());
					
				}
				if(eb1.getnode1().equalsIgnoreCase(dstname)){
					dstBB.add(eb1.getnode2());
					
				}
				
			}
			
			//Find route of traffic from example links and optical links
			//Assign traffic to optical links
			
			for(ExampleLinks link:links){
				
				for(String src:srcBB){
					
					for(String dst:dstBB){
						
						if(link.getnode1().equalsIgnoreCase(src)&&link.getnode2().equalsIgnoreCase(dst)){
							
							//Link found
					
						
							
						}
					}
				}
			
			
			
			
			
		}
	}
		
		System.out.println(k);
	
	}
	

}
