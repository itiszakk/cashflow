CREATE SCHEMA IF NOT EXISTS cashflow;

CREATE TABLE IF NOT EXISTS cashflow.user (
    login VARCHAR(255) PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS cashflow.category (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    created_by VARCHAR(255) REFERENCES cashflow.user(login) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS cashflow.expense (
    id BIGSERIAL PRIMARY KEY,
    category_id BIGINT REFERENCES cashflow.category(id) ON DELETE CASCADE,
    description VARCHAR(255),
    amount DOUBLE PRECISION NOT NULL,
    date TIMESTAMP WITH TIME ZONE,
    created_by VARCHAR(255) REFERENCES cashflow.user(login) ON DELETE CASCADE
);