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

CREATE SCHEMA prototyp_db;

SET default_table_access_method = heap;

CREATE TABLE prototyp_db.patient (
    p_id      integer NOT NULL PRIMARY KEY,
    firstname text    NOT NULL,
    surname   text    NOT NULL,
    UNIQUE (firstname, surname)
);

ALTER TABLE prototyp_db.patient ALTER COLUMN p_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME prototyp_db.patient_p_id
    START WITH 0
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1
);

CREATE TABLE prototyp_db.feedback (
    f_id      integer NOT NULL PRIMARY KEY,
    p_id      integer NOT NULL,
    dia_id    integer NOT NULL,
    feedback  text    NOT NULL,
    UNIQUE (dia_id, p_id)
);

ALTER TABLE prototyp_db.feedback ALTER COLUMN f_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME prototyp_db.feedback_f_id
    START WITH 0
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1
);

CREATE TABLE prototyp_db.doctor (
    d_id      integer NOT NULL PRIMARY KEY,
    firstname text    NOT NULL,
    surname   text    NOT NULL,
    UNIQUE (firstname, surname)
);

ALTER TABLE prototyp_db.doctor ALTER COLUMN d_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME prototyp_db.doctor_p_id
    START WITH 0
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1
);

CREATE TABLE prototyp_db.diagnosis (
    dia_id      integer NOT NULL PRIMARY KEY,
    p_id        integer NOT NULL,
    d_id        integer NOT NULL,
    diagnosis   text    NOT NULL
);

ALTER TABLE prototyp_db.diagnosis ALTER COLUMN dia_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME prototyp_db.diagnosis_dia_id
    START WITH 0
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1
);

ALTER TABLE ONLY prototyp_db.diagnosis
    ADD CONSTRAINT diagnosis_p_id_fkey FOREIGN KEY (p_id) REFERENCES prototyp_db.patient(p_id) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE ONLY prototyp_db.diagnosis
    ADD CONSTRAINT diagnosis_d_id_fkey FOREIGN KEY (d_id) REFERENCES prototyp_db.doctor(d_id) ON DELETE CASCADE ON UPDATE CASCADE;