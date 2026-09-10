package com.example.expense_tracker.service;

import com.example.expense_tracker.dto.ExpenseRequestDTO;
import com.example.expense_tracker.dto.ExpenseResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExpenseService {

    ExpenseResponseDTO createExpense(ExpenseRequestDTO requestDTO);

    ExpenseResponseDTO getExpenseById(String id);

    Page<ExpenseResponseDTO> getAllExpenses(Pageable pageable);
}
