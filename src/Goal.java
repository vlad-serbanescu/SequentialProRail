
public class Goal {

	float intervalStart;
	float intervalEnd;
	Destination d;

	public Goal(float intervalStart, float intervalEnd, Destination d) {
		super();
		this.intervalStart = intervalStart;
		this.intervalEnd = intervalEnd;
		this.d = d;
	}
	
	@Override
	public String toString() {
		return("Goal from "+ intervalStart+ " to "+ intervalEnd+ " goes to "+ d);
	}

	

}
