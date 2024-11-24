package org.itiszakk.cashflow.service;

import org.itiszakk.cashflow.domain.Category;
import org.itiszakk.cashflow.controller.CategoryInput;

import java.util.List;

public interface CategoryService {

    List<Category> getByUser(String login);

    List<Category> getAll();

    Category getById(Long id);

    Category upsert(CategoryInput input);
}