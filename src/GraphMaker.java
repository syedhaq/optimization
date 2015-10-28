import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.Vector;

//Creates Graph with edges and all nodes in graph
//Takes an opticalbackbone link, creates an edge and adds to treeset
//Checks if the nodes in the backbone exist in Hashset and adds the missing ones

public class GraphMaker {
	final int Maxnodes = 41;
	private HashSet nodes[];
	private TreeSet allEdges;
	private Vector minimumEdges;
	private int fnode;
	private int tnode;

	// instantiate GraphMaker
	public GraphMaker() {
		nodes = new HashSet[Maxnodes];
		allEdges = new TreeSet(new Edge());
		minimumEdges = new Vector(Maxnodes);
	}

	// start adding elements

	public void addElements(Optical_backbone_links link) {
		Edge e1 = new Edge(link);
		// Add edge to TreeSet
		allEdges.add(e1);

		fnode = e1.getfnode();
		tnode = e1.gettnode();

		// Check if hashset has node present, if not add
		 if (nodes[fnode] == null) {
		nodes[fnode] = new HashSet<Integer>(2 * Maxnodes);

		nodes[fnode].add(new Integer(fnode));

		 }
		 if (nodes[tnode] == null) {
		nodes[tnode] = new HashSet<Integer>(2 * Maxnodes);
		nodes[tnode].add(new Integer(tnode));

		 }

	}

	// This method takes all edges and performs Kruskal on it
	public void Kruskalimplement() {
	    int size = allEdges.size();
	    //System.out.println(size);
	    for (int i=0; i<size; i++) {
	      Edge curEdge = (Edge) allEdges.first();
	      
	      if (allEdges.remove(curEdge)) {
	        // successful removal from priority queue: allEdges

	        if (nodesnotEqual(curEdge.getfnode(), curEdge.gettnode())) {
	          // System.out.println("Nodes are in different sets ...");
	          HashSet src, dst;
	          int dstHashSetIndex;

	          if (nodes[curEdge.getfnode()].size() > nodes[curEdge.gettnode()].size()) {
	            // have to transfer all nodes including curEdge.to
	            src = nodes[curEdge.gettnode()];
	            dst = nodes[dstHashSetIndex = curEdge.getfnode()];
	          } else {
	            // have to transfer all nodes including curEdge.from
	            src = nodes[curEdge.getfnode()];
	            dst = nodes[dstHashSetIndex = curEdge.gettnode()];
	          }

	          Object srcArray[] = src.toArray();
	          int transferSize = srcArray.length;
	          for (int j=0; j<transferSize; j++) {
	            // move each node from set: src into set: dst
	            // and update appropriate index in array: nodes
	            if (src.remove(srcArray[j])) {
	              dst.add(srcArray[j]);
	              nodes[((Integer) srcArray[j]).intValue()] = nodes[dstHashSetIndex];
	            } else {
	              // This is a serious problem
	              System.out.println("Something wrong: set union");
	              System.exit(1);
	            }
	          }

	          minimumEdges.add(curEdge);
	          // add new edge to MST edge vector
	        } else {
	          // System.out.println("Nodes are in the same set ... nothing to do here");
	        }

	      } else {
	        // This is a serious problem
	        System.out.println("TreeSet should have contained this element!!");
	        System.exit(1);
	      }
	    }
	  }

	private boolean nodesnotEqual(int getfnode, int gettnode) {
		// Check if both nodes are in the same set and add
		// If in the same set, nodes[fnode] and nodes[tnode] will point to same
		// array
		return (!nodes[getfnode].equals(nodes[gettnode]));
	}
	

	public void printElements() {
		Iterator<Edge> iterator = minimumEdges.iterator();
		double totalcost=0;
		System.out.println(minimumEdges.size());
		while (iterator.hasNext()) {
			Edge edge1 = iterator.next();
			System.out.println(edge1.getfnode() + "," + edge1.gettnode() + ","
					+ edge1.cost);
			 totalcost=totalcost+edge1.cost;
			
		}
		System.out.println(totalcost);

	}

}