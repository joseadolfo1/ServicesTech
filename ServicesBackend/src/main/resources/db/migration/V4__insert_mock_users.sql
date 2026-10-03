-- 1. Insertar los 3 roles del sistema
INSERT INTO roles (id_rol, nombre, descripcion) VALUES
                                                    (1, 'ROLE_CLIENTE', 'Usuario que solicita los servicios'),
                                                    (2, 'ROLE_TECNICO', 'Personal encargado de realizar los servicios'),
                                                    (3, 'ROLE_ADMINISTRADOR', 'Administrador general del sistema')
    ON CONFLICT (id_rol) DO NOTHING;

-- 2. Insertar usuarios de prueba (Clave: 123456 encriptada con BCrypt)
-- Los primeros admins se autoregistran (created_by NULL)
INSERT INTO usuarios (id_usuario, nombres, apellidos, dni, email, telefono, enabled, clave) VALUES
                                                                                                (1, 'Carlos', 'Mendoza', '12345678', 'admin1@test.com', '987654321', true, '$2a$10$5j26qGHjE6qBpqo9UefKx.tFl04kxXfKUWu2eW5Bt5szQDgsOuy8W'),
                                                                                                (2, 'Ana', 'Torres', '87654321', 'admin2@test.com', '912345678', true, '$2a$10$5j26qGHjE6qBpqo9UefKx.tFl04kxXfKUWu2eW5Bt5szQDgsOuy8W'),
                                                                                                (3, 'Luis', 'Gomez', '11223344', 'tecnico1@test.com', '999888777', true, '$2a$10$5j26qGHjE6qBpqo9UefKx.tFl04kxXfKUWu2eW5Bt5szQDgsOuy8W'),
                                                                                                (4, 'Maria', 'Rojas', '44332211', 'tecnico2@test.com', '988777666', true, '$2a$10$5j26qGHjE6qBpqo9UefKx.tFl04kxXfKUWu2eW5Bt5szQDgsOuy8W'),
                                                                                                (5, 'Jorge', 'Perez', '55667788', 'cliente1@test.com', '977666555', true, '$2a$10$5j26qGHjE6qBpqo9UefKx.tFl04kxXfKUWu2eW5Bt5szQDgsOuy8W'),
                                                                                                (6, 'Laura', 'Silva', '88776655', 'cliente2@test.com', '966555444', true, '$2a$10$5j26qGHjE6qBpqo9UefKx.tFl04kxXfKUWu2eW5Bt5szQDgsOuy8W'),
                                                                                                (7, 'Roberto', 'Sanchez', '77889900', 'tecnico_admin@test.com', '955443322', true, ''),
                                                                                                (8, 'Sofia', 'Ramirez', '99887766', 'super_user@test.com', '944332211', true, '$2a$10$5j26qGHjE6qBpqo9UefKx.tFl04kxXfKUWu2eW5Bt5szQDgsOuy8W')
    ON CONFLICT (id_usuario) DO NOTHING;

-- 3. Asignación de Roles en la tabla intermedia
-- Administradores (1 y 2)
INSERT INTO usuario_rol (id_usuario, id_rol) VALUES
                                                 (1, 3), (2, 3)
    ON CONFLICT DO NOTHING;

-- Técnicos (3 y 4)
INSERT INTO usuario_rol (id_usuario, id_rol) VALUES
                                                 (3, 2), (4, 2)
    ON CONFLICT DO NOTHING;

-- Clientes (5 y 6)
INSERT INTO usuario_rol (id_usuario, id_rol) VALUES
                                                 (5, 1), (6, 1)
    ON CONFLICT DO NOTHING;

-- Usuario 7: Técnico y Administrador
INSERT INTO usuario_rol (id_usuario, id_rol) VALUES
                                                 (7, 2), (7, 3)
    ON CONFLICT DO NOTHING;

-- Usuario 8: Cliente, Técnico y Administrador
INSERT INTO usuario_rol (id_usuario, id_rol) VALUES
                                                 (8, 1), (8, 2), (8, 3)
    ON CONFLICT DO NOTHING;