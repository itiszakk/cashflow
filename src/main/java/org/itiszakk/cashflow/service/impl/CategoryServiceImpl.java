package org.itiszakk.cashflow.service.impl;

import org.itiszakk.cashflow.domain.category.Category;
import org.itiszakk.cashflow.domain.category.CategoryEntity;
import org.itiszakk.cashflow.domain.category.CategoryInput;
import org.itiszakk.cashflow.mapper.CategoryMapper;
import org.itiszakk.cashflow.repository.CategoryRepository;
import org.itiszakk.cashflow.service.CategoryService;
import org.itiszakk.cashflow.exception.impl.CategoryNotFoundException;
import org.itiszakk.cashflow.domain.user.User;
import org.itiszakk.cashflow.mapper.UserMapper;
import org.itiszakk.cashflow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll().stream()
                .map(CategoryMapper::convert)
                .toList();
    }

    @Override
    public Optional<Category> getById(Long id) {
        return categoryRepository.findById(id)
                .map(CategoryMapper::convert);
    }

    @Override
    public Category ensureCategory(Long id) {
        return getById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    @Override
    public Category upsert(CategoryInput input) {

        User user = userService.ensureUser(input.getCreatedBy());

        CategoryEntity entity = CategoryEntity.builder()
                .id(input.getId())
                .name(input.getName())
                .createdBy(UserMapper.convert(user))
                .build();

        categoryRepository.save(entity);

        return CategoryMapper.convert(entity);
    }
}