import java.util.Scanner;
import java.util.Vector;

public class BookStore {

    Scanner sc = new Scanner(System.in);

    Vector<String> customerNames = new Vector<>();
    Vector<Integer> totalItems = new Vector<>();
    Vector<Integer> totalPrices = new Vector<>();

    String[] itemNames = {
            "Java Programming Book",
            "Python Programming Book",
            "Website Development Book",
            "C++ Programming Book",
            "PHP Programming Book",
            "Flutter Development Book"
    };

    int[] itemPrices = { 50000, 60000, 70000, 50000, 17000, 80000 };

    public static void main(String[] args) {
        new BookStore().run();
    }

    void run() {
        int choice;
        do {
            printMenu();
            choice = scanInt();

            switch (choice) {
                case 1:
                    insertTransaction();
                    break;
                case 2:
                    deleteTransaction();
                    break;
                case 3:
                    System.out.println("-^ Thanks for using this program ^-");
                    break;
                default:
                    System.out.println("Invalid menu!");
            }
        } while (choice != 3);
    }

    void printMenu() {
        System.out.println("\nBook Store");
        System.out.println("===================");
        System.out.println("1. Insert Transaction");
        System.out.println("2. Delete Transaction");
        System.out.println("3. Exit");
        System.out.print("Choose >> ");
    }

    int scanInt() {
        while (true) {
            try {
                int input = sc.nextInt();
                sc.nextLine();
                return input;
            } catch (Exception e) {
                sc.nextLine();
                System.out.print("Input must be a number! Try again: ");
            }
        }
    }

    void insertTransaction() {
        String name;
        int items;

        do {
            System.out.print("Input Customer Name [5 - 20 characters]: ");
            name = sc.nextLine();
        } while (name.length() < 5 || name.length() > 20);

        do {
            System.out.print("Input how many items [1 - 5]: ");
            items = scanInt();
        } while (items < 1 || items > 5);

        int totalPrice = 0;

        // TEMPORARY STORAGE FOR BILL
        Vector<String> boughtItems = new Vector<>();
        Vector<Integer> boughtPrices = new Vector<>();

        printItemTable();

        for (int i = 0; i < items; i++) {
            int choose;
            do {
                System.out.print("Choose item ID for item " + (i + 1) + " [1 - 6]: ");
                choose = scanInt();
            } while (choose < 1 || choose > 6);

            boughtItems.add(itemNames[choose - 1]);
            boughtPrices.add(itemPrices[choose - 1]);
            totalPrice += itemPrices[choose - 1];
        }

        // PRINT BILL
        bill(name, boughtItems, boughtPrices, totalPrice);

        // PAYMENT
        int payment;
        int change;

        do {
            System.out.print("Input Payment : ");
            payment = scanInt();

            if (payment < totalPrice) {
                System.out.println("Payment is not enough!");
            }
        } while (payment < totalPrice);

        change = payment - totalPrice;
        System.out.println("Your change is " + change);

        // SAVE TRANSACTION
        customerNames.add(name);
        totalItems.add(items);
        totalPrices.add(totalPrice);

        System.out.println("\nTransaction Success!");
    }

    void bill(String customerName, Vector<String> items, Vector<Integer> prices, int totalPrice) {
        System.out.println("\nBill");
        System.out.println("--------------------------------");
        System.out.println("Customer Name : " + customerName);
        System.out.println("\nItem(s)");
        System.out.println("-----------------------------------------------");
        System.out.printf("| %-3s | %-25s | %-8s |\n", "No", "Detail Transaction", "Price");
        System.out.println("-----------------------------------------------");

        for (int i = 0; i < items.size(); i++) {
            System.out.printf("| %-3d | %-25s | %-8d |\n",
                    (i + 1),
                    items.get(i),
                    prices.get(i));
        }

        System.out.println("-----------------------------------------------");
        System.out.println("Total Price : " + totalPrice);
        System.out.println("-----------------------------------------------");
    }

    void printItemTable() {
        System.out.println("\nList Item");
        System.out.println("================================================");
        System.out.printf("| %-3s | %-30s | %-5s |\n", "ID", "Item Name", "Price");
        System.out.println("================================================");

        for (int i = 0; i < itemNames.length; i++) {
            System.out.printf("| %-3d | %-30s | %-5d |\n",
                    (i + 1),
                    itemNames[i],
                    itemPrices[i]);
        }

        System.out.println("================================================");
    }

    void deleteTransaction() {
        if (customerNames.isEmpty()) {
            System.out.println("No transaction available!");
            return;
        }

        System.out.println("\nTransaction History");
        System.out.println("================================================");
        System.out.printf("| %-3s | %-20s | %-5s | %-7s |\n",
                "No", "Customer Name", "Item", "Total");
        System.out.println("================================================");

        for (int i = 0; i < customerNames.size(); i++) {
            System.out.printf("| %-3d | %-20s | %-5d | %-7d |\n",
                    (i + 1),
                    customerNames.get(i),
                    totalItems.get(i),
                    totalPrices.get(i));
        }

        System.out.println("================================================");

        int del;
        do {
            System.out.print("Choose transaction to delete [1 - "
                    + customerNames.size() + ", 0 to cancel]: ");
            del = scanInt();

            // BACK TO MAIN MENU
            if (del == 0) {
                System.out.println("Cancel delete transaction.");
                return;
            }

        } while (del < 1 || del > customerNames.size());

        customerNames.remove(del - 1);
        totalItems.remove(del - 1);
        totalPrices.remove(del - 1);

        System.out.println("Transaction deleted successfully!");
    }

}
