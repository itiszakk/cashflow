package org.itiszakk.cashflow.controller;

import org.itiszakk.cashflow.domain.Expense;
import org.itiszakk.cashflow.service.ExpenseService;
import org.itiszakk.cashflow.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PreAuthorize("isAuthenticated()")
    @QueryMapping(value = "expenses")
    public List<Expense> expenses() {
        return expenseService.getByUser(SecurityUtils.getCurrentUser());
    }

    @PreAuthorize("isAuthenticated()")
    @MutationMapping(value = "upsertExpense")
    public Expense upsert(@Argument(value = "expenseInput") ExpenseInput input) {
        return expenseService.upsert(input);
    }
}