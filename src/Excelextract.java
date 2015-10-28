import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excelextract {
	public static void main(String[] args) {
		//Make new Graphmaker
		GraphMaker graph=new GraphMaker();
		try {
			FileInputStream file = new FileInputStream(new File(
					"Challenge Data Set V4.xlsx"));

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			//Optical link
			XSSFSheet sheet = workbook.getSheetAt(5);
			//Edgetobackbone link
			XSSFSheet eb=workbook.getSheetAt(7);
			
			//Examplelinks
			XSSFSheet exambb=workbook.getSheetAt(8);
			
			//Traffic
			XSSFSheet traffic=workbook.getSheetAt(6);

			// Iterate through each rows one by one
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
				
				
				
				//System.out.println("");
				Optical_backbone_links link1=new Optical_backbone_links(rowcell);
				graph.addElements(link1);
				
			}
			//Add edgetobackbone
			Iterator<Row> ebIterator=eb.iterator();
			
			while (ebIterator.hasNext()) {
				Row row = ebIterator.next();
				// For each row, iterate through all the columns
				Iterator<Cell> cellIterator = row.cellIterator();
				//Create String Array for each row
				ArrayList<String> rowcell = new ArrayList<String>();
				ArrayList<edgetoBB>edgetoback=new ArrayList<edgetoBB>();
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
			
			//add exampleBB
			Iterator<Row>example=exambb.iterator();
			ArrayList<ExampleLinks>links=new ArrayList<ExampleLinks>();
			while (example.hasNext()) {
				Row row = example.next();
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
				ExampleLinks link1=new ExampleLinks(rowcell);
				links.add(link1);
				//System.out.println(link1.getLinkname());
				
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
				System.out.println(tr1.getSource());
				//Only get 0 traffic for now
				if(tr1.getHour()!=0){
					break;
				}
				
			
				
				
			}
			
			//graph.Kruskalimplement();
			//graph.printElements();
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
