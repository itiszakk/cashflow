package org.itiszakk.cashflow.util;

import org.itiszakk.cashflow.domain.Category;
import org.itiszakk.cashflow.repository.CategoryEntity;

public class CategoryUtils {

    public static Category convert(CategoryEntity source) {
        return Category.builder()
                .id(source.getId())
                .name(source.getName())
                .createdBy(source.getName())
                .build();
    }
}