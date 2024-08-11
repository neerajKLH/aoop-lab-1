import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Enum representing the severity levels
enum LogLevel {
    INFO, DEBUG, ERROR;
}

// Command interface declaring the execute method
interface Command {
    void execute(String message);
}

// LogCommand class implementing the Command interface
class LogCommand implements Command {
    private LogHandler handler;
    private String message;
    private LogLevel level;

    public LogCommand(LogHandler handler, String message, LogLevel level) {
        this.handler = handler;
        this.message = message;
        this.level = level;
    }

    @Override
    public void execute(String message) {
        handler.handle(message, level);
    }

    public void execute() {
        execute(message);
    }
}

// Abstract LogHandler class (part of Chain of Responsibility)
abstract class LogHandler {
    protected LogHandler nextHandler;

    public void setNextHandler(LogHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public void handle(String message, LogLevel level) {
        if (canHandle(level)) {
            log(message);
        } else if (nextHandler != null) {
            nextHandler.handle(message, level);
        }
    }

    protected abstract boolean canHandle(LogLevel level);

    protected abstract void log(String message);
}

// Concrete handler for INFO level
class InfoHandler extends LogHandler {
    @Override
    protected boolean canHandle(LogLevel level) {
        return level == LogLevel.INFO;
    }

    @Override
    protected void log(String message) {
        System.out.println("INFO: " + message);
    }
}

// Concrete handler for DEBUG level
class DebugHandler extends LogHandler {
    @Override
    protected boolean canHandle(LogLevel level) {
        return level == LogLevel.DEBUG;
    }

    @Override
    protected void log(String message) {
        System.out.println("DEBUG: " + message);
    }
}

// Concrete handler for ERROR level
class ErrorHandler extends LogHandler {
    @Override
    protected boolean canHandle(LogLevel level) {
        return level == LogLevel.ERROR;
    }

    @Override
    protected void log(String message) {
        System.out.println("ERROR: " + message);
    }
}

// Logger class using Iterator to process a list of commands
class Logger {
    private List<Command> commands = new ArrayList<>();

    public void addCommand(Command command) {
        commands.add(command);
    }

    public void processCommands() {
        Iterator<Command> iterator = commands.iterator();
        while (iterator.hasNext()) {
            Command command = iterator.next();
            command.execute();
        }
    }
}

// Client class to configure and demonstrate the logging system
public class LoggingSystem {
    public static void main(String[] args) {
        // Setting up the chain of responsibility
        LogHandler infoHandler = new InfoHandler();
        LogHandler debugHandler = new DebugHandler();
        LogHandler errorHandler = new ErrorHandler();

        infoHandler.setNextHandler(debugHandler);
        debugHandler.setNextHandler(errorHandler);

        // Creating the Logger and adding commands
        Logger logger = new Logger();

        logger.addCommand(new LogCommand(infoHandler, "System started successfully.", LogLevel.INFO));
        logger.addCommand(new LogCommand(debugHandler, "Debugging connection issue.", LogLevel.DEBUG));
        logger.addCommand(new LogCommand(errorHandler, "Error detected in module X.", LogLevel.ERROR));

        // Processing all the commands
        logger.processCommands();
    }
}