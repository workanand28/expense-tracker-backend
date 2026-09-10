package com.example.expense_tracker.repository;

import com.example.expense_tracker.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface ExpenseCustomRepository {

    Page<Expense> searchExpenses(
            String category,
            Double minAmount,
            Double maxAmount,
            String search,
            Pageable pageable);
}
