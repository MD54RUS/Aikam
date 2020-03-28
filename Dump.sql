--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2
-- Dumped by pg_dump version 12.2

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

SET default_table_access_method = "heap";

--
-- Name: CUSTOMER; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE "public"."CUSTOMER" (
    "ID" bigint NOT NULL,
    "NAME" "text",
    "LASTNAME" "text"
);


--
-- Name: CUSTOMER_ID_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE "public"."CUSTOMER_ID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: CUSTOMER_ID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE "public"."CUSTOMER_ID_seq" OWNED BY "public"."CUSTOMER"."ID";


--
-- Name: GOODS; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE "public"."GOODS" (
    "ID" bigint NOT NULL,
    "NAME" "text",
    "PRICE" integer
);


--
-- Name: GOODS_ID_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE "public"."GOODS_ID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: GOODS_ID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE "public"."GOODS_ID_seq" OWNED BY "public"."GOODS"."ID";


--
-- Name: PURCHASES; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE "public"."PURCHASES" (
    "ID" bigint NOT NULL,
    "CUSTOMER_ID" bigint NOT NULL,
    "GOODS_ID" bigint NOT NULL,
    "DATE" "date" NOT NULL
);


--
-- Name: PURCHASES_ID_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE "public"."PURCHASES_ID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: PURCHASES_ID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE "public"."PURCHASES_ID_seq" OWNED BY "public"."PURCHASES"."ID";


--
-- Name: CUSTOMER ID; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY "public"."CUSTOMER" ALTER COLUMN "ID" SET DEFAULT "nextval"('"public"."CUSTOMER_ID_seq"'::"regclass");


--
-- Name: GOODS ID; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY "public"."GOODS" ALTER COLUMN "ID" SET DEFAULT "nextval"('"public"."GOODS_ID_seq"'::"regclass");


--
-- Name: PURCHASES ID; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY "public"."PURCHASES" ALTER COLUMN "ID" SET DEFAULT "nextval"('"public"."PURCHASES_ID_seq"'::"regclass");


--
-- Data for Name: CUSTOMER; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO "public"."CUSTOMER" ("ID", "NAME", "LASTNAME") VALUES (1, 'Иван', 'Иванов');
INSERT INTO "public"."CUSTOMER" ("ID", "NAME", "LASTNAME") VALUES (2, 'Петр', 'Петров');
INSERT INTO "public"."CUSTOMER" ("ID", "NAME", "LASTNAME") VALUES (4, 'Сидор', 'Сидоров');


--
-- Data for Name: GOODS; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO "public"."GOODS" ("ID", "NAME", "PRICE") VALUES (1, 'apple', 12);
INSERT INTO "public"."GOODS" ("ID", "NAME", "PRICE") VALUES (2, 'milk', 45);


--
-- Data for Name: PURCHASES; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO "public"."PURCHASES" ("ID", "CUSTOMER_ID", "GOODS_ID", "DATE") VALUES (1, 1, 1, '2001-01-01');
INSERT INTO "public"."PURCHASES" ("ID", "CUSTOMER_ID", "GOODS_ID", "DATE") VALUES (2, 2, 1, '2002-02-02');
INSERT INTO "public"."PURCHASES" ("ID", "CUSTOMER_ID", "GOODS_ID", "DATE") VALUES (3, 1, 1, '2001-01-02');
INSERT INTO "public"."PURCHASES" ("ID", "CUSTOMER_ID", "GOODS_ID", "DATE") VALUES (7, 1, 2, '2001-01-01');
INSERT INTO "public"."PURCHASES" ("ID", "CUSTOMER_ID", "GOODS_ID", "DATE") VALUES (5, 4, 2, '2002-02-02');
INSERT INTO "public"."PURCHASES" ("ID", "CUSTOMER_ID", "GOODS_ID", "DATE") VALUES (6, 4, 1, '2001-01-01');


--
-- Name: CUSTOMER_ID_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('"public"."CUSTOMER_ID_seq"', 4, true);


--
-- Name: GOODS_ID_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('"public"."GOODS_ID_seq"', 2, true);


--
-- Name: PURCHASES_ID_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('"public"."PURCHASES_ID_seq"', 7, true);


--
-- Name: CUSTOMER CUSTOMER_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY "public"."CUSTOMER"
    ADD CONSTRAINT "CUSTOMER_pkey" PRIMARY KEY ("ID");


--
-- Name: GOODS GOODS_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY "public"."GOODS"
    ADD CONSTRAINT "GOODS_pkey" PRIMARY KEY ("ID");


--
-- Name: PURCHASES PURCHASES_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY "public"."PURCHASES"
    ADD CONSTRAINT "PURCHASES_pkey" PRIMARY KEY ("ID");


--
-- PostgreSQL database dump complete
--

