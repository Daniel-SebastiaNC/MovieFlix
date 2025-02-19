CREATE TABLE tb_movie (
    id serial PRIMARY KEY,
    title varchar(255) NOT NULL,
    description text,
    release_date date,
    rating numeric,
    created_at timestamp,
    updated_at timestamp
);