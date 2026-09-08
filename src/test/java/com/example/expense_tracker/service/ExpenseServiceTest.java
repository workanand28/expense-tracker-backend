package com.example.expense_tracker.service;

import com.example.expense_tracker.dto.ExpenseRequestDTO;
import com.example.expense_tracker.dto.ExpenseResponseDTO;
import com.example.expense_tracker.entity.Expense;
import com.example.expense_tracker.repository.ExpenseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExpenseServiceTest {

    @Mock
    private ExpenseRepository expenseRepository;

    @InjectMocks
    private ExpenseServiceImpl expenseService;

    private ExpenseRequestDTO requestDTO;

    @BeforeEach
    void setUp() {
        requestDTO = new ExpenseRequestDTO(100.50, "Food", "Dinner with friends");
    }

    @Test
    void testCreateExpense_Success() {
        Expense savedExpense = new Expense("exp123", 100.50, "Food", "Dinner with friends", LocalDate.now());
        when(expenseRepository.save(any(Expense.class))).thenReturn(savedExpense);

        ExpenseResponseDTO response = expenseService.createExpense(requestDTO);

        assertNotNull(response);
        assertEquals("exp123", response.getId());
        assertEquals(100.50, response.getAmount());
        assertEquals("Food", response.getCategory());
        assertEquals("Dinner with friends", response.getDescription());
        assertEquals(LocalDate.now(), response.getDate());

        verify(expenseRepository, times(1)).save(any(Expense.class));
    }
}
