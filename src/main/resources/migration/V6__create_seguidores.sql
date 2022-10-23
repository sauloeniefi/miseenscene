-- Table: public.seguidores

-- DROP TABLE IF EXISTS public.seguidores;

CREATE TABLE IF NOT EXISTS public.seguidores
(
    id_seguidores bigint NOT NULL,
    id_usuario bigint,
    id_usuario_seguidor bigint,
    CONSTRAINT seguidores_pkey PRIMARY KEY (id_seguidores)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.seguidores
    OWNER to postgres;