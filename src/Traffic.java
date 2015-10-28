import java.util.ArrayList;


public class Traffic {
private double hour,tunit,p1,p2;
private String source,destination;

public Traffic(ArrayList<String>trafficlist){
setHour(Double.parseDouble(trafficlist.get(0)));
setTunit(Double.parseDouble(trafficlist.get(1)));
setSource(trafficlist.get(2));
setDestination(trafficlist.get(3));
setP1(Double.parseDouble(trafficlist.get(4)));
setP2(Double.parseDouble(trafficlist.get(5)));
}

public double getHour() {
	return hour;
}

public void setHour(double hour) {
	this.hour = hour;
}

public double getTunit() {
	return tunit;
}

public void setTunit(double tunit) {
	this.tunit = tunit;
}

public double getP1() {
	return p1;
}

public void setP1(double p1) {
	this.p1 = p1;
}

public double getP2() {
	return p2;
}

public void setP2(double p2) {
	this.p2 = p2;
}

public String getSource() {
	return source;
}

public void setSource(String source) {
	this.source = source;
}

public String getDestination() {
	return destination;
}

public void setDestination(String destination) {
	this.destination = destination;
}




}
