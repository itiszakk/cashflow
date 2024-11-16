package org.itiszakk.cashflow.service;

import org.itiszakk.cashflow.domain.category.Category;
import org.itiszakk.cashflow.domain.category.CategoryInput;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> getAll();

    Optional<Category> getById(Long id);

    Category ensureCategory(Long id);

    Category upsert(CategoryInput input);
}