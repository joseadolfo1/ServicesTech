CREATE TABLE roles (
                       id_rol SERIAL PRIMARY KEY,
                       nombre VARCHAR(100) UNIQUE,
                       descripcion VARCHAR(100)
);