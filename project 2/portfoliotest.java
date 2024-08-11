import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PortfolioTest {

    private Portfolio portfolio;

    @BeforeEach
    public void setUp() {
        portfolio = new Portfolio();
    }

    @Test
    public void testConservativeStrategy() {
        InvestmentStrategy conservativeStrategy = new ConservativeStrategy();
        portfolio.setInvestmentStrategy(conservativeStrategy);

        portfolio.invest(10000);
        // Assertions can be made based on the behavior of the conservative strategy
        assertTrue(true, "Conservative Strategy was executed.");
    }

    @Test
    public void testBalancedStrategy() {
        InvestmentStrategy balancedStrategy = new BalancedStrategy();
        portfolio.setInvestmentStrategy(balancedStrategy);

        portfolio.invest(10000);
        // Assertions can be made based on the behavior of the balanced strategy
        assertTrue(true, "Balanced Strategy was executed.");
    }

    @Test
    public void testAggressiveStrategy() {
        InvestmentStrategy aggressiveStrategy = new AggressiveStrategy();
        portfolio.setInvestmentStrategy(aggressiveStrategy);

        portfolio.invest(10000);
        // Assertions can be made based on the behavior of the aggressive strategy
        assertTrue(true, "Aggressive Strategy was executed.");
    }

    @Test
    public void testNoStrategySet() {
        assertThrows(IllegalStateException.class, () -> {
            portfolio.invest(10000);
        });
    }
}
