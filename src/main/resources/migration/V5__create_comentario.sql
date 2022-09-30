CREATE TABLE IF NOT EXISTS public.comentario
(
    id_comentario bigint NOT NULL,
    texto_comentario character varying(255) COLLATE pg_catalog."default",
    publicacao_id_publicacao bigint,
    usuario_id_usuario bigint,
    CONSTRAINT comentario_pkey PRIMARY KEY (id_comentario),
    CONSTRAINT fk44ijhj9rjoasdvolbtvkd8d6q FOREIGN KEY (publicacao_id_publicacao)
        REFERENCES public.publicacao (id_publicacao) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkbgrhjm7xjj8gymj98qj4jb0gb FOREIGN KEY (usuario_id_usuario)
        REFERENCES public.usuario (id_usuario) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
