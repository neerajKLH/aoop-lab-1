// Step 1: Define the Strategy Interface
interface InvestmentStrategy {
    void invest(double amount);
}

// Step 2: Implement Different Investment Strategies

// Conservative Strategy
class ConservativeStrategy implements InvestmentStrategy {
    @Override
    public void invest(double amount) {
        System.out.println("Investing " + amount + " in conservative instruments like bonds and fixed deposits.");
    }
}

// Balanced Strategy
class BalancedStrategy implements InvestmentStrategy {
    @Override
    public void invest(double amount) {
        System.out.println("Investing " + amount + " in a balanced mix of stocks and bonds.");
    }
}

// Aggressive Strategy
class AggressiveStrategy implements InvestmentStrategy {
    @Override
    public void invest(double amount) {
        System.out.println("Investing " + amount + " in aggressive instruments like stocks and high-risk assets.");
    }
}

// Step 3: Create a Context Class
class Portfolio {
    private InvestmentStrategy investmentStrategy;

    // Set the strategy dynamically
    public void setInvestmentStrategy(InvestmentStrategy investmentStrategy) {
        this.investmentStrategy = investmentStrategy;
    }

    public void invest(double amount) {
        if (investmentStrategy == null) {
            throw new IllegalStateException("Investment strategy not set");
        }
        investmentStrategy.invest(amount);
    }
}

// Step 4: Use the Strategy Pattern in the Main Class
public class Main {
    public static void main(String[] args) {
        Portfolio portfolio = new Portfolio();

        // Using Conservative Strategy
        portfolio.setInvestmentStrategy(new ConservativeStrategy());
        portfolio.invest(10000);

        // Using Balanced Strategy
        portfolio.setInvestmentStrategy(new BalancedStrategy());
        portfolio.invest(10000);

        // Using Aggressive Strategy
        portfolio.setInvestmentStrategy(new AggressiveStrategy());
        portfolio.invest(10000);
    }
}
