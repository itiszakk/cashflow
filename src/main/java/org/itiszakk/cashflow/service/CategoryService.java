package org.itiszakk.cashflow.service;

import org.itiszakk.cashflow.domain.category.Category;
import org.itiszakk.cashflow.domain.category.CategoryInput;

import java.util.List;

public interface CategoryService {

    List<Category> getAll();

    Category getById(Long id);

    Category upsert(CategoryInput input);
}