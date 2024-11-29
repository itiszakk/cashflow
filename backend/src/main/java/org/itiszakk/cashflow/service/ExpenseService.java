package org.itiszakk.cashflow.service;

import org.itiszakk.cashflow.controller.ExpenseInput;
import org.itiszakk.cashflow.domain.Expense;

import java.util.List;

public interface ExpenseService {

    List<Expense> getByUser(String login);

    Expense upsert(ExpenseInput input);
}
