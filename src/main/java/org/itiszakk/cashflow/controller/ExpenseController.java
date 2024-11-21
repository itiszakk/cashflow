package org.itiszakk.cashflow.controller;

import org.itiszakk.cashflow.domain.expense.Expense;
import org.itiszakk.cashflow.domain.expense.ExpenseInput;
import org.itiszakk.cashflow.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @QueryMapping(value = "expenses")
    public List<Expense> expenses() {
        return expenseService.getAll();
    }

    @QueryMapping(value = "expense")
    public Expense expense(@Argument(value = "id") Long id) {
        return expenseService.getById(id);
    }

    @MutationMapping(value = "upsertExpense")
    public Expense upsert(@Argument(value = "expenseInput") ExpenseInput input) {
        return expenseService.upsert(input);
    }
}