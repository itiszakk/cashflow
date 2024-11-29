package org.itiszakk.cashflow.controller;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryInput {

    private final Long id;
    private final String name;
}
