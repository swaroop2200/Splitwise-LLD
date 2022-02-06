import manager.ExpenseManager;
import model.Expense;
import model.ExpenseType;
import model.User;
import service.SplitService;

import java.util.*;

public class Driver {

    public static void main(String[] args) {

        ExpenseManager expenseManager = new ExpenseManager();
        SplitService splitService = new SplitService();

        expenseManager.addUser(new User("u1", "User1", "gaurav@workat.tech", "9876543210"));
        expenseManager.addUser(new User("u2", "User2", "sagar@workat.tech", "9876543210"));
        expenseManager.addUser(new User("u3", "User3", "hi@workat.tech", "9876543210"));
        expenseManager.addUser(new User("u4", "User4", "mock-interviews@workat.tech", "9876543210"));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String command = scanner.nextLine();
            String[] commands = command.split(" ");
            String commandType = commands[0];

            if ("SHOW".equals(commandType)) {
                if (commands.length == 1) {
                    expenseManager.showBalances();
                } else {
                    expenseManager.showBalance(commands[1]);
                }
            } else if ("EXPENSE".equals(commandType)) {
                User paidBy = expenseManager.getUser(commands[1]);
                double amount = Double.parseDouble(commands[2]);
                int noOfUsers = Integer.parseInt(commands[3]);
                String expenseType = commands[4 + noOfUsers];
                // List<Split> splits = new ArrayList<Split>();
                List<User> owedUsers = new ArrayList<User>();

                for (int i = 0; i < noOfUsers; i++) {
                    owedUsers.add(expenseManager.getUser(commands[4 + i]));
                }

                if ("EQUAL".equals(expenseType)) {
                    Expense expense = new Expense(
                            amount,
                            paidBy,
                            splitService.getEqualSplits(amount, owedUsers, paidBy),
                            null,
                            ExpenseType.EQUAL);

                    expenseManager.addExpense(expense);
                } else if ("EXACT".equals(expenseType)) {
                    Expense expense = new Expense(
                            amount,
                            paidBy,
                            splitService.getExactSplits(amount, getUserContributions(commands, noOfUsers, expenseManager), paidBy),
                            null,
                            ExpenseType.EXACT);
                    expenseManager.addExpense(expense);
                } else if ("PERCENT".equals(expenseType)) {
                    Expense expense = new Expense(
                            amount,
                            paidBy,
                            splitService.getPercentageSplits(amount, getUserContributions(commands, noOfUsers, expenseManager), paidBy),
                            null,
                            ExpenseType.PERCENT);
                    expenseManager.addExpense(expense);
                }
            }
        }
    }

    private static Map<User, Double> getUserContributions(String[] commands, int noOfUsers, ExpenseManager expenseManager){
        Map<User, Double> map = new HashMap<User, Double>();
        for (int i = 0; i < noOfUsers; i++){
            map.put(expenseManager.getUser(commands[4+i]), Double.parseDouble(commands[5+noOfUsers+i]));
        }
        return map;
    }
}
