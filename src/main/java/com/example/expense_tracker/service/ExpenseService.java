package com.example.expense_tracker.service;

import com.example.expense_tracker.dto.ExpenseRequestDTO;
import com.example.expense_tracker.dto.ExpenseResponseDTO;

public interface ExpenseService {

    ExpenseResponseDTO createExpense(ExpenseRequestDTO requestDTO);
}
