SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

CREATE SCHEMA patient_db;

SET default_table_access_method = heap;

CREATE TABLE patient_db.patient (
    p_id      integer NOT NULL PRIMARY KEY,
    firstname text    NOT NULL,
    surname   text    NOT NULL,
    email     text    NOT NULL,     
    UNIQUE (firstname, surname)
);

ALTER TABLE patient_db.patient ALTER COLUMN p_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME patient_db.patient_p_id
    START WITH 0
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1
);

CREATE TABLE patient_db.outbox (
    id UUID NOT NULL PRIMARY KEY,
    aggregate_type text NOT NULL,
    aggregate_id text NOT NULL,
    type text NOT NULL,
    payload text NOT NULL
);

ALTER TABLE patient_db.outbox OWNER TO postgres;