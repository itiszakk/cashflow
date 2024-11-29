package org.itiszakk.cashflow.service.impl;

import org.itiszakk.cashflow.controller.ExpenseInput;
import org.itiszakk.cashflow.domain.Expense;
import org.itiszakk.cashflow.repository.CategoryRepository;
import org.itiszakk.cashflow.repository.ExpenseEntity;
import org.itiszakk.cashflow.repository.ExpenseRepository;
import org.itiszakk.cashflow.repository.UserRepository;
import org.itiszakk.cashflow.service.ExpenseService;
import org.itiszakk.cashflow.util.ExpenseUtils;
import org.itiszakk.cashflow.util.SecurityUtils;
import org.itiszakk.cashflow.util.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Expense> getByUser(String login) {
        return expenseRepository.findByCreatedByLogin(login).stream()
                .map(ExpenseUtils::convert)
                .toList();
    }

    @Override
    public Expense upsert(ExpenseInput input) {

        ExpenseEntity existing = input.getId() != null
                ? expenseRepository.getReferenceById(input.getId())
                : null;

        ExpenseEntity entity = existing == null
                ? createNew(input)
                : createUpdated(input, existing);

        return ExpenseUtils.convert(expenseRepository.save(entity));
    }

    private ExpenseEntity createNew(ExpenseInput input) {
        return ExpenseEntity.builder()
                .category(categoryRepository.getReferenceById(input.getCategoryId()))
                .description(input.getDescription())
                .amount(input.getAmount())
                .date(TimeUtils.getOrNow(input.getDate()))
                .createdBy(userRepository.getReferenceById(SecurityUtils.getCurrentUser()))
                .build();
    }

    private ExpenseEntity createUpdated(ExpenseInput input, ExpenseEntity existing) {

        if (!hasAccessToExpense(existing)) {
            throw new RuntimeException("No access to expense");
        }

        return ExpenseEntity.builder()
                .id(input.getId())
                .category(categoryRepository.getReferenceById(input.getCategoryId()))
                .description(input.getDescription())
                .amount(input.getAmount())
                .date(TimeUtils.getOrNow(input.getDate()))
                .createdBy(userRepository.getReferenceById(SecurityUtils.getCurrentUser()))
                .build();
    }

    private boolean hasAccessToExpense(ExpenseEntity expense) {
        return expense.getCreatedBy().getLogin().equals(SecurityUtils.getCurrentUser());
    }
}