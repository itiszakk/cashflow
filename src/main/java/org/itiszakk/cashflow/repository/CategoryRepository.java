package org.itiszakk.cashflow.repository;

import org.itiszakk.cashflow.domain.category.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

}
