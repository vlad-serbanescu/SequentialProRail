import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class AuctioneerAgent {
	Map<Route, Float> beliefs;
	List<Route> goals;
	List<BiddingAgent> biddersList;
	int numWinners;
	AuctionOraganizerAgent organizer;

	Map<Route, Entry<BiddingAgent, Float>> winners = new HashMap<>();

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
			System.out.println("Received a bid");
			if(winners.containsKey(slot)){
				Entry<BiddingAgent, Float> e = winners.get(slot);
				if(price> e.getValue())
					winners.put(slot, new AbstractMap.SimpleEntry<BiddingAgent,Float>(ba, price));
			}
			else{
				if(beliefs.containsKey(slot)){
					Float min = beliefs.get(slot);
					if(price>min) winners.put(slot, new AbstractMap.SimpleEntry<BiddingAgent,Float>(ba, price));
				}
				else{
					winners.put(slot, new AbstractMap.SimpleEntry<BiddingAgent,Float>(ba, price));
				}
					
			}
	}

	public void goalRule(Route g) {
		System.out.println("Starting an Auction");
		float price=0.0f;
		if(beliefs.containsKey(g)){
			price = beliefs.get(g);
		}
		System.out.println("Inform all bidders");
		for (BiddingAgent biddingAgent : biddersList) {
			biddingAgent.announce(g, price, this);
		}
		
		System.out.println("All informed");
		if(winners.containsKey(g)){
			Entry<BiddingAgent, Float> e = winners.get(g);
			if(e.getKey()==null){
				System.out.println("No winner");
			}
			else{
				System.out.println("Sold for the price of "+ e.getValue());
				e.getKey().sold(this,g);
			}
		}
	}
	
	public void run(){
		System.out.println("Auctioneer Started");
		while(goals.size()>0){
			goalRule(goals.remove(0));
		}
	}

}
