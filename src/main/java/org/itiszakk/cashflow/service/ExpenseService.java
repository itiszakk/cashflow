package org.itiszakk.cashflow.service;

import org.itiszakk.cashflow.domain.expense.Expense;
import org.itiszakk.cashflow.domain.expense.ExpenseInput;

import java.util.List;

public interface ExpenseService {

    List<Expense> getByUser(String login);

    List<Expense> getAll();

    Expense getById(Long id);

    Expense upsert(ExpenseInput input);
}
