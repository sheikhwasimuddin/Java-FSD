//problem statement 1: create using java collection the system should allow adding food items to an order,removing items and calculating the total bill. use to store order item and to store the price of each food item.

//mspa1_sheikh wasimuddin_iot63
import java.util.*;

class Item {
    String name;
    double price;

    Item(String n, double p) {
        name = n;
        price = p;
    }
}

public class MSPA1PS1 {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Item> list = new ArrayList<>();

    public static void addItem() {
        System.out.print("Enter food name: ");
        String name = sc.nextLine();

        System.out.print("Enter price: ");
        double price = sc.nextDouble();
        sc.nextLine();

        list.add(new Item(name, price));
    }

    public static void removeItem() {
        System.out.print("Enter item name to remove: ");
        String name = sc.nextLine();

        boolean found = false;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).name.equalsIgnoreCase(name)) {
                list.remove(i);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Item not found");
        }
    }

    public static void viewOrder() {
        if (list.isEmpty()) {
            System.out.println("Order is empty");
            return;
        }

        for (Item i : list) {
            System.out.println(i.name + " - " + i.price);
        }
    }

    public static void totalPrice() {
        double total = 0;

        for (Item i : list) {
            total += i.price;
        }

        System.out.println("Total Price: " + total);
    }

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n1 Add Item");
            System.out.println("2 Remove Item");
            System.out.println("3 View Order");
            System.out.println("4 Total Price");
            System.out.println("5 Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addItem();
                    break;
                case 2:
                    removeItem();
                    break;
                case 3:
                    viewOrder();
                    break;
                case 4:
                    totalPrice();
                    break;
            }

        } while (choice != 5);
    }
}