CREATE TABLE IF NOT EXISTS public.inventory_list_t
(
    inventory_list_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    list_n character varying COLLATE pg_catalog."default" NOT NULL,
    list_desc_t character varying COLLATE pg_catalog."default",
    is_active boolean NOT NULL,
    start_date date,
    end_date date,
    CONSTRAINT inventory_list_t_pkey PRIMARY KEY (inventory_list_id)
);
CREATE TABLE IF NOT EXISTS public.inventory_list_item_t
(
    list_item_i bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    sku_id character varying COLLATE pg_catalog."default",
    product_id character varying COLLATE pg_catalog."default",
    stock_level numeric,
    back_order_level numeric,
    pre_order_level numeric,
    is_active boolean,
    list_id bigint,
    CONSTRAINT inventory_list_item_t_pkey PRIMARY KEY (list_item_i),
    CONSTRAINT list_id FOREIGN KEY (list_id)
        REFERENCES public.inventory_list_t (inventory_list_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);