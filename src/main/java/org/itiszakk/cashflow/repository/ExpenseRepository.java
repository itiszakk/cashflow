package org.itiszakk.cashflow.repository;

import org.itiszakk.cashflow.domain.expense.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Long> {

    
}
