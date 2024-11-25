package org.itiszakk.cashflow.service.impl;

import org.itiszakk.cashflow.controller.CategoryInput;
import org.itiszakk.cashflow.domain.Category;
import org.itiszakk.cashflow.repository.CategoryEntity;
import org.itiszakk.cashflow.repository.CategoryRepository;
import org.itiszakk.cashflow.repository.UserRepository;
import org.itiszakk.cashflow.service.CategoryService;
import org.itiszakk.cashflow.util.CategoryUtils;
import org.itiszakk.cashflow.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getByUser(String login) {
        return categoryRepository.findByCreatedByLogin(login).stream()
                .map(CategoryUtils::convert)
                .toList();
    }

    @Override
    public Category upsert(CategoryInput input) {

        CategoryEntity existing = input.getId() != null
                ? categoryRepository.getReferenceById(input.getId())
                : null;

        CategoryEntity entity = existing == null
                ? createNew(input)
                : createUpdated(input, existing);

        return CategoryUtils.convert(categoryRepository.save(entity));
    }

    private CategoryEntity createNew(CategoryInput input) {
        return CategoryEntity.builder()
                .name(input.getName())
                .createdBy(userRepository.getReferenceById(SecurityUtils.getCurrentUser()))
                .build();
    }

    private CategoryEntity createUpdated(CategoryInput input, CategoryEntity existing) {

        if (!hasAccessToCategory(existing)) {
            throw new RuntimeException("No access to category");
        }

        return CategoryEntity.builder()
                .id(input.getId())
                .name(input.getName())
                .createdBy(userRepository.getReferenceById(SecurityUtils.getCurrentUser()))
                .build();
    }

    private boolean hasAccessToCategory(CategoryEntity category) {
        return category.getCreatedBy().getLogin().equals(SecurityUtils.getCurrentUser());
    }
}