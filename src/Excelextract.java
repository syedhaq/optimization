import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excelextract {
	//Global variables

	
	public static void main(String[] args) {
		ArrayList<Edgerouter>allEdges=new ArrayList<Edgerouter>();
		ArrayList<Optical_backbone_links>allLinks=new ArrayList<Optical_backbone_links>();
		ArrayList<edgetoBB>edgetoback=new ArrayList<edgetoBB>();
		ArrayList<Traffic>allTraffic=new ArrayList<Traffic>();
		//ArrayList<Node>allNodes=new ArrayList<Node>();
		HashMap<String, Node> allNodes = new HashMap<>();
		HashMap<String,Edge> allEdgelinks=new HashMap<>();
		//Make new Graphmaker
		//GraphMaker graph=new GraphMaker();
		try {
			FileInputStream file = new FileInputStream(new File(
					"Challenge Data Set V4.xlsx"));

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			//Get all edge routers
			XSSFSheet edgesheet=workbook.getSheetAt(3);
			//Optical link
			XSSFSheet sheet = workbook.getSheetAt(5);
			//Edgetobackbone link
			XSSFSheet eb=workbook.getSheetAt(7);
			
			//Examplelinks
			XSSFSheet exambb=workbook.getSheetAt(8);
			
			//Traffic
			XSSFSheet traffic=workbook.getSheetAt(6);
			
			//Nodes
			XSSFSheet nodesheet=workbook.getSheetAt(4);
					
			//Add edges
					Iterator<Row> edgeIterator = edgesheet.iterator();
					
					while (edgeIterator.hasNext()) {
						Row row = edgeIterator.next();
						// For each row, iterate through all the columns
						Iterator<Cell> cellIterator = row.cellIterator();
						
						
						//Just one cell to copy in this ocassion
							Cell cell = cellIterator.next();

						Edgerouter link1=new Edgerouter(cell.toString());
						allEdges.add(link1);
						//graph.addElements(link1);
						
					}
			


			//Add edgetobackbone
			Iterator<Row> ebIterator=eb.iterator();
			
			while (ebIterator.hasNext()) {
				Row row = ebIterator.next();
				// For each row, iterate through all the columns
				Iterator<Cell> cellIterator = row.cellIterator();
				//Create String Array for each row
				ArrayList<String> rowcell = new ArrayList<String>();
				
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					rowcell.add(cell.toString());
					//System.out.println(cell.toString());
					
					
				}
				
				
				
				//System.out.println("");
				edgetoBB edgeblink=new edgetoBB(rowcell);
				//Store edgetobb
				edgetoback.add(edgeblink);
				//System.out.println(edgeblink.getLinkname()+" "+edgeblink.end1+ " "+edgeblink.getdistance());
				
				
				
			}
			
		
			
			//Add traffic
			Iterator<Row>trit=traffic.iterator();
			
			while (trit.hasNext()) {
				Row row = trit.next();
				// For each row, iterate through all the columns
				Iterator<Cell> cellIterator = row.cellIterator();
				//Create String Array for each row
				ArrayList<String> rowcell = new ArrayList<String>();
				
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					rowcell.add(cell.toString());
					//System.out.println(cell.toString());
					
					
				}
				
				
				
				//System.out.println("");
				Traffic tr1=new Traffic(rowcell);
				
				//System.out.println(tr1.getSource());
				//Only get 0 traffic for now
				if(tr1.getHour()!=0){
					break;
				}
				allTraffic.add(tr1);
				
			
				
				
			}
			
			Iterator<Row> nodeIterator = nodesheet.iterator();
			
			while (nodeIterator.hasNext()) {
				Row row = nodeIterator.next();
				// For each row, iterate through all the columns
				Iterator<Cell> cellIterator = row.cellIterator();
				//Create String Array for each row
				ArrayList<String> rowcell = new ArrayList<String>();
				
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					rowcell.add(cell.toString());
					//System.out.println(cell.toString());
					
					
				}
				
				
				
				//System.out.println("");
				Node node1=new Node(rowcell.get(0));
				//set backbone location if it is not none
				if(!rowcell.get(1).equalsIgnoreCase("NONE")){
					node1.setBlocation(Integer.parseInt(rowcell.get(1).replaceAll("[\\D]", "")));
				}
				//Add node to HashMap
				allNodes.put(rowcell.get(0), node1);
				
			}
			
			// add links to node
			Iterator<Row> rowIterator = sheet.iterator();
			
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				// For each row, iterate through all the columns
				Iterator<Cell> cellIterator = row.cellIterator();
				//Create String Array for each row
				ArrayList<String> rowcell = new ArrayList<String>();
				
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					rowcell.add(cell.toString());
					//System.out.println(cell.toString());
					
					
				}
				
				
				//Retrieve node from hashmap
				
				Node nodetoadd=allNodes.get(rowcell.get(1));
				Node neighbor=allNodes.get(rowcell.get(2));
				Edge forward=new Edge(nodetoadd,neighbor,Double.parseDouble(rowcell.get(3)),Double.parseDouble(rowcell.get(4)));
				allEdgelinks.put(nodetoadd.getName()+neighbor.getName(), forward);
				Edge backward=new Edge(neighbor,nodetoadd,Double.parseDouble(rowcell.get(3)),Double.parseDouble(rowcell.get(4)));
				allEdgelinks.put(neighbor.getName()+nodetoadd.getName(), backward);
				nodetoadd.addNeighbors(forward);
				neighbor.addNeighbors(backward);
				
				
				
				
				//System.out.println("");
				//graph.addElements(link1);
				
			}
		
			
				
			//graph.Kruskalimplement();
			//graph.printElements();
			file.close();
			
			//Assign traffic to links
			Dijkstra.ospf(new ArrayList<Node>(allNodes.values()));
			
			//Test
			HashMap<String, Edge> design=sumlinks.addtraffic(allTraffic,edgetoback,allNodes,allEdgelinks,1);
			ArrayList<Node>path=new ArrayList<Node>();
			double failurecount=0;
			 //path=Dijkstra.pathInfo.get(allNodes.get("O6")).get(allNodes.get("O11"));
			
			for (Edge etest:design.values()){
				HashMap<String, Edge> test=(HashMap)design.clone();
				test.remove(etest.getEnd().getName()+etest.getStartnode().getName());
				test.remove(etest.getStartnode().getName()+etest.getEnd().getName());
				HashMap<String,Node>newnodes=(HashMap)allNodes.clone();
				//Remove links
				newnodes.get(etest.getStartnode().getName()).removeNeighbors(etest);
				Dijkstra.ospf(new ArrayList<Node>(newnodes.values()));
				HashMap<String,Edge>newDesign=sumlinks.addtraffic(allTraffic,edgetoback,newnodes,test,0);
				
				for(Edge newedge:newDesign.values()){
					if(newedge.getTrafficp1()+newedge.getTrafficp2()>design.get(newedge.getStartnode().getName()+newedge.getEnd().getName()).getCapacity()){
						System.out.println("Failure at removing link");
						failurecount++;
						break;
					}
					
					
				}
				
				
			}
			System.out.println("Total failures:"+failurecount);
			
			
			
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
