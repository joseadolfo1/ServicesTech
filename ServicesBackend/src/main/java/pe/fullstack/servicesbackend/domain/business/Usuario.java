package pe.fullstack.servicesbackend.domain.business;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario{

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

    private String clave;

    private List<String> roles;
}