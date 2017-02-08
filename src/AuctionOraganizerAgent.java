import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AuctionOraganizerAgent {
	int idCounter;
	LinkedList<BiddingAgent> trainAgents, containerAgents;
	int round;
	float timeSlot;
	Train winnerTrain;
	List<Container> winnerContainers;

	public AuctionOraganizerAgent(LinkedList<Train> trains, LinkedList<Container> containers) {
		idCounter = 0;
		round = 0;
		trainAgents = new LinkedList<>();
		containerAgents = new LinkedList<>();
		timeSlot = 0.0f;
		for (Train train : trains) {
			BiddingAgent ba = new BiddingAgent(train.budget, new Goal(train.intervalStart, train.intervalEnd, null),
					train.risk);

			trainAgents.add(ba);
		}

		for (Container container : containers) {
			BiddingAgent ba = new BiddingAgent(container.maxBudget,
					new Goal(container.arrivalTimeInRtm, container.getDeadline(), container.d), container.risk);
			containerAgents.add(ba);
		}

	}

	public void start(float timeSlot) {
		round = 1;
		this.timeSlot = timeSlot;
		Route freeRoute = new Route(timeSlot, null);
		List<Route> goals = new LinkedList<>();
		goals.add(freeRoute);
		Map<Route, Float> beliefBase = new HashMap<>();
		new AuctioneerAgent(beliefBase, goals, this.trainAgents, 1, this);

	}

	public void round1(List<BiddingAgent> winners, List<Float> prices, List<Train> unhappy, Train winner) {
		if (winners.size() == 0) {
			System.out.println("No intersted trains");

		} else {
			System.out.println("Winner is a train paying " + prices.get(0).floatValue());
			round = 2;
			Route timeSlotRoute = new Route(timeSlot, null);
			List<Route> goals = new LinkedList<>();
			goals.add(timeSlotRoute);
			Map<Route, Float> beliefBase = new HashMap<>();
			new AuctioneerAgent(beliefBase, goals, containerAgents, winner.noContainers, this);
		}
	}

	public void round2(List<Float> prices, List<Container> unhappyC, List<Container> winnerC) {
		round = 0;
		Destination wd = null;
		winnerContainers = winnerC;
		for (Container container : winnerContainers) {
			container.paidPrice = prices.get(winnerContainers.indexOf(container));
		}
		System.out.println("Getting the destiantion");
		if (winnerContainers.size() > 0)
			wd = winnerContainers.get(0).d;
		System.out.println(prices);
		System.out.println(wd);
	}

	public void result(List<BiddingAgent> winners, List<Float> prices, List<Train> unhappy, Train winner,
			List<Container> unhappyC, List<Container> winnerC) {
		System.out.println("Results received for round = " + round);
		if (round == 1)
			round1(winners, prices, unhappy, winner);
		else if (round == 2)
			round2(prices, unhappyC, winnerC);

	}
}
