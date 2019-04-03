--
-- PostgreSQL database dump
--

-- Dumped from database version 10.7
-- Dumped by pg_dump version 11.2

-- Started on 2019-04-01 20:08:43

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 196 (class 1259 OID 16385)
-- Name: address; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.address (
    street character varying NOT NULL,
    city character varying NOT NULL,
    zip_code integer NOT NULL,
    address_id bigint NOT NULL,
    employee_id bigint
);


ALTER TABLE public.address OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 16577)
-- Name: address_address_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.address_address_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.address_address_id_seq OWNER TO postgres;

--
-- TOC entry 2184 (class 0 OID 0)
-- Dependencies: 199
-- Name: address_address_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.address_address_id_seq OWNED BY public.address.address_id;


--
-- TOC entry 198 (class 1259 OID 16397)
-- Name: employee; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.employee (
    name character varying NOT NULL,
    last_name character varying NOT NULL,
    phone_number character varying NOT NULL,
    sex character varying NOT NULL,
    email character varying NOT NULL,
    date_of_birth date NOT NULL,
    employee_id bigint NOT NULL
);


ALTER TABLE public.employee OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 16589)
-- Name: employee_employee_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.employee_employee_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.employee_employee_id_seq OWNER TO postgres;

--
-- TOC entry 2185 (class 0 OID 0)
-- Dependencies: 200
-- Name: employee_employee_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.employee_employee_id_seq OWNED BY public.employee.employee_id;


--
-- TOC entry 197 (class 1259 OID 16390)
-- Name: job; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.job (
    company_name character varying NOT NULL,
    start_date date NOT NULL,
    end_date date NOT NULL,
    "position" character varying NOT NULL,
    job_id bigint NOT NULL,
    employee_id bigint
);


ALTER TABLE public.job OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 16601)
-- Name: job_job_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.job_job_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.job_job_id_seq OWNER TO postgres;

--
-- TOC entry 2186 (class 0 OID 0)
-- Dependencies: 201
-- Name: job_job_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.job_job_id_seq OWNED BY public.job.job_id;


--
-- TOC entry 2039 (class 2604 OID 16579)
-- Name: address address_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.address ALTER COLUMN address_id SET DEFAULT nextval('public.address_address_id_seq'::regclass);


--
-- TOC entry 2041 (class 2604 OID 16591)
-- Name: employee employee_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee ALTER COLUMN employee_id SET DEFAULT nextval('public.employee_employee_id_seq'::regclass);


--
-- TOC entry 2040 (class 2604 OID 16603)
-- Name: job job_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.job ALTER COLUMN job_id SET DEFAULT nextval('public.job_job_id_seq'::regclass);


--
-- TOC entry 2173 (class 0 OID 16385)
-- Dependencies: 196
-- Data for Name: address; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.address (street, city, zip_code, address_id, employee_id) VALUES ('Holovna, 110', 'Chenivtsi', 58000, 2, 4);
INSERT INTO public.address (street, city, zip_code, address_id, employee_id) VALUES ('Franka, 54', 'Rivne', 43300, 3, 5);
INSERT INTO public.address (street, city, zip_code, address_id, employee_id) VALUES ('Nebesnoi Sotni ,4B', 'Chernivtsi', 58000, 43, 52);


--
-- TOC entry 2175 (class 0 OID 16397)
-- Dependencies: 198
-- Data for Name: employee; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.employee (name, last_name, phone_number, sex, email, date_of_birth, employee_id) VALUES ('Denys', 'Ohorodnik', '+380973999060', 'male', 'den.ohorodnik@gmail.com', '1999-06-04', 4);
INSERT INTO public.employee (name, last_name, phone_number, sex, email, date_of_birth, employee_id) VALUES ('Petro', 'Ivanov', '+380975737266', 'male', 'petro.iva@ukr.net', '2003-10-23', 5);
INSERT INTO public.employee (name, last_name, phone_number, sex, email, date_of_birth, employee_id) VALUES ('Denys', 'Ivanov', '+380973431062', 'male', 'den.ivanov@gmail.com', '2002-10-01', 52);


--
-- TOC entry 2174 (class 0 OID 16390)
-- Dependencies: 197
-- Data for Name: job; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.job (company_name, start_date, end_date, "position", job_id, employee_id) VALUES ('SoftServe', '2018-10-02', '2019-03-12', 'middle', 1, 4);
INSERT INTO public.job (company_name, start_date, end_date, "position", job_id, employee_id) VALUES ('InventorSoft', '2017-05-02', '2018-08-03', 'junior', 2, 4);
INSERT INTO public.job (company_name, start_date, end_date, "position", job_id, employee_id) VALUES ('SharpMinds', '2016-10-02', '2018-08-20', 'middle', 3, 5);
INSERT INTO public.job (company_name, start_date, end_date, "position", job_id, employee_id) VALUES ('SoftServe', '2010-02-10', '2012-02-10', 'junior', 47, 52);


--
-- TOC entry 2187 (class 0 OID 0)
-- Dependencies: 199
-- Name: address_address_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.address_address_id_seq', 55, true);


--
-- TOC entry 2188 (class 0 OID 0)
-- Dependencies: 200
-- Name: employee_employee_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.employee_employee_id_seq', 64, true);


--
-- TOC entry 2189 (class 0 OID 0)
-- Dependencies: 201
-- Name: job_job_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.job_job_id_seq', 60, true);


--
-- TOC entry 2043 (class 2606 OID 16581)
-- Name: address address_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.address
    ADD CONSTRAINT address_pkey PRIMARY KEY (address_id);


--
-- TOC entry 2049 (class 2606 OID 16593)
-- Name: employee employee_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT employee_pkey PRIMARY KEY (employee_id);


--
-- TOC entry 2047 (class 2606 OID 16605)
-- Name: job job_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.job
    ADD CONSTRAINT job_pkey PRIMARY KEY (job_id);


--
-- TOC entry 2044 (class 1259 OID 16659)
-- Name: fki_address_employee_fkey; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_address_employee_fkey ON public.address USING btree (employee_id);


--
-- TOC entry 2045 (class 1259 OID 16634)
-- Name: fki_jobs_employee_fkey; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_jobs_employee_fkey ON public.job USING btree (employee_id);


--
-- TOC entry 2050 (class 2606 OID 16665)
-- Name: address address_employee_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.address
    ADD CONSTRAINT address_employee_fkey FOREIGN KEY (employee_id) REFERENCES public.employee(employee_id) ON UPDATE CASCADE ON DELETE CASCADE DEFERRABLE;


--
-- TOC entry 2051 (class 2606 OID 16670)
-- Name: job jobs_employee_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.job
    ADD CONSTRAINT jobs_employee_fkey FOREIGN KEY (employee_id) REFERENCES public.employee(employee_id) ON UPDATE CASCADE ON DELETE CASCADE;


-- Completed on 2019-04-01 20:08:43

--
-- PostgreSQL database dump complete
--

