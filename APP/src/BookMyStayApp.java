/**
 * UseCase2RoomInitialization.java
 *
 * This class demonstrates basic room types and static availability
 * in the Book My Stay App (Hotel Booking System).
 *
 * Key Concepts:
 * - Abstract class for generalized room
 * - Concrete room classes (SingleRoom, DoubleRoom, SuiteRoom)
 * - Inheritance and polymorphism
 * - Encapsulation of room attributes
 * - Static representation of availability
 *
 * @author YourName
 * @version 2.1
 */

// Abstract Room class representing a general room
abstract class Room {
    private String type;
    private int beds;
    private double pricePerNight;
    private int sizeInSqFt;

    // Constructor
    public Room(String type, int beds, double pricePerNight, int sizeInSqFt) {
        this.type = type;
        this.beds = beds;
        this.pricePerNight = pricePerNight;
        this.sizeInSqFt = sizeInSqFt;
    }

    // Abstract method to display room details
    public abstract void displayDetails();

    // Getters
    public String getType() { return type; }
    public int getBeds() { return beds; }
    public double getPricePerNight() { return pricePerNight; }
    public int getSizeInSqFt() { return sizeInSqFt; }
}

// SingleRoom class extending Room
class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1, 50.0, 150);
    }

    @Override
    public void displayDetails() {
        System.out.println(getType() + " | Beds: " + getBeds() + " | Size: " + getSizeInSqFt() + " sq.ft | Price: $" + getPricePerNight());
    }
}

// DoubleRoom class extending Room
class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2, 90.0, 250);
    }

    @Override
    public void displayDetails() {
        System.out.println(getType() + " | Beds: " + getBeds() + " | Size: " + getSizeInSqFt() + " sq.ft | Price: $" + getPricePerNight());
    }
}

// SuiteRoom class extending Room
class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 3, 200.0, 500);
    }

    @Override
    public void displayDetails() {
        System.out.println(getType() + " | Beds: " + getBeds() + " | Size: " + getSizeInSqFt() + " sq.ft | Price: $" + getPricePerNight());
    }
}

// Main class to initialize and display rooms
public class UseCase2RoomInitialization {

    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("Book My Stay App - Room Availability");
        System.out.println("Hotel Booking System v2.1");
        System.out.println("==========================================");

        // Static availability variables
        int availableSingleRooms = 5;
        int availableDoubleRooms = 3;
        int availableSuiteRooms = 2;

        // Polymorphic room array
        Room[] rooms = { new SingleRoom(), new DoubleRoom(), new SuiteRoom() };

        // Display room details and availability
        for (Room room : rooms) {
            room.displayDetails();
            if (room instanceof SingleRoom) {
                System.out.println("Available: " + availableSingleRooms);
            } else if (room instanceof DoubleRoom) {
                System.out.println("Available: " + availableDoubleRooms);
            } else if (room instanceof SuiteRoom) {
                System.out.println("Available: " + availableSuiteRooms);
            }
            System.out.println("------------------------------------------");
        }

        System.out.println("Application executed successfully.");
    }
}