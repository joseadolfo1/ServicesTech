-- Las migraciones de datos mock insertan ids explícitos, lo que no avanza las
-- secuencias. Esto las sincroniza con el máximo id existente.
SELECT setval(pg_get_serial_sequence('usuarios','id_usuario'),
              COALESCE((SELECT MAX(id_usuario) FROM usuarios), 0) + 1, false);

SELECT setval(pg_get_serial_sequence('roles','id_rol'),
              COALESCE((SELECT MAX(id_rol) FROM roles), 0) + 1, false);