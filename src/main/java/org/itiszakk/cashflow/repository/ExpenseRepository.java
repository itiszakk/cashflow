package org.itiszakk.cashflow.repository;

import org.itiszakk.cashflow.domain.expense.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Long> {

    List<ExpenseEntity> findByCreatedByLogin(String login);
}
