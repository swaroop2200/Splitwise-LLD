package model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Expense {
    private String id;
    private double amount;
    private User paidBy;
    private List<Split> splits;
    private ExpenseMetadata metadata;
    private ExpenseType expenseType;

    public Expense(double amount, User paidBy, List<Split> splits, ExpenseMetadata metadata, ExpenseType expenseType) {
        this.amount = amount;
        this.paidBy = paidBy;
        this.splits = splits;
        this.metadata = metadata;
        this.expenseType = expenseType;
    }
}
