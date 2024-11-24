package org.itiszakk.cashflow.util;

import org.itiszakk.cashflow.domain.Expense;
import org.itiszakk.cashflow.repository.ExpenseEntity;

public class ExpenseUtils {

    public static Expense convert(ExpenseEntity source) {
        return Expense.builder()
                .id(source.getId())
                .category(CategoryUtils.convert(source.getCategory()))
                .description(source.getDescription())
                .amount(source.getAmount())
                .date(source.getDate())
                .createdBy(UserUtils.convert(source.getCreatedBy()))
                .build();
    }

    public static ExpenseEntity convert(Expense source) {
        return ExpenseEntity.builder()
                .id(source.getId())
                .category(CategoryUtils.convert(source.getCategory()))
                .description(source.getDescription())
                .amount(source.getAmount())
                .date(source.getDate())
                .createdBy(UserUtils.convert(source.getCreatedBy()))
                .build();
    }
}
