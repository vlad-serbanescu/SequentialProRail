
public class Route {
	float timeSlot;

	public Route(float timeSlot, Destination d) {
		super();
		this.timeSlot = timeSlot;
		this.d = d;
	}

	Destination d;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Route [timeSlot=" + timeSlot + ", d=" + d + "]";
	}
	
	
}
