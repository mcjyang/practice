package design;
import java.util.*;

public class Panel {
	private Thermometer t; // Observer reference
	private ArrayList<Button> buttons;// wrapper for Buttons
	
	public Panel(Thermometer t) {
		this.t = t;
		buttons = new ArrayList<>(); 
		buttons.add(new Button(this, "HEAT/AC", 1));
		buttons.add(new Button(this, "ADJUST", t.getStatus()+1));
	}
	
	public void update(Button b){
		System.out.println("In Panel update.");
		t.execute("Heat", b.getTarget());
		return;
	}
	
	public List<Button> getButtons(){
		return buttons;
	}

}
