package com.example.expense_tracker.exception;

import com.example.expense_tracker.entity.Expense;

public class ExpenseNotFoundException extends RuntimeException{

    public ExpenseNotFoundException(String message) {
        super(message);
    }
}
