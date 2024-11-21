package org.itiszakk.cashflow.repository;

import org.itiszakk.cashflow.domain.category.CategoryEntity;
import org.itiszakk.cashflow.domain.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    List<CategoryEntity> findByCreatedByLogin(String login);
}
