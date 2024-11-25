package org.itiszakk.cashflow.repository;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "expense", schema = "cashflow")
public class ExpenseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @Column(name = "description")
    private String description;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "date")
    private OffsetDateTime date;

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "created_by")
    private UserEntity createdBy;
}
