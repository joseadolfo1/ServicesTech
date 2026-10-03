package pe.fullstack.servicesbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
    Optional<UsuarioEntity> findByEmail(String email);
    Optional<UsuarioEntity> findByEnabledTrueAndEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByDni(String dni);
}