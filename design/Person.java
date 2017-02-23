package design;

public class Person {

	private Thermometer t;
	public Person(Thermometer t) {
		this.t= t;
	}
	
	public Thermometer interact(){
		return t;
	}

}
