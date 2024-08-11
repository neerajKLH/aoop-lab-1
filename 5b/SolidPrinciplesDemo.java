import java.util.ArrayList;
import java.util.List;

// 1. Single Responsibility Principle (SRP)
// Problem Statement: Manage employee details and salary calculations

// Employee class to manage employee details
class Employee {
    private String name;
    private String role;

    public Employee(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }
}

// SalaryCalculator class to handle salary calculations
class SalaryCalculator {
    public double calculateSalary(Employee employee) {
        switch (employee.getRole()) {
            case "Developer":
                return 60000;
            case "Manager":
                return 80000;
            default:
                return 40000;
        }
    }
}

// 2. Open/Closed Principle (OCP)
// Problem Statement: Calculate the area of various shapes

// Abstract Shape class with a method to calculate the area
abstract class Shape {
    public abstract double calculateArea();
}

// Rectangle class extending Shape
class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return width * height;
    }
}

// Circle class extending Shape
class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

// 3. Liskov Substitution Principle (LSP)
// Problem Statement: Bird management system with flying capability

// Bird class with a method to simulate flying
abstract class Bird {
    public abstract void fly();
}

// Sparrow class that can fly
class Sparrow extends Bird {
    @Override
    public void fly() {
        System.out.println("Sparrow is flying.");
    }
}

// Ostrich class that cannot fly (LSP Violation Example)
class Ostrich extends Bird {
    @Override
    public void fly() {
        throw new UnsupportedOperationException("Ostriches cannot fly.");
    }
}

// 4. Interface Segregation Principle (ISP)
// Problem Statement: Worker management system with specific actions

// Worker interface
interface Worker {
    void work();
}

// Eater interface
interface Eater {
    void eat();
}

// Robot class that only works
class Robot implements Worker {
    @Override
    public void work() {
        System.out.println("Robot is working.");
    }
}

// Human class that both works and eats
class Human implements Worker, Eater {
    @Override
    public void work() {
        System.out.println("Human is working.");
    }

    @Override
    public void eat() {
        System.out.println("Human is eating.");
    }
}

// 5. Dependency Inversion Principle (DIP)
// Problem Statement: Message processing system with different message services

// MessageService interface
interface MessageService {
    void sendMessage(String message);
}

// EmailService class implementing MessageService
class EmailService implements MessageService {
    @Override
    public void sendMessage(String message) {
        System.out.println("Sending Email: " + message);
    }
}

// SMSService class implementing MessageService
class SMSService implements MessageService {
    @Override
    public void sendMessage(String message) {
        System.out.println("Sending SMS: " + message);
    }
}

// MyApplication class depending on MessageService interface
class MyApplication {
    private MessageService messageService;

    public MyApplication(MessageService messageService) {
        this.messageService = messageService;
    }

    public void sendNotification(String message) {
        messageService.sendMessage(message);
    }
}

// Main class to demonstrate all SOLID principles
public class SolidPrinciplesDemo {
    public static void main(String[] args) {
        // SRP Example
        Employee emp = new Employee("Alice", "Developer");
        SalaryCalculator salaryCalculator = new SalaryCalculator();
        System.out.println("Salary of " + emp.getName() + ": $" + salaryCalculator.calculateSalary(emp));

        // OCP Example
        Shape rectangle = new Rectangle(10, 5);
        Shape circle = new Circle(7);
        System.out.println("Area of Rectangle: " + rectangle.calculateArea());
        System.out.println("Area of Circle: " + circle.calculateArea());

        // LSP Example
        Bird sparrow = new Sparrow();
        sparrow.fly();
        try {
            Bird ostrich = new Ostrich();
            ostrich.fly();
        } catch (UnsupportedOperationException e) {
            System.out.println(e.getMessage());
        }

        // ISP Example
        Worker robot = new Robot();
        robot.work();
        Worker human = new Human();
        human.work();
        ((Eater) human).eat();

        // DIP Example
        MessageService emailService = new EmailService();
        MyApplication app = new MyApplication(emailService);
        app.sendNotification("Hello via Email!");

        MessageService smsService = new SMSService();
        app = new MyApplication(smsService);
        app.sendNotification("Hello via SMS!");
    }
}