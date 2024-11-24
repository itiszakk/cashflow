package org.itiszakk.cashflow.controller;

import org.itiszakk.cashflow.domain.Category;
import org.itiszakk.cashflow.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @QueryMapping(value = "categoriesByUser")
    public List<Category> categories(@Argument(value = "login") String login) {
        return categoryService.getByUser(login);
    }

    @QueryMapping(value = "categories")
    public List<Category> categories() {
        return categoryService.getAll();
    }

    @QueryMapping(value = "category")
    public Category category(@Argument(value = "id") Long id) {
        return categoryService.getById(id);
    }

    @MutationMapping(value = "upsertCategory")
    public Category upsert(@Argument(value = "categoryInput") CategoryInput input) {
        return categoryService.upsert(input);
    }
}