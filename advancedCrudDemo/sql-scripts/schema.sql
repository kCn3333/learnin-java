CREATE TABLE IF NOT EXISTS public.course
(
    id serial NOT NULL,
    title character varying(128) COLLATE pg_catalog."default",
    instructor_id integer,
    CONSTRAINT course_pkey PRIMARY KEY (id),
    CONSTRAINT course_title_key UNIQUE (title)
);

CREATE TABLE IF NOT EXISTS public.course_student
(
    course_id integer NOT NULL,
    student_id integer NOT NULL,
    CONSTRAINT course_student_pkey PRIMARY KEY (course_id, student_id)
);

CREATE TABLE IF NOT EXISTS public.instructor
(
    id serial NOT NULL,
    first_name character varying(45) COLLATE pg_catalog."default",
    last_name character varying(45) COLLATE pg_catalog."default",
    email character varying(45) COLLATE pg_catalog."default",
    instructor_detail_id integer,
    CONSTRAINT instructor_pkey PRIMARY KEY (id),
    CONSTRAINT instructor_instructor_detail_id_key UNIQUE (instructor_detail_id)
);

CREATE TABLE IF NOT EXISTS public.instructor_detail
(
    id serial NOT NULL,
    youtube_channel character varying(128) COLLATE pg_catalog."default",
    hobby character varying(45) COLLATE pg_catalog."default",
    CONSTRAINT instructor_detail_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.review
(
    id serial NOT NULL,
    comment character varying(256) COLLATE pg_catalog."default",
    course_id integer,
    CONSTRAINT review_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.student
(
    id serial NOT NULL,
    first_name character varying(45) COLLATE pg_catalog."default",
    last_name character varying(45) COLLATE pg_catalog."default",
    email character varying(45) COLLATE pg_catalog."default",
    CONSTRAINT student_pkey PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.course
    ADD CONSTRAINT fk_instructor FOREIGN KEY (instructor_id)
    REFERENCES public.instructor (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.course_student
    ADD CONSTRAINT fk_course_05 FOREIGN KEY (course_id)
    REFERENCES public.course (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.course_student
    ADD CONSTRAINT fk_student FOREIGN KEY (student_id)
    REFERENCES public.student (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.instructor
    ADD CONSTRAINT fk_instructor_detail FOREIGN KEY (instructor_detail_id)
    REFERENCES public.instructor_detail (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
CREATE INDEX IF NOT EXISTS instructor_instructor_detail_id_key
    ON public.instructor(instructor_detail_id);


ALTER TABLE IF EXISTS public.review
    ADD CONSTRAINT fk_course FOREIGN KEY (course_id)
    REFERENCES public.course (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

END;