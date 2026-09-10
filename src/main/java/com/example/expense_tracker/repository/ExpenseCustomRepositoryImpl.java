package com.example.expense_tracker.repository;



import java.util.ArrayList;
import java.util.List;

import com.example.expense_tracker.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;



@Repository
public class ExpenseCustomRepositoryImpl implements ExpenseCustomRepository {

    private final MongoTemplate mongoTemplate;

    public ExpenseCustomRepositoryImpl(
            MongoTemplate mongoTemplate) {

        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Page<Expense> searchExpenses(
            String category,
            Double minAmount,
            Double maxAmount,
            String search,
            Pageable pageable) {

        Query query = new Query();

        List<Criteria> criteriaList = new ArrayList<>();

        if (category != null && !category.isBlank()) {
            criteriaList.add(Criteria.where("category").is(category)
            );
        }

        if (minAmount != null) {
            criteriaList.add(Criteria.where("amount").gte(minAmount)
            );
        }

        if (maxAmount != null) {
            criteriaList.add(Criteria.where("amount").lte(maxAmount)
            );
        }

        if (search != null && !search.isBlank()) {
            criteriaList.add(Criteria.where("description").regex(search, "i")
            );
        }

        if (!criteriaList.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criteriaList)
            );
        }

        long total = mongoTemplate.count(query, Expense.class);

        query.with(pageable);

        List<Expense> expenses = mongoTemplate.find(query, Expense.class);

        return new PageImpl<>(expenses, pageable, total);
    }
}