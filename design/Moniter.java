package design;

public class Moniter {

	private double temp;
	
	public Moniter() {
		temp = 20;
	}
	
	public double measure(){
		return temp;
	}
	
	public void GodMode(double temp){
		this.temp = temp;
	}

}
