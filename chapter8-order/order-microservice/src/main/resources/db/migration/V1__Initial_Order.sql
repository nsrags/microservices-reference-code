CREATE TABLE IF NOT EXISTS public.order_t
(
    order_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    order_desc character varying COLLATE pg_catalog."default",
    order_total double precision NOT NULL,
    shipping_total double precision NOT NULL,
    tax_total double precision NOT NULL,
    is_guest boolean,
    customer_id bigint,
    order_status character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT order_t_pkey PRIMARY KEY (order_id)
);
CREATE TABLE IF NOT EXISTS public.order_line_item_t
(
    line_item_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    order_id bigint NOT NULL,
    product_id character varying COLLATE pg_catalog."default" NOT NULL,
    sku_id character varying COLLATE pg_catalog."default" NOT NULL,
    quantity bigint NOT NULL,
    CONSTRAINT order_line_item_t_pkey PRIMARY KEY (line_item_id),
    CONSTRAINT order_id FOREIGN KEY (order_id)
        REFERENCES public.order_t (order_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);
CREATE TABLE IF NOT EXISTS public.payment_t
(
    payment_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    payment_method_type character varying COLLATE pg_catalog."default" NOT NULL,
    expiration_year character varying COLLATE pg_catalog."default" NOT NULL,
    expiration_month character varying COLLATE pg_catalog."default" NOT NULL,
    issue_number character varying COLLATE pg_catalog."default" NOT NULL,
    card_type character varying COLLATE pg_catalog."default" NOT NULL,
    order_id bigint,
    CONSTRAINT payment_t_pkey PRIMARY KEY (payment_id),
    CONSTRAINT order_id FOREIGN KEY (order_id)
        REFERENCES public.order_t (order_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);