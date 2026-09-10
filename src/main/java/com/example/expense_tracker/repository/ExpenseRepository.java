package com.example.expense_tracker.repository;
import com.example.expense_tracker.entity.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ExpenseRepository
        extends MongoRepository<Expense, String>,
        ExpenseCustomRepository {

    List<Expense> findByCategory(String category);
}
