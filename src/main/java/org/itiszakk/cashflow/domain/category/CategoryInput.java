package org.itiszakk.cashflow.domain.category;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryInput {

    private final Long id;
    private final String name;
    private final String createdBy;
}
