package pe.fullstack.servicesbackend.config;

import lombok.NoArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pe.fullstack.servicesbackend.repository.UsuarioEntity;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class UserInfoDetails implements UserDetails {

    private String email;

    private String clave;

    private List<GrantedAuthority> authorities;

    private Integer usuarioId;

    public UserInfoDetails(UsuarioEntity usuarioEntity) {
        this.usuarioId = usuarioEntity.getIdUsuario();
        this.email = usuarioEntity.getEmail(); // Use email as username
        this.clave = usuarioEntity.getClave();
        this.authorities = usuarioEntity.getRoles().stream()
                .map(r -> new SimpleGrantedAuthority(r.getNombre()))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public @Nullable String getPassword() {
        return this.clave;
    }

    @Override
    public @Nullable String getUsername() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer id) {
        this.usuarioId = id;
    }
}
