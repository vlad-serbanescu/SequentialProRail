import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class AuctioneerAgent {
	Map<Route, Float> beliefs;
	List<Route> goals;
	List<BiddingAgent> biddersList;
	int numWinners;
	AuctionOraganizerAgent organizer;

	Map<Route, TreeMap<Float, BiddingAgent>> winners = new HashMap<>();

	public AuctioneerAgent(Map<Route, Float> beliefBase, List<Route> initGoals, List<BiddingAgent> biddersList,
			int winners, AuctionOraganizerAgent organizer) {
		super();
		this.beliefs = beliefBase;
		this.goals = initGoals;
		this.biddersList = biddersList;
		this.numWinners = winners;
		this.organizer = organizer;
	}

	public void Bid(BiddingAgent ba, Route slot, Float price) {
		//System.out.println("Received a bid");
		if (beliefs.containsKey(slot))
			if (beliefs.get(slot) < price) {
				if (winners.containsKey(slot)) {
					TreeMap<Float, BiddingAgent> e = winners.get(slot);
					if (e.size() >= numWinners) {
						if (price > e.firstKey()) {
							e.remove(e.firstKey());
							e.put(price, ba);
						}
					} else
						e.put(price, ba);
				} else {
					TreeMap<Float, BiddingAgent> tm = new TreeMap<>();
					tm.put(price, ba);
					winners.put(slot, tm);
				}
			}
	}

	public void goalRule(Route g) {
		System.out.println("Starting an Auction");
		float price = 0.0f;
		if (beliefs.containsKey(g)) {
			price = beliefs.get(g);
		}
		System.out.println("Inform all bidders");
		System.out.println(beliefs);
		System.out.println(g);
		for (BiddingAgent biddingAgent : biddersList) {
			biddingAgent.announce(g, price, this);
		}

		System.out.println("All informed");
		System.out.println(winners);
		if (winners.containsKey(g)) {
			TreeMap<Float, BiddingAgent> e = winners.get(g);
			System.out.println("Sold for the price(s) of " + e.keySet());
			for (BiddingAgent biddingAgent : e.values()) {
				biddingAgent.sold(this, g);
			}
			Set<BiddingAgent> uh = new HashSet<>(biddersList);
			uh.removeAll(e.values());
			List<BiddingAgent> unhappy = new LinkedList<>(uh);
			organizer.result(new LinkedList<>(e.values()), new LinkedList<>(e.keySet()), unhappy);
		}

	}

	public void run() {
		System.out.println("Auctioneer Started");
		while (goals.size() > 0) {
			goalRule(goals.remove(0));
		}
	}

}
