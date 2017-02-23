package design;

public class Button {

	private Panel p; // always have observer reference so that I can keep it be notified
	private double target;
	private String name;
	
	public Button(Panel p, String name, double target) {
		this.p = p;
		this.name = name;
		this.target = target;
		
	}
	
	public void setTarget(double target){
		if(target == this.target) return;
		this.target = target;
		
		// notify Panel with this reference
		p.update(this);
	}
	
	public double getTarget(){
		return target;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}

}
