package org.itiszakk.cashflow.service.impl;

import org.itiszakk.cashflow.domain.Category;
import org.itiszakk.cashflow.domain.Expense;
import org.itiszakk.cashflow.repository.ExpenseEntity;
import org.itiszakk.cashflow.controller.ExpenseInput;
import org.itiszakk.cashflow.domain.User;
import org.itiszakk.cashflow.exception.impl.ExpenseNotFoundException;
import org.itiszakk.cashflow.repository.ExpenseRepository;
import org.itiszakk.cashflow.service.CategoryService;
import org.itiszakk.cashflow.service.ExpenseService;
import org.itiszakk.cashflow.service.UserService;
import org.itiszakk.cashflow.util.CategoryUtils;
import org.itiszakk.cashflow.util.ExpenseUtils;
import org.itiszakk.cashflow.util.UserUtils;
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
    public List<Expense> getByUser(String login) {
        return expenseRepository.findByCreatedByLogin(login).stream()
                .map(ExpenseUtils::convert)
                .toList();
    }

    @Override
    public List<Expense> getAll() {
        return expenseRepository.findAll().stream()
                .map(ExpenseUtils::convert)
                .toList();
    }

    @Override
    public Expense getById(Long id) {
        return expenseRepository.findById(id)
                .map(ExpenseUtils::convert)
                .orElseThrow(() -> new ExpenseNotFoundException(id));
    }

    @Override
    public Expense upsert(ExpenseInput input) {

        User user = userService.getByLogin(input.getCreatedBy());
        Category category = categoryService.getById(input.getCategoryId());

        ExpenseEntity entity = ExpenseEntity.builder()
                .id(input.getId())
                .category(CategoryUtils.convert(category))
                .description(input.getDescription())
                .amount(input.getAmount())
                .date(input.getDateOrNow())
                .createdBy(UserUtils.convert(user))
                .build();

        expenseRepository.save(entity);

        return ExpenseUtils.convert(entity);
    }
}