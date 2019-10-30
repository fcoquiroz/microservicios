CREATE TABLE clientes (
  id               VARCHAR(255) PRIMARY KEY,
  version          BIGINT              NOT NULL,
  first_name       VARCHAR(255)        NOT NULL,
  last_name        VARCHAR(255)        NOT NULL,
  tax_id           VARCHAR(255)        NOT NULL,
  email            VARCHAR(255) UNIQUE NOT NULL,
  credit_threshold NUMERIC(15, 6)      NOT NULL DEFAULT 0,
  verified         BOOLEAN             NOT NULL DEFAULT FALSE
);