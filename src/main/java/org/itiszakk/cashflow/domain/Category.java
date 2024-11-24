package org.itiszakk.cashflow.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Category {

    private final Long id;
    private final String name;
    private final User createdBy;
}
