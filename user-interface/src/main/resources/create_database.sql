--- Connect with postgres@localhost ---
CREATE DATABASE banque
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

REVOKE ALL ON DATABASE banque FROM public;