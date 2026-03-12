/**
 * UseCase3InventorySetup.java
 *
 * This class demonstrates centralized room inventory management
 * in the Book My Stay App (Hotel Booking System).
 *
 * Key Concepts:
 * - Centralized inventory using HashMap
 * - Encapsulation of inventory logic
 * - Single source of truth for room availability
 * - Scalable design for adding new room types
 *
 * @author YourName
 * @version 3.1
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
        System.out.println(getType() + " | Beds: " + getBeds() + " | Size: " + getSizeInSqFt() + " sq.ft | Price: $" + getPricePerNight());
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() { super("Double Room", 2, 90.0, 250); }
    @Override
    public void displayDetails() {
        System.out.println(getType() + " | Beds: " + getBeds() + " | Size: " + getSizeInSqFt() + " sq.ft | Price: $" + getPricePerNight());
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() { super("Suite Room", 3, 200.0, 500); }
    @Override
    public void displayDetails() {
        System.out.println(getType() + " | Beds: " + getBeds() + " | Size: " + getSizeInSqFt() + " sq.ft | Price: $" + getPricePerNight());
    }
}

// Centralized RoomInventory class
class RoomInventory {
    private Map<String, Integer> inventory;

    // Constructor initializes inventory with room types
    public RoomInventory() {
        inventory = new HashMap<>();
    }

    // Register a room type with initial availability
    public void addRoomType(String roomType, int count) {
        inventory.put(roomType, count);
    }

    // Get current availability of a room type
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Update availability (e.g., after booking or cancellation)
    public void updateAvailability(String roomType, int newCount) {
        if (inventory.containsKey(roomType)) {
            inventory.put(roomType, newCount);
        } else {
            System.out.println("Room type not found: " + roomType);
        }
    }

    // Display all room availability
    public void displayInventory() {
        System.out.println("Current Room Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " | Available: " + entry.getValue());
        }
    }
}

// Main class
public class UseCase3InventorySetup {

    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("Book My Stay App - Centralized Inventory");
        System.out.println("Hotel Booking System v3.1");
        System.out.println("==========================================");

        // Initialize rooms
        Room[] rooms = { new SingleRoom(), new DoubleRoom(), new SuiteRoom() };

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();
        inventory.addRoomType("Single Room", 5);
        inventory.addRoomType("Double Room", 3);
        inventory.addRoomType("Suite Room", 2);

        // Display room details
        for (Room room : rooms) {
            room.displayDetails();
            System.out.println("Available: " + inventory.getAvailability(room.getType()));
            System.out.println("------------------------------------------");
        }

        // Demonstrate updating inventory
        System.out.println("Booking one Double Room...");
        int updatedDoubleCount = inventory.getAvailability("Double Room") - 1;
        inventory.updateAvailability("Double Room", updatedDoubleCount);

        System.out.println("------------------------------------------");
        inventory.displayInventory();
        System.out.println("Application executed successfully.");
    }
}