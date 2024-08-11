// BankingLoginSingleton.java
class BankingLoginSingleton {

    // Private static instance of the class, initially null
    private static BankingLoginSingleton instance;

    // Private variable to store the login state
    private boolean isLoggedIn = false;

    // Private constructor to prevent instantiation from other classes
    private BankingLoginSingleton() {
    }

    // Public method to provide access to the single instance
    public static synchronized BankingLoginSingleton getInstance() {
        if (instance == null) {
            instance = new BankingLoginSingleton();
        }
        return instance;
    }

    // Method to log in the user
    public void login(String username, String password) {
        if (username.equals("user") && password.equals("pass")) {
            isLoggedIn = true;
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid credentials!");
        }
    }

    // Method to log out the user
    public void logout() {
        isLoggedIn = false;
        System.out.println("Logged out successfully!");
    }

    // Method to check the login state
    public boolean checkLogin() {
        return isLoggedIn;
    }

    // Method to view balance
    public void viewBalance() {
        if (isLoggedIn) {
            System.out.println("Your balance is $1000.");
        } else {
            System.out.println("Please log in to view your balance.");
        }
    }

    // Method to deposit money
    public void deposit(int amount) {
        if (isLoggedIn) {
            System.out.println("Deposited $" + amount + " successfully.");
        } else {
            System.out.println("Please log in to deposit money.");
        }
    }

    // Method to withdraw money
    public void withdraw(int amount) {
        if (isLoggedIn) {
            System.out.println("Withdrew $" + amount + " successfully.");
        } else {
            System.out.println("Please log in to withdraw money.");
        }
    }

    // Main method to demonstrate the Singleton Banking Login system
    public static void main(String[] args) {
        // Getting the single instance of BankingLoginSingleton
        BankingLoginSingleton bankingLogin = BankingLoginSingleton.getInstance();

        // Trying to view balance before logging in
        bankingLogin.viewBalance();

        // Logging in with correct credentials
        bankingLogin.login("user", "pass");

        // Performing banking operations
        bankingLogin.viewBalance();
        bankingLogin.deposit(500);
        bankingLogin.withdraw(300);

        // Logging out
        bankingLogin.logout();

        // Trying to perform an operation after logging out
        bankingLogin.viewBalance();
    }
}