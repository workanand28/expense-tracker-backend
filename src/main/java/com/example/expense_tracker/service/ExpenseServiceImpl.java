package com.example.expense_tracker.service;
import com.example.expense_tracker.exception.ExpenseNotFoundException;
import org.springframework.stereotype.Service;


import com.example.expense_tracker.dto.ExpenseResponseDTO;
import com.example.expense_tracker.dto.ExpenseRequestDTO;
import com.example.expense_tracker.entity.Expense;
import com.example.expense_tracker.repository.ExpenseRepository;


import java.time.LocalDate;


@Service
public class ExpenseServiceImpl implements ExpenseService {


    private final ExpenseRepository expenseRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public ExpenseResponseDTO createExpense(ExpenseRequestDTO requestDTO) {

        Expense expense = new Expense();

        expense.setAmount(requestDTO.getAmount());
        expense.setCategory(requestDTO.getCategory());
        expense.setDescription(requestDTO.getDescription());
        expense.setDate(LocalDate.now());

        Expense savedExpense = expenseRepository.save(expense);


        ExpenseResponseDTO responseDTO = new ExpenseResponseDTO();

        responseDTO.setId(savedExpense.getId());
        responseDTO.setAmount(savedExpense.getAmount());
        responseDTO.setCategory(savedExpense.getCategory());
        responseDTO.setDescription(savedExpense.getDescription());
        responseDTO.setDate(savedExpense.getDate());

        return responseDTO;

    }

    @Override
    public ExpenseResponseDTO getExpenseById(String id) {

        Expense expense = expenseRepository.findById(id)
                .orElseThrow( () -> new ExpenseNotFoundException("Expense with id: " + id + " not found!"));

        ExpenseResponseDTO responseDTO = new ExpenseResponseDTO();

        responseDTO.setId(expense.getId());
        responseDTO.setAmount(expense.getAmount());
        responseDTO.setCategory(expense.getCategory());
        responseDTO.setDescription(expense.getDescription());
        responseDTO.setDate(expense.getDate());

        return responseDTO;
    }
}
