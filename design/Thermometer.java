package design;

public class Thermometer {
	
	private Screen s;
	private Panel p;
	private Moniter m;
	private Heater h;
	private Cooler c;
	private double status;

	public Thermometer() {
		m = new Moniter();
		status = m.measure();
		p = new Panel(this);
		s = new Screen(status);
		h = new Heater();
		c = new Cooler();
	}
	
	public void execute(String func, double target){
		System.out.println("In Thermometer SetTarget");
		if(func.equals("Heat") && target > status) h.launch();
		//...
		//...
	}
	
	public void detectStatus(){
		while(true){
			double cur = m.measure();
			//check moniter's temp equal to pre temp
			if(cur != status){
				System.out.println("Status changed");
				status = cur;
				sendStatusToScreen(cur);
			}
			
			// assume 1 is heater 0 is coolear
			// stop heater if following condition is true
			if((p.getButtons().get(0).getTarget() == 1) && p.getButtons().get(1).getTarget() <= cur){
				h.stop();
				break;
			}
			// ...
			// ...
		}
	}
	
	public void sendStatusToScreen(double val){
		s.update(val);
	}
	
	public double getStatus(){
		return status;
	}
	
	public Panel getPanel(){
		return p;
	}
	
	public Moniter getMoniter(){
		return m;
	}
	
	public static void main(String[] args){
		System.out.println("test start");
		final Thermometer t = new Thermometer();
		final Person me = new Person(t);
		
		// Thread 1: keep measure status
		Thread t1 = new Thread(){
			
			@Override
			public void run(){
				t.detectStatus();
			}
		};
		
		// Thread 2: interact with Thermometer
		Thread t2 = new Thread(){
			
			@Override
			public void run(){
				me.interact().getPanel().getButtons().get(1).setTarget(30);
				int cur = 20;
				while(cur<30){
					try{
						Thread.sleep(1000);
					} catch (Exception e){
						System.out.println("exception here.");
					}
					me.interact().getMoniter().GodMode(++cur);
				}
			}
		};
		
		t1.start();
		t2.start();
	}

}
