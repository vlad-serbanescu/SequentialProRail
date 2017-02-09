
public class Destination {
	String name;
	float drivingTime, costToDriveToDest;

	public Destination(String name, float drivingTime, float costToDriveToDest) {
		super();
		this.name = name;
		this.drivingTime = drivingTime;
		this.costToDriveToDest = costToDriveToDest;
	}

	@Override
	public String toString() {
		return this.name;

	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Destination) {
			Destination d = (Destination) obj;
			return d.name.equals(this.name);

		}
		return super.equals(obj);
	}
}
