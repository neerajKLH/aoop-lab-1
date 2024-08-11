import java.util.ArrayList;
import java.util.List;

// Observer interface for receiving notifications
interface Observer {
    void update(String message);
}

// Subject interface for managing observers and notifying them
interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(String message);
}

// Concrete implementation of the Subject interface
class Auction implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private String auctionStatus;

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    // Methods to simulate auction events
    public void itemAvailable() {
        auctionStatus = "Item is now available for bidding!";
        notifyObservers(auctionStatus);
    }

    public void biddingStart() {
        auctionStatus = "Bidding has started!";
        notifyObservers(auctionStatus);
    }

    public void biddingEnd() {
        auctionStatus = "Bidding has ended.";
        notifyObservers(auctionStatus);
    }
}

// Concrete implementation of Observer interface for Bidders
class Bidder implements Observer {
    private String name;

    public Bidder(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " received notification: " + message);
    }
}

// Client class to demonstrate the auction system
public class OnlineAuctionSystem {
    public static void main(String[] args) {
        // Create an Auction
        Auction auction = new Auction();

        // Create Bidders
        Bidder alice = new Bidder("Alice");
        Bidder bob = new Bidder("Bob");

        // Bidders subscribe to auction notifications
        auction.addObserver(alice);
        auction.addObserver(bob);

        // Simulate auction events
        auction.itemAvailable();
        auction.biddingStart();
        auction.biddingEnd();

        // Bidders unsubscribe from auction notifications
        auction.removeObserver(bob);

        // Simulate another auction event
        auction.itemAvailable();
    }
}