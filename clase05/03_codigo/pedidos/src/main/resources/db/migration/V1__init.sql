CREATE TABLE pedidos (
  id               VARCHAR(255) PRIMARY KEY,
  version          BIGINT                   NOT NULL,
  created_at       TIMESTAMP WITH TIME ZONE NULL     DEFAULT now(),
  last_modified_at TIMESTAMP WITH TIME ZONE NULL     DEFAULT now(),

  cliente_id       VARCHAR(255)             NOT NULL,
  importe          NUMERIC(15, 6)           NOT NULL DEFAULT 0,
  pagado           BOOLEAN                  NOT NULL DEFAULT FALSE
);


