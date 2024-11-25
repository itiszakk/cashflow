package org.itiszakk.cashflow.service;

import org.itiszakk.cashflow.controller.CategoryInput;
import org.itiszakk.cashflow.domain.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getByUser(String login);

    Category upsert(CategoryInput input);
}