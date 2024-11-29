package org.itiszakk.cashflow.repository;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category", schema = "cashflow")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "created_by")
    private UserEntity createdBy;
}