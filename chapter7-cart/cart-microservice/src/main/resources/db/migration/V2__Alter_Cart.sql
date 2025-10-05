ALTER TABLE public.cart_t
ADD cart_total double precision,
ADD shipping_total double precision,
ADD tax_total double precision;

ALTER TABLE public.cart_line_item_t
ADD list_price double precision,
ADD sale_price double precision,
ADD product_n character varying COLLATE pg_catalog."default";
