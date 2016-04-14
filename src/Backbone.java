
public class Backbone {

	private String name;
	private int location;
	
	
	public Backbone(String name){
		this.name=name;
		this.location=Integer.parseInt(name.replaceAll("[\\D]", ""));
	}
	
	public String getname(){
		return this.name;
	}
	public int getlocation(){
		return this.location;

	}
}
