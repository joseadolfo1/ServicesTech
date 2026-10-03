package pe.fullstack.servicesbackend.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioResponse {

    private Integer idUsuario;

    private String nombres;

    private String apellidos;

    private String dni;

    private String email;

    private String telefono;

    private Boolean enabled;

    private LocalDateTime lastSignIn;

    private Integer createdBy;

    private LocalDateTime createdAt;

    private Integer updatedBy;

    private LocalDateTime updatedAt;

}