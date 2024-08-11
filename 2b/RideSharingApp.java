// RideSharingApp.java

// Singleton pattern to manage user authentication
class UserAuthentication {
    private static UserAuthentication instance;
    private boolean isAuthenticated = false;

    private UserAuthentication() {
    }

    public static synchronized UserAuthentication getInstance() {
        if (instance == null) {
            instance = new UserAuthentication();
        }
        return instance;
    }

    public void login(String username, String password) {
        if (username.equals("user") && password.equals("password")) {
            isAuthenticated = true;
            System.out.println("User logged in successfully!");
        } else {
            System.out.println("Invalid credentials!");
        }
    }

    public void logout() {
        isAuthenticated = false;
        System.out.println("User logged out successfully!");
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }
}

// Factory Method pattern for creating vehicles
abstract class Vehicle {
    public abstract void ride();
}

class Car extends Vehicle {
    public void ride() {
        System.out.println("Car is on the way!");
    }
}

class Bike extends Vehicle {
    public void ride() {
        System.out.println("Bike is on the way!");
    }
}

class Scooter extends Vehicle {
    public void ride() {
        System.out.println("Scooter is on the way!");
    }
}

class VehicleFactory {
    public static Vehicle createVehicle(String type) {
        switch (type.toLowerCase()) {
            case "car":
                return new Car();
            case "bike":
                return new Bike();
            case "scooter":
                return new Scooter();
            default:
                throw new IllegalArgumentException("Unknown vehicle type: " + type);
        }
    }
}

// Abstract Factory pattern for creating payment methods
interface PaymentMethod {
    void pay(double amount);
}

class CreditCard implements PaymentMethod {
    public void pay(double amount) {
        System.out.println("Paying $" + amount + " with Credit Card.");
    }
}

class PayPal implements PaymentMethod {
    public void pay(double amount) {
        System.out.println("Paying $" + amount + " with PayPal.");
    }
}

class Cash implements PaymentMethod {
    public void pay(double amount) {
        System.out.println("Paying $" + amount + " with Cash.");
    }
}

abstract class PaymentFactory {
    public abstract PaymentMethod createPaymentMethod();
}

class CreditCardFactory extends PaymentFactory {
    public PaymentMethod createPaymentMethod() {
        return new CreditCard();
    }
}

class PayPalFactory extends PaymentFactory {
    public PaymentMethod createPaymentMethod() {
        return new PayPal();
    }
}

class CashFactory extends PaymentFactory {
    public PaymentMethod createPaymentMethod() {
        return new Cash();
    }
}

// Main Ride-Sharing Application
public class RideSharingApp {
    public static void main(String[] args) {
        // Singleton: Managing user authentication
        UserAuthentication auth = UserAuthentication.getInstance();

        // User tries to request a ride without logging in
        if (!auth.isAuthenticated()) {
            System.out.println("Please log in to request a ride.");
        }

        // User logs in
        auth.login("user", "password");

        // Factory Method: Creating vehicles
        Vehicle vehicle = VehicleFactory.createVehicle("car");
        vehicle.ride();

        vehicle = VehicleFactory.createVehicle("bike");
        vehicle.ride();

        vehicle = VehicleFactory.createVehicle("scooter");
        vehicle.ride();

        // Abstract Factory: Creating payment methods
        PaymentFactory paymentFactory = new CreditCardFactory();
        PaymentMethod paymentMethod = paymentFactory.createPaymentMethod();
        paymentMethod.pay(15.75);

        paymentFactory = new PayPalFactory();
        paymentMethod = paymentFactory.createPaymentMethod();
        paymentMethod.pay(8.50);

        paymentFactory = new CashFactory();
        paymentMethod = paymentFactory.createPaymentMethod();
        paymentMethod.pay(5.00);

        // User logs out
        auth.logout();

        // Try to request another ride after logout
        if (!auth.isAuthenticated()) {
            System.out.println("Please log in to request a ride.");
        }
    }
}