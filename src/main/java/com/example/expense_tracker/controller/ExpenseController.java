package com.example.expense_tracker.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.expense_tracker.dto.ExpenseRequestDTO;
import com.example.expense_tracker.dto.ExpenseResponseDTO;
import com.example.expense_tracker.service.ExpenseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


@RestController
@RequestMapping({"/api/expenses", "/api/expense"})
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public ResponseEntity<ExpenseResponseDTO> saveExpense(@Valid @RequestBody ExpenseRequestDTO requestDTO) {

        ExpenseResponseDTO response = expenseService.createExpense(requestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponseDTO> getExpenseById(@PathVariable String id) {

        ExpenseResponseDTO response = expenseService.getExpenseById(id);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<ExpenseResponseDTO>> getAllExpenses(
            Pageable pageable) {

        Page<ExpenseResponseDTO> response =
                expenseService.getAllExpenses(pageable);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ExpenseResponseDTO>> getExpensesByCategory(@PathVariable String category) {
        List<ExpenseResponseDTO> response = expenseService.getExpensesByCategory(category);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ExpenseResponseDTO>> searchExpenses(

            @RequestParam(required = false)
            String category,

            @RequestParam(required = false)
            Double minAmount,

            @RequestParam(required = false)
            Double maxAmount,

            @RequestParam(required = false)
            String search,

            Pageable pageable) {

        Page<ExpenseResponseDTO> response =
                expenseService.searchExpenses(
                        category,
                        minAmount,
                        maxAmount,
                        search,
                        pageable
                );

        return ResponseEntity.ok(response);
    }
}
