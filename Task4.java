import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Room {
    private int roomNumber;
    private String roomType;
    private boolean isReserved;

    public Room(int roomNumber, String roomType) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.isReserved = false;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void reserveRoom() {
        isReserved = true;
    }

    public void cancelReservation() {
        isReserved = false;
    }
}

class Hotel {
    private String name;
    private List<Room> rooms;

    public Hotel(String name) {
        this.name = name;
        this.rooms = new ArrayList<>();
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public List<Room> getAvailableRooms() {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (!room.isReserved()) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public Room getRoomByNumber(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null;
    }
}

public class HotelReservationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create a hotel with rooms
        Hotel hotel = new Hotel("Grand Hotel");
        hotel.addRoom(new Room(101, "Standard"));
        hotel.addRoom(new Room(102, "Standard"));
        hotel.addRoom(new Room(201, "Deluxe"));
        hotel.addRoom(new Room(202, "Deluxe"));
        hotel.addRoom(new Room(301, "Suite"));

        while (true) {
            System.out.println("1. Search for available rooms");
            System.out.println("2. Make a reservation");
            System.out.println("3. View booking details");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    List<Room> availableRooms = hotel.getAvailableRooms();
                    System.out.println("Available Rooms:");
                    for (Room room : availableRooms) {
                        System.out.println("Room Number: " + room.getRoomNumber() + ", Type: " + room.getRoomType());
                    }
                    break;
                case 2:
                    System.out.print("Enter room number to reserve: ");
                    int roomNumber = scanner.nextInt();
                    Room selectedRoom = hotel.getRoomByNumber(roomNumber);
                    if (selectedRoom != null && !selectedRoom.isReserved()) {
                        selectedRoom.reserveRoom();
                        System.out.println("Room reserved successfully!");
                    } else {
                        System.out.println("Invalid room number or room already reserved!");
                    }
                    break;
                case 3:
                    System.out.print("Enter room number to view booking details: ");
                    int bookingRoomNumber = scanner.nextInt();
                    Room bookingRoom = hotel.getRoomByNumber(bookingRoomNumber);
                    if (bookingRoom != null && bookingRoom.isReserved()) {
                        System.out.println("Booking Details:");
                        System.out.println("Room Number: " + bookingRoom.getRoomNumber());
                        System.out.println("Room Type: " + bookingRoom.getRoomType());
                    } else {
                        System.out.println("Invalid room number or room not reserved!");
                    }
                    break;
                case 4:
                    System.out.println("Thank you for using our system. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Please enter a number between 1 and 4.");
            }
        }
    }
}
