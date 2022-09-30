CREATE TABLE IF NOT EXISTS public.publicacao
(
    id_publicacao bigint NOT NULL,
    descricao character varying(255) COLLATE pg_catalog."default",
    dh_inclusao timestamp without time zone,
    titulo character varying(255) COLLATE pg_catalog."default",
    id_usuario bigint NOT NULL,
    CONSTRAINT publicacao_pkey PRIMARY KEY (id_publicacao),
    CONSTRAINT id_usuario FOREIGN KEY (id_usuario)
        REFERENCES public.usuario (id_usuario) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)