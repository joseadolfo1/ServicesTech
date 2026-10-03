package pe.fullstack.servicesbackend.repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuario_rol")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRolEntity {

    @EmbeddedId
    private UsuarioRolKey id = new UsuarioRolKey();

    @ManyToOne
    @MapsId("idUsuario")
    @JoinColumn(name = "id_usuario")
    private UsuarioEntity usuario;

    @ManyToOne
    @MapsId("idRol")
    @JoinColumn(name = "id_rol")
    private RolEntity rol;

    @Column(name = "fecha_asignacion", insertable = false, updatable = false)
    private LocalDateTime fechaAsignacion;
}