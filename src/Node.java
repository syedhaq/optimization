import java.util.ArrayList;

public class Node {
  
  private String name;
  private int blocation;
  private ArrayList<Edge>neighbors=new ArrayList<Edge>();
  
  
  
  public Node(String name) {
  
    this.name = name;
  }


  public String getName() {
    return name;
  }
  
  
  

  public boolean equals(Object obj) {
	  Node n=(Node)obj;
	  return this.name.equalsIgnoreCase(n.name);
	  								}


public int getBlocation() {
	return blocation;
}


public void setBlocation(int blocation) {
	this.blocation = blocation;
}


public ArrayList<Edge> getNeighbors() {
	return neighbors;
}


public void addNeighbors(Edge neighbor) {
	neighbors.add(neighbor);
}




  

 
 
  
} 