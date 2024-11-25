package org.itiszakk.cashflow.controller;

import org.itiszakk.cashflow.domain.Category;
import org.itiszakk.cashflow.service.CategoryService;
import org.itiszakk.cashflow.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PreAuthorize("isAuthenticated()")
    @QueryMapping(value = "categories")
    public List<Category> categories() {
        return categoryService.getByUser(SecurityUtils.getCurrentUser());
    }

    @PreAuthorize("isAuthenticated()")
    @MutationMapping(value = "upsertCategory")
    public Category upsert(@Argument(value = "categoryInput") CategoryInput input) {
        return categoryService.upsert(input);
    }
}