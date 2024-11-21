package org.itiszakk.cashflow.util;

import org.itiszakk.cashflow.domain.category.Category;
import org.itiszakk.cashflow.domain.category.CategoryEntity;

public class CategoryUtils {

    public static Category convert(CategoryEntity source) {
        return Category.builder()
                .id(source.getId())
                .name(source.getName())
                .createdBy(UserUtils.convert(source.getCreatedBy()))
                .build();
    }

    public static CategoryEntity convert(Category source) {
        return CategoryEntity.builder()
                .id(source.getId())
                .name(source.getName())
                .createdBy(UserUtils.convert(source.getCreatedBy()))
                .build();
    }
}