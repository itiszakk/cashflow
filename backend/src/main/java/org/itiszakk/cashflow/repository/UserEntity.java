package org.itiszakk.cashflow.repository;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user", schema = "cashflow")
public class UserEntity {

    @Id
    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @ToString.Exclude
    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.REMOVE)
    private List<ExpenseEntity> expenses;

    @ToString.Exclude
    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.REMOVE)
    private List<CategoryEntity> categories;
}