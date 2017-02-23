package design;

public class Screen {

	private double temp;
	
	public Screen(double temp) {
		this.temp = temp;
	}
	
	public void update(double val){
		temp = val;
		System.out.println(temp);
	}

}
