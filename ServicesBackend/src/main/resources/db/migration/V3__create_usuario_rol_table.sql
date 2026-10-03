CREATE TABLE usuario_rol (
                             id_rol INT REFERENCES roles(id_rol) ON UPDATE CASCADE ON DELETE CASCADE,
                             id_usuario INT REFERENCES usuarios(id_usuario) ON UPDATE CASCADE ON DELETE CASCADE,
                             fecha_asignacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             PRIMARY KEY (id_rol, id_usuario)
);