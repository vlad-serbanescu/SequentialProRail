import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class BiddingAgent {

	float belief, risk;
	List<Goal> goals;

	public BiddingAgent(float init_value, Goal init_goal, float risk) {
		super();
		this.belief = init_value;
		this.risk = risk;
		this.init_goal = init_goal;
		goals = new LinkedList();
		goals.add(init_goal);
	}

	Goal init_goal;

	public void announce(Route slot, float price, AuctioneerAgent auctioneerAgent) {
		System.out.println("Received Announce");
		float budget = belief;
		if (goals.size() > 0) {
			Goal goal = goals.get(0);
			float timeFit = strictFit(goal, slot);
			float myOffer = bidStrategy(price, budget, timeFit, risk);
			if (myOffer < 0)
				goals.clear();
			else {
				auctioneerAgent.Bid(this, slot, myOffer);
			}

		}

	}

	private float bidStrategy(float min, float budget, float timeFit, float risk) {

		if(budget<min)
			return 0;
		if(timeFit<=0) return timeFit;
		return min + (budget - min) * timeFit * risk;
	}

	private float strictFit(Goal goal, Route slot) {
		if (slot.timeSlot > goal.intervalEnd)
			return -1;
		else if ((slot.d != null) || ((goal.d != null) && (goal.intervalStart <= slot.timeSlot))
				|| (slot.d.equals(goal.d))) {
			return (slot.timeSlot - goal.intervalStart + 1) / (goal.intervalEnd - slot.timeSlot);
		} else
			return 0;
	}

	public void sold(AuctioneerAgent auctioneerAgent, Route g) {
		System.out.println("I won the auction");
		goals.clear();
	}

}
