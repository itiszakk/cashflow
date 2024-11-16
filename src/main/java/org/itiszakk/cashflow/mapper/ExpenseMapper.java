package org.itiszakk.cashflow.mapper;

import org.itiszakk.cashflow.domain.expense.Expense;
import org.itiszakk.cashflow.domain.expense.ExpenseEntity;

public class ExpenseMapper {

    public static Expense convert(ExpenseEntity source) {
        return Expense.builder()
                .id(source.getId())
                .category(CategoryMapper.convert(source.getCategory()))
                .description(source.getDescription())
                .amount(source.getAmount())
                .date(source.getDate())
                .createdBy(UserMapper.convert(source.getCreatedBy()))
                .build();
    }

    public static ExpenseEntity convert(Expense source) {
        return ExpenseEntity.builder()
                .id(source.getId())
                .category(CategoryMapper.convert(source.getCategory()))
                .description(source.getDescription())
                .amount(source.getAmount())
                .date(source.getDate())
                .createdBy(UserMapper.convert(source.getCreatedBy()))
                .build();
    }
}
