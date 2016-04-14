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
			
			pathInfo.put(start, new HashMap<>());
			cost.put(start, new HashMap<>());
		}
		heap.add(start,0);
		cost.get(start).put(start, (double) 0);
		pathInfo.get(start).put(start, new ArrayList<Node>());
		
		while(!heap.isEmpty()){
			Node node = heap.poll();
		
			for(Edge edge : node.vertice){
				double totalcost = cost.get(start).get(node) + edge.cost;
				ArrayList<Node> path = new ArrayList<>(pathInfo.get(start).get(node));
				path.add(edge.end);
				
				if(!cost.get(start).containsKey(edge.end)){
					heap.add(edge.end, totalcost);
					cost.get(start).put(edge.end, totalcost);
					//pathInfo.get(start).put(edge.end, new ArrayList<Node>(pathInfo.get(start).get(node)));
					pathInfo.get(start).put(edge.end, path);
					
				}else{
					if(totalcost < cost.get(start).get(edge.end)){
						heap.updatePriority(edge.end, totalcost);
						cost.get(start).put(edge.end, totalcost);
						pathInfo.get(start).put(edge.end, path);
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