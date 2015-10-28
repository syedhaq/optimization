import java.util.Comparator;

//This class creates an edge from optical node
public class Edge implements Comparator  {
	String fNode,tNode;
	int fNodeint, tNodeint;
	double cost;
	//Constructor takes in an optical Link
	public Edge(){
		
	};
	

	public Edge(Optical_backbone_links link){
		fNode=link.getnode1();
		tNode=link.getnode2();
		cost=link.getdistance();
	}
	
	public int getfnode(){
		String fnodestr=fNode.replaceAll("[^0-9]", "");
		return(Integer.parseInt(fnodestr));
		
	}
	
	public int gettnode(){
		String tnodestr=tNode.replaceAll("[^0-9]", "");
		return(Integer.parseInt(tnodestr));
	}
	
	public double getcost(){
		return cost;
	}
	
    public int compare(Object o1, Object o2) {
        // Used for comparisons during add/remove operations
        double cost1 = ((Edge) o1).getcost();
        double cost2 = ((Edge) o2).getcost();
        int from1 = ((Edge) o1).getfnode();
        int from2 = ((Edge) o2).getfnode();
        int to1   = ((Edge) o1).gettnode();
        int to2   = ((Edge) o2).gettnode();

        if (cost1<cost2)
          return(-1);
        else if (cost1==cost2 && from1==from2 && to1==to2)
          return(0);
        else if (cost1==cost2)
          return(-1);
        else if (cost1>cost2)
          return(1); 
        else
          return(0);
      }
    public boolean equals(Object obj) {
        // Used for comparisions during add/remove operations
        Edge e = (Edge) obj;
        return (cost==e.cost && fNode==e.fNode && tNode==e.tNode);
      }
}
