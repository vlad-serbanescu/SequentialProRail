import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Main {

	static int TRAINS = 2, CONTAINERS= 100;
	
	public static void main(String[] args) {
		Destination duisburg = new Destination("Duisburg", 4.0f, 4000.0f),
				munich = new Destination("Munich", 16.0f, 16000.0f);
		List<Container> containers = new LinkedList<>();
		List<Train> trains = new LinkedList<>(); 
		
		
		Random r = new Random(System.currentTimeMillis());
		for (int i = 0; i < TRAINS; i++) {
			int startTime= i+ r.nextInt(3);
			float riskFactor = r.nextFloat(), budget = 100000 * r.nextFloat() + 1000; 
			int endTime = startTime+4;
			Train tr = new Train(30, budget, riskFactor, startTime, endTime);
			trains.add(tr);
		}
		for (int i = 0; i < CONTAINERS; i++) {
			Destination d = (r.nextInt(10)<=6)?  duisburg:munich;
			int arrival = (r.nextInt(10)<=6)? 9:11;
			float risk = r.nextFloat(), budget = 1000 * r.nextFloat() + 100; 
			if(d==munich)
				budget*=4;
			float latest = arrival+4+d.drivingTime;
			Container cont = new Container(d, arrival, risk, latest, budget);
			containers.add(cont);
			
		}		
	}
}
