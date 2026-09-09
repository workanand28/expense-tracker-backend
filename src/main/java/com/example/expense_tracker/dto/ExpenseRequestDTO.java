package com.example.expense_tracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class ExpenseRequestDTO {

    @Positive(message = "Amount must be greater than zero")
    private double amount;

    @NotBlank(message = "Category is required")
    private String category;


    @NotBlank(message = "Description is required")
    private String description;


    public ExpenseRequestDTO() {
    }

    public ExpenseRequestDTO(double amount, String category, String description) {
        this.amount = amount;
        this.category = category;
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
