CREATE TABLE IF NOT EXISTS public.cart_t
(
    cart_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    cart_created_ts date NOT NULL,
    cart_created_by character varying COLLATE pg_catalog."default" NOT NULL,
    customer_id bigint NOT NULL,
    CONSTRAINT cart_t_pkey PRIMARY KEY (cart_id)
);
CREATE TABLE IF NOT EXISTS public.cart_line_item_t
(
    cart_line_item_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    product_id character varying COLLATE pg_catalog."default" NOT NULL,
    sku_id character varying COLLATE pg_catalog."default" NOT NULL,
    qty bigint,
    cart_id bigint NOT NULL,
    CONSTRAINT cart_line_item_t_pkey PRIMARY KEY (cart_line_item_id),
    CONSTRAINT cart_id FOREIGN KEY (cart_id)
        REFERENCES public.cart_t (cart_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS public.customer_t
(
    customer_id bigint NOT NULL,
    first_n character varying COLLATE pg_catalog."default" NOT NULL,
    last_n character varying COLLATE pg_catalog."default" NOT NULL,
    email_addr_t character varying COLLATE pg_catalog."default" NOT NULL,
    is_active boolean NOT NULL,
    CONSTRAINT customer_t_pkey PRIMARY KEY (customer_id)
);