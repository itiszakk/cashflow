package org.itiszakk.cashflow.repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.REMOVE)
    private List<ExpenseEntity> expenses;

    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.REMOVE)
    private List<CategoryEntity> categories;
}