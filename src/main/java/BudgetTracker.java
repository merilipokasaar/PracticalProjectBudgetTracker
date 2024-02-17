import java.util.Scanner;

class BudgetTracker {
    private double totalIncome;
    private double totalExpenses;
    private double goalPercentage;
    private Scanner scanner;

    public BudgetTracker() {
        scanner = new Scanner(System.in);
    }

    public void start() {
        welcomeMessage();
        pausePrint();
        enterInitialIncome();
        setSavingsGoal();
        displayGoalConfirmation();

        while (true) {
            pausePrint();
            displayMenu();
            int choice = getUserChoice();
            processChoice(choice);
        }

    }

    private void welcomeMessage() {
        System.out.println("Welcome to the Budget Tracker!");
        System.out.println("Let's begin!");
    }

    private static void pausePrint() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void enterInitialIncome() {
        do {
            System.out.println("Enter your income:");
            totalIncome = getAPositiveNumberInput();
            if (totalIncome <= 0) {
                System.out.println("Income needs to be a positive number!");
            }
        } while (totalIncome <= 0);
        System.out.println("Income inserted!");
    }

    private void setSavingsGoal() {
        while (true) {
            System.out.println("Please enter your savings goal(%): ");
            goalPercentage = getAPositiveNumberInput();
            if (goalPercentage <= 0) {
                System.out.println("Be ambitious! Choose a bigger savings goal!");
            } else {
                System.out.println("Goal has been set successfully");
                break;
            }
        }
    }

    private void displayGoalConfirmation() {
        System.out.println("That's great! With your savings goal, you'll be able to save "
                + (totalIncome * goalPercentage / 100) + " euros monthly!");
    }

    private void displayMenu() {
        System.out.println("Menu:");
        System.out.println("1. Add additional income");
        System.out.println("2. Add an expense");
        System.out.println("3. Check current savings status");
        System.out.println("4. Exit");
    }

    private int getUserChoice() {
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    private void processChoice(int choice) {
        switch (choice) {
            case 1:
                addAdditionalIncome();
                break;
            case 2:
                addExpense();
                break;
            case 3:
                checkSavingsStatus();
                break;
            case 4:
                exitBudgetTracker();
                break;
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                break;
        }
    }

    private double getAPositiveNumberInput() {
        double amount;
        while (true) {
            if (scanner.hasNextDouble()) {
                amount = scanner.nextDouble();
                if (amount >= 0) {
                    break;
                } else {
                    System.out.println("Invalid input! Please enter a positive number.");
                }
            } else {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next();
            }
        }
        return amount;
    }

    private void addAdditionalIncome() {
        System.out.print("Enter additional income: ");
        double additionalIncome = getAPositiveNumberInput();
        totalIncome += additionalIncome;
        System.out.println("Additional income added successfully. Total of your earnings this month is: "
                + totalIncome + " euros");
    }

    private void addExpense() {
        System.out.print("Enter expense: ");
        double expense = getAPositiveNumberInput();
        totalExpenses += expense;
        System.out.println("Expense added successfully. Total of your expenses this month is: "
                + totalExpenses + " euros");
    }

    private void checkSavingsStatus() {
        double savingsPercentage = Math.round(100 - (totalExpenses * 100 / totalIncome));
        double savingsAmount = Math.round(totalIncome - totalExpenses);
        System.out.println("At the moment your savings percentage is " + savingsPercentage
                + "% and you have saved " + savingsAmount + " euros");
        if (savingsPercentage < goalPercentage) {
            System.out.println("Your expenses are too high! Try to save more.");
        } else {
            System.out.println("Well done! You are on the right track!");
        }
    }

    private void exitBudgetTracker() {
        System.out.println("Exiting Budget Tracker. See you!");
        System.exit(0);
    }
}
