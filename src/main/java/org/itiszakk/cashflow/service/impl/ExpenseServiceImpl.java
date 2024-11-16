package org.itiszakk.cashflow.service.impl;

import org.itiszakk.cashflow.domain.category.Category;
import org.itiszakk.cashflow.mapper.CategoryMapper;
import org.itiszakk.cashflow.service.CategoryService;
import org.itiszakk.cashflow.domain.expense.Expense;
import org.itiszakk.cashflow.domain.expense.ExpenseEntity;
import org.itiszakk.cashflow.domain.expense.ExpenseInput;
import org.itiszakk.cashflow.mapper.ExpenseMapper;
import org.itiszakk.cashflow.repository.ExpenseRepository;
import org.itiszakk.cashflow.service.ExpenseService;
import org.itiszakk.cashflow.domain.user.User;
import org.itiszakk.cashflow.mapper.UserMapper;
import org.itiszakk.cashflow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @Override
    public List<Expense> getAll() {
        return expenseRepository.findAll().stream()
                .map(ExpenseMapper::convert)
                .toList();
    }

    @Override
    public Expense upsert(ExpenseInput input) {

        User user = userService.ensureUser(input.getCreatedBy());
        Category category = categoryService.ensureCategory(input.getCategoryId());

        ExpenseEntity entity = ExpenseEntity.builder()
                .id(input.getId())
                .category(CategoryMapper.convert(category))
                .description(input.getDescription())
                .amount(input.getAmount())
                .date(input.getDateOrNow())
                .createdBy(UserMapper.convert(user))
                .build();

        expenseRepository.save(entity);

        return ExpenseMapper.convert(entity);
    }
}