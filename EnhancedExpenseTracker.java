import java.util.ArrayList;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EnhancedExpenseTracker {
    private static ArrayList<Expense> expenses = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            displayMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addExpense();
                    break;
                case 2:
                    viewTotalExpenses();
                    break;
                case 3:
                    viewAllExpenses();
                    break;
                case 4:
                    System.out.println("Exiting Expense Tracker. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (choice != 4);

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("Expense Tracker Menu:");
        System.out.println("1. Add Expense");
        System.out.println("2. View Total Expenses");
        System.out.println("3. View All Expenses");
        System.out.println("4. Exit");
    }

    private static void addExpense() {
        System.out.print("Enter the expense amount: $");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Enter a brief description: ");
        String description = scanner.nextLine();

        System.out.print("Enter the expense category: ");
        String category = scanner.nextLine();

        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        Expense expense = new Expense(amount, description, category, date);
        expenses.add(expense);

        System.out.println("Expense added successfully!");
    }

    private static void viewTotalExpenses() {
        double totalExpenses = expenses.stream().mapToDouble(Expense::getAmount).sum();
        System.out.println("Total Expenses: $" + totalExpenses);
    }

    private static void viewAllExpenses() {
        System.out.println("All Expenses:");
        for (Expense expense : expenses) {
            System.out.println(expense);
        }
    }

    private static class Expense {
        private double amount;
        private String description;
        private String category;
        private String date;

        public Expense(double amount, String description, String category, String date) {
            this.amount = amount;
            this.description = description;
            this.category = category;
            this.date = date;
        }

        public double getAmount() {
            return amount;
        }

        @Override
        public String toString() {
            return String.format("Date: %s, Amount: $%.2f, Description: %s, Category: %s", date, amount, description, category);
        }
    }
}