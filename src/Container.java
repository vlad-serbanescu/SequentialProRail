
public class Container {

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Container [d=" + d + ", paidPrice=" + paidPrice + "]";
	}

	Destination d;
	float arrivalTimeInRtm, risk, latestArrivalTimeatD, maxBudget, paidPrice;

	public Container(Destination d, float arrivalTimeInRtm, float risk, float latestArrivalTimeatD, float maxBudget) {
		super();
		this.d = d;
		this.arrivalTimeInRtm = arrivalTimeInRtm;
		this.risk = risk;
		this.latestArrivalTimeatD = latestArrivalTimeatD;
		this.maxBudget = maxBudget;
	}

	public float getDeadline() {

		return latestArrivalTimeatD - d.drivingTime;
	}
}
