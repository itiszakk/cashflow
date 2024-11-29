package org.itiszakk.cashflow.util;

import org.itiszakk.cashflow.domain.Expense;
import org.itiszakk.cashflow.repository.ExpenseEntity;

public class ExpenseUtils {

    public static Expense convert(ExpenseEntity source) {
        return Expense.builder()
                .id(source.getId())
                .categoryId(source.getCategory().getId())
                .description(source.getDescription())
                .amount(source.getAmount())
                .date(source.getDate())
                .createdBy(source.getCreatedBy().getLogin())
                .build();
    }
}
