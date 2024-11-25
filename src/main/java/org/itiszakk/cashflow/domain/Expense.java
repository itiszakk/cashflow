package org.itiszakk.cashflow.domain;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
public class Expense {

    private final Long id;
    private final Long categoryId;
    private final String description;
    private final Double amount;
    private final OffsetDateTime date;
    private final String createdBy;
}
