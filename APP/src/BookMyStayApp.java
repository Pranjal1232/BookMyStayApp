/**
 * UseCase4RoomSearch.java
 *
 * This class demonstrates room search and availability check
 * in the Book My Stay App (Hotel Booking System).
 *
 * Key Concepts:
 * - Read-only access to inventory
 * - Defensive programming to filter unavailable rooms
 * - Separation of concerns between search and booking
 * - Use of Room domain objects to display details
 *
 * @author YourName
 * @version 4.0
 */

import java.util.HashMap;
import java.util.Map;

// Abstract Room class
abstract class Room {
    private String type;
    private int beds;
    private double pricePerNight;
    private int sizeInSqFt;

    public Room(String type, int beds, double pricePerNight, int sizeInSqFt) {
        this.type = type;
        this.beds = beds;
        this.pricePerNight = pricePerNight;
        this.sizeInSqFt = sizeInSqFt;
    }

    public abstract void displayDetails();

    public String getType() { return type; }
    public int getBeds() { return beds; }
    public double getPricePerNight() { return pricePerNight; }
    public int getSizeInSqFt() { return sizeInSqFt; }
}

// Concrete room classes
class SingleRoom extends Room {
    public SingleRoom() { super("Single Room", 1, 50.0, 150); }
    @Override
    public void displayDetails() {
        System.out.println(getType() + " | Beds: " + getBeds() +
                " | Size: " + getSizeInSqFt() + " sq.ft | Price: $" + getPricePerNight());
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() { super("Double Room", 2, 90.0, 250); }
    @Override
    public void displayDetails() {
        System.out.println(getType() + " | Beds: " + getBeds() +
                " | Size: " + getSizeInSqFt() + " sq.ft | Price: $" + getPricePerNight());
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() { super("Suite Room", 3, 200.0, 500); }
    @Override
    public void displayDetails() {
        System.out.println(getType() + " | Beds: " + getBeds() +
                " | Size: " + getSizeInSqFt() + " sq.ft | Price: $" + getPricePerNight());
    }
}

// Centralized RoomInventory class
class RoomInventory {
    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
    }

    public void addRoomType(String roomType, int count) {
        inventory.put(roomType, count);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Read-only access method
    public Map<String, Integer> getAllAvailability() {
        return new HashMap<>(inventory); // Return a copy to prevent mutation
    }
}

// RoomSearchService class
class RoomSearchService {

    private RoomInventory inventory;

    public RoomSearchService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    // Display available rooms without modifying inventory
    public void displayAvailableRooms(Room[] rooms) {
        System.out.println("Available Rooms:");
        for (Room room : rooms) {
            int available = inventory.getAvailability(room.getType());
            if (available > 0) {
                room.displayDetails();
                System.out.println("Available: " + available);
                System.out.println("------------------------------------------");
            }
        }
    }
}

// Main class
public class UseCase4RoomSearch {

    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("Book My Stay App - Room Search");
        System.out.println("Hotel Booking System v4.0");
        System.out.println("==========================================");

        // Initialize rooms
        Room[] rooms = { new SingleRoom(), new DoubleRoom(), new SuiteRoom() };

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();
        inventory.addRoomType("Single Room", 5);
        inventory.addRoomType("Double Room", 0); // Simulate no availability
        inventory.addRoomType("Suite Room", 2);

        // Perform search
        RoomSearchService searchService = new RoomSearchService(inventory);
        searchService.displayAvailableRooms(rooms);

        System.out.println("Search completed without modifying inventory.");
    }
}