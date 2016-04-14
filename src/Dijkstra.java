import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Dijkstra {

	static HashMap<Node, HashMap<Node,ArrayList<Node>>> pathInfo = new HashMap<>();
	static HashMap<Node, HashMap<Node, Double>> cost = new HashMap<>();
	
	public static void dijkstra(Node start){
		Heap<Node> heap = new Heap<Node>();
		
		if(!pathInfo.containsKey(start)){
			
			pathInfo.put(start, new HashMap<Node,ArrayList<Node>>());
			cost.put(start, new HashMap<Node,Double>());
		}
		heap.add(start,0);
		cost.get(start).put(start, (double) 0);
		pathInfo.get(start).put(start, new ArrayList<Node>());
		
		while(!heap.isEmpty()){
			Node node = heap.poll();
		
			for(Edge edge : node.getNeighbors()){
				double totalcost = cost.get(start).get(node) + edge.getCost();
				ArrayList<Node> path = new ArrayList<>(pathInfo.get(start).get(node));
				path.add(edge.getEnd());
				
				if(!cost.get(start).containsKey(edge.getEnd())){
					heap.add(edge.getEnd(), totalcost);
					cost.get(start).put(edge.getEnd(), totalcost);
					//pathInfo.get(start).put(edge.end, new ArrayList<Node>(pathInfo.get(start).get(node)));
					pathInfo.get(start).put(edge.getEnd(), path);
					
				}else{
					if(totalcost < cost.get(start).get(edge.getEnd())){
						heap.updatePriority(edge.getEnd(), totalcost);
						cost.get(start).put(edge.getEnd(), totalcost);
						pathInfo.get(start).put(edge.getEnd(), path);
					}
				}	
			}
		}
	}
	
	public static void ospf(ArrayList<Node> nodelist){
		for(Node n : nodelist){
			dijkstra(n);
		}
	}
}