package com.example.expense_tracker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.expense_tracker.dto.ExpenseRequestDTO;
import com.example.expense_tracker.dto.ExpenseResponseDTO;
import com.example.expense_tracker.service.ExpenseService;


@RestController
@RequestMapping({"/api/expenses", "/api/expense"})
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public ResponseEntity<ExpenseResponseDTO> saveExpense(@RequestBody ExpenseRequestDTO requestDTO) {

        ExpenseResponseDTO response = expenseService.createExpense(requestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
