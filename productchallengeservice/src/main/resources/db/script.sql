CREATE TABLE public.users (
	id bigserial NOT NULL,
	create_at timestamp(6) NULL,
	email varchar(255) NULL,
	"password" varchar(255) NULL,
	update_at timestamp(6) NULL,
	user_name varchar(255) NULL,
	CONSTRAINT users_pkey PRIMARY KEY (id)
);

CREATE TABLE public.roles (
	id bigserial NOT NULL,
	"name" varchar(255) NULL,
	CONSTRAINT roles_pkey PRIMARY KEY (id)
);

CREATE TABLE public.users_roles (
	user_model_id int8 NOT NULL,
	roles_id int8 NOT NULL
);

ALTER TABLE public.users_roles ADD CONSTRAINT fka62j07k5mhgifpp955h37ponj FOREIGN KEY (roles_id) REFERENCES public.roles(id);
ALTER TABLE public.users_roles ADD CONSTRAINT fkdy8vvfurjq4afobw967mbyj1 FOREIGN KEY (user_model_id) REFERENCES public.users(id);