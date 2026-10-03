CREATE TABLE usuarios (
                          id_usuario SERIAL PRIMARY KEY,
                          nombres VARCHAR(100) NOT NULL,
                          apellidos VARCHAR(100) NOT NULL,
                          dni VARCHAR(8) UNIQUE NOT NULL CHECK (dni ~ '^[0-9]{8}$'),
    email VARCHAR(100) UNIQUE NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    enabled BOOLEAN NOT NULL DEFAULT TRUE,
    last_sign_in TIMESTAMP,
    created_by INT REFERENCES usuarios(id_usuario),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by INT REFERENCES usuarios(id_usuario),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    clave VARCHAR(255) NOT NULL
);