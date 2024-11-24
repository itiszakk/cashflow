package org.itiszakk.cashflow.controller;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
public class ExpenseInput {

    private final Long id;
    private final Long categoryId;
    private final String description;
    private final Double amount;
    private final OffsetDateTime date;
    private final String createdBy;

    public OffsetDateTime getDateOrNow() {
        return date == null ? OffsetDateTime.now() : date;
    }
}
