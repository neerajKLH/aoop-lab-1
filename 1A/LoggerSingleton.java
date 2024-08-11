// LoggerSingleton.java
public class LoggerSingleton {

    // Private static instance of the class, initially null
    private static LoggerSingleton instance;

    // Private constructor to prevent instantiation from other classes
    private LoggerSingleton() {
        // You can initialize your logger configuration here if needed
    }

    // Public method to provide access to the single instance
    public static synchronized LoggerSingleton getInstance() {
        if (instance == null) {
            instance = new LoggerSingleton();
        }
        return instance;
    }

    // Method to log messages
    public void log(String message) {
        System.out.println("Log: " + message);
        // You can expand this method to log messages to a file, database, etc.
    }

    // Main method to demonstrate the Singleton Logger
    public static void main(String[] args) {
        // Getting the single instance of Logger
        LoggerSingleton logger = LoggerSingleton.getInstance();

        // Logging messages using the singleton logger instance
        logger.log("This is the first log message.");
        logger.log("This is the second log message.");

        // Demonstrating that the same instance is used
        LoggerSingleton anotherLogger = LoggerSingleton.getInstance();
        anotherLogger.log("This is the third log message.");
    }
}