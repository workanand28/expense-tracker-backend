package com.example.expense_tracker.controller;

import com.example.expense_tracker.dto.ExpenseRequestDTO;
import com.example.expense_tracker.dto.ExpenseResponseDTO;
import com.example.expense_tracker.service.ExpenseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class ExpenseControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ExpenseService expenseService;

    @InjectMocks
    private ExpenseController expenseController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(expenseController).build();
    }

    @Test
    void testSaveExpense_Success() throws Exception {
        ExpenseResponseDTO responseDTO = new ExpenseResponseDTO("exp001", 50.0, "Transport", "Bus fare", LocalDate.of(2026, 9, 8));

        when(expenseService.createExpense(any(ExpenseRequestDTO.class))).thenReturn(responseDTO);

        String requestJson = """
                {
                    "amount": 50.0,
                    "category": "Transport",
                    "description": "Bus fare"
                }
                """;

        mockMvc.perform(post("/api/expenses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("exp001"))
                .andExpect(jsonPath("$.amount").value(50.0))
                .andExpect(jsonPath("$.category").value("Transport"))
                .andExpect(jsonPath("$.description").value("Bus fare"));
    }

    @Test
    void testSaveExpense_SingularEndpoint_Success() throws Exception {
        ExpenseResponseDTO responseDTO = new ExpenseResponseDTO("exp002", 25.0, "Books", "Study book", LocalDate.of(2026, 9, 8));

        when(expenseService.createExpense(any(ExpenseRequestDTO.class))).thenReturn(responseDTO);

        String requestJson = """
                {
                    "amount": 25.0,
                    "category": "Books",
                    "description": "Study book"
                }
                """;

        mockMvc.perform(post("/api/expense")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("exp002"))
                .andExpect(jsonPath("$.amount").value(25.0));
    }
}
