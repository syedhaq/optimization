
public class Edgerouter {
	private String name;
	
	public Edgerouter(String name){
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}
	
	public boolean equals(Object obj){
		Edgerouter e=(Edgerouter)obj;
		return this.name.equalsIgnoreCase(e.name);
		
	}
	
}
