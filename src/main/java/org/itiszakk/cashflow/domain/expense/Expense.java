package org.itiszakk.cashflow.domain.expense;

import lombok.Builder;
import lombok.Data;
import org.itiszakk.cashflow.domain.user.User;
import org.itiszakk.cashflow.domain.category.Category;

import java.time.OffsetDateTime;

@Data
@Builder
public class Expense {

    private final Long id;
    private final Category category;
    private final String description;
    private final Double amount;
    private final OffsetDateTime date;
    private final User createdBy;
}
