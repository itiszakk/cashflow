package org.itiszakk.cashflow.domain.category;

import lombok.Builder;
import lombok.Data;
import org.itiszakk.cashflow.domain.user.User;

@Data
@Builder
public class Category {

    private final Long id;
    private final String name;
    private final User createdBy;
}
