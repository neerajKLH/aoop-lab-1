// GameApplication.java

// Singleton pattern to manage the game state
class GameState {
    private static GameState instance;
    private int currentLevel = 1;

    private GameState() {
    }

    public static synchronized GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }
        return instance;
    }

    public void advanceLevel() {
        currentLevel++;
        System.out.println("Advancing to level " + currentLevel);
    }

    public int getCurrentLevel() {
        return currentLevel;
    }
}

// Factory Method pattern for creating enemies
abstract class Enemy {
    public abstract void attack();
}

class EasyEnemy extends Enemy {
    public void attack() {
        System.out.println("Easy enemy attacks lightly!");
    }
}

class HardEnemy extends Enemy {
    public void attack() {
        System.out.println("Hard enemy attacks fiercely!");
    }
}

class EnemyFactory {
    public static Enemy createEnemy(String difficulty) {
        if (difficulty.equalsIgnoreCase("easy")) {
            return new EasyEnemy();
        } else if (difficulty.equalsIgnoreCase("hard")) {
            return new HardEnemy();
        } else {
            throw new IllegalArgumentException("Unknown difficulty: " + difficulty);
        }
    }
}

// Abstract Factory pattern for creating weapons and power-ups
interface Weapon {
    void use();
}

class Sword implements Weapon {
    public void use() {
        System.out.println("Swinging a sword!");
    }
}

class Gun implements Weapon {
    public void use() {
        System.out.println("Shooting a gun!");
    }
}

interface PowerUp {
    void apply();
}

class HealthPack implements PowerUp {
    public void apply() {
        System.out.println("Using health pack to restore health.");
    }
}

class Shield implements PowerUp {
    public void apply() {
        System.out.println("Using shield to block damage.");
    }
}

abstract class GameItemsFactory {
    public abstract Weapon createWeapon();
    public abstract PowerUp createPowerUp();
}

class EasyItemsFactory extends GameItemsFactory {
    public Weapon createWeapon() {
        return new Sword();
    }

    public PowerUp createPowerUp() {
        return new HealthPack();
    }
}

class HardItemsFactory extends GameItemsFactory {
    public Weapon createWeapon() {
        return new Gun();
    }

    public PowerUp createPowerUp() {
        return new Shield();
    }
}

// Main game application
public class GameApplication {
    public static void main(String[] args) {
        // Singleton: Managing game state
        GameState gameState = GameState.getInstance();
        System.out.println("Starting game at level " + gameState.getCurrentLevel());

        // Factory Method: Creating enemies based on difficulty
        Enemy easyEnemy = EnemyFactory.createEnemy("easy");
        easyEnemy.attack();

        Enemy hardEnemy = EnemyFactory.createEnemy("hard");
        hardEnemy.attack();

        // Abstract Factory: Creating game items based on difficulty
        GameItemsFactory easyItemsFactory = new EasyItemsFactory();
        Weapon easyWeapon = easyItemsFactory.createWeapon();
        PowerUp easyPowerUp = easyItemsFactory.createPowerUp();
        easyWeapon.use();
        easyPowerUp.apply();

        GameItemsFactory hardItemsFactory = new HardItemsFactory();
        Weapon hardWeapon = hardItemsFactory.createWeapon();
        PowerUp hardPowerUp = hardItemsFactory.createPowerUp();
        hardWeapon.use();
        hardPowerUp.apply();

        // Advance game state to next level
        gameState.advanceLevel();
        System.out.println("Current level is now " + gameState.getCurrentLevel());
    }
}