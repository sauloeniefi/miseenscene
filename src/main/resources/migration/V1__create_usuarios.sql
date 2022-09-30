CREATE TABLE IF NOT EXISTS public.usuario
(
    id_usuario bigint NOT NULL,
    descricao character varying(255) COLLATE pg_catalog."default",
    dh_registro timestamp without time zone,
    email character varying(255) COLLATE pg_catalog."default",
    nome character varying(255) COLLATE pg_catalog."default",
    senha character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT usuario_pkey PRIMARY KEY (id_usuario)
    )