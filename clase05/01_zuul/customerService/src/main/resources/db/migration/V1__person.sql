CREATE TABLE customers (
  id      VARCHAR(255) PRIMARY KEY,
  version BIGINT       NOT NULL,
  name    VARCHAR(255) NOT NULL,
  email   VARCHAR(255) NOT NULL
);