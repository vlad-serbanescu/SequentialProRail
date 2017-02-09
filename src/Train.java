
public class Train {
	int noContainers;
	float budget, risk, intervalStart, intervalEnd, paidPrice;

	public Train(int noContainers, float budget, float risk, float intervalStart, float intervalEnd) {
		super();
		this.noContainers = noContainers;
		this.budget = budget;
		this.risk = risk;
		this.intervalStart = intervalStart;
		this.intervalEnd = intervalEnd;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Train [noContainers=" + noContainers + ", intervalStart=" + intervalStart + ", intervalEnd="
				+ intervalEnd + ", paidPrice=" + paidPrice + "]";
	}
	
	
}
