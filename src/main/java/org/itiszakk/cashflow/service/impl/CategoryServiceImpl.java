package org.itiszakk.cashflow.service.impl;

import org.itiszakk.cashflow.domain.Category;
import org.itiszakk.cashflow.repository.CategoryEntity;
import org.itiszakk.cashflow.controller.CategoryInput;
import org.itiszakk.cashflow.domain.User;
import org.itiszakk.cashflow.exception.impl.CategoryNotFoundException;
import org.itiszakk.cashflow.util.CategoryUtils;
import org.itiszakk.cashflow.util.UserUtils;
import org.itiszakk.cashflow.repository.CategoryRepository;
import org.itiszakk.cashflow.service.CategoryService;
import org.itiszakk.cashflow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getByUser(String login) {
        return categoryRepository.findByCreatedByLogin(login).stream()
                .map(CategoryUtils::convert)
                .toList();
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll().stream()
                .map(CategoryUtils::convert)
                .toList();
    }

    @Override
    public Category getById(Long id) {
        return categoryRepository.findById(id)
                .map(CategoryUtils::convert)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    @Override
    public Category upsert(CategoryInput input) {

        User user = userService.getByLogin(input.getCreatedBy());

        CategoryEntity entity = CategoryEntity.builder()
                .id(input.getId())
                .name(input.getName())
                .createdBy(UserUtils.convert(user))
                .build();

        categoryRepository.save(entity);

        return CategoryUtils.convert(entity);
    }
}