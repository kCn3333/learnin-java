CREATE TABLE IF NOT EXISTS public.employee
(
    id serial NOT NULL,
    first_name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    last_name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    email character varying(100) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT employee_pkey PRIMARY KEY (id),
    CONSTRAINT employee_email_key UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS public.members
(
    user_id character varying(50) COLLATE pg_catalog."default" NOT NULL,
    pw character(68) COLLATE pg_catalog."default" NOT NULL,
    active boolean NOT NULL,
    CONSTRAINT members_pkey PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS public.roles
(
    user_id character varying(50) COLLATE pg_catalog."default" NOT NULL,
    role character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT roles_pkey PRIMARY KEY (user_id, role)
);

ALTER TABLE IF EXISTS public.roles
    ADD CONSTRAINT roles_user_id_fkey FOREIGN KEY (user_id)
    REFERENCES public.members (user_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

END;