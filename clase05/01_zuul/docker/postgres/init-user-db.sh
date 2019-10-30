#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
    CREATE USER c7 PASSWORD 'c7';
    CREATE DATABASE c7_data_customer OWNER c7;
    GRANT ALL PRIVILEGES ON DATABASE c7_data_customer TO c7;

    CREATE DATABASE c7_data_orders OWNER c7;
    GRANT ALL PRIVILEGES ON DATABASE c7_data_orders TO c7;
EOSQL