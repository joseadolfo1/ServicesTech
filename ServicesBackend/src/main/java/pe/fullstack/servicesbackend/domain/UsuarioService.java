package pe.fullstack.servicesbackend.domain;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.fullstack.servicesbackend.config.UserInfoDetails;
import pe.fullstack.servicesbackend.controller.mapper.UsuarioMapper;
import pe.fullstack.servicesbackend.domain.business.Usuario;
import pe.fullstack.servicesbackend.repository.RolEntity;
import pe.fullstack.servicesbackend.repository.RolRepository;
import pe.fullstack.servicesbackend.repository.UsuarioEntity;
import pe.fullstack.servicesbackend.repository.UsuarioRepository;


import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private UsuarioRepository  usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RolRepository rolRepository;


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UsuarioEntity usuarioEntity = this.usuarioRepository.findByEnabledTrueAndEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User not found with email: " + email));

        return new UserInfoDetails(usuarioEntity);
    }

    public List<Usuario> getUsuarios() {
        return this.usuarioMapper.convertFromEntity(
                this.usuarioRepository.findAll()
        );
    }

    public Usuario getUsuario(Integer id) throws Exception{

        if (!this.usuarioRepository.existsById(id)) {
            throw new Exception("RECURSO NO EXISTE");
        }

        return this.usuarioMapper.convertFromEntity(
                this.usuarioRepository.getReferenceById(id)
        );
    }

    @Transactional
    public Usuario createUsuario(Usuario usuario) {

        if (this.usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("Ya existe un usuario con el email: " + usuario.getEmail());
        }
        if (this.usuarioRepository.existsByDni(usuario.getDni())) {
            throw new IllegalArgumentException("Ya existe un usuario con el DNI: " + usuario.getDni());
        }

        List<RolEntity> roles = new ArrayList<>(
                usuario.getRoles().stream()
                        .map(nombre -> this.rolRepository.findByNombre(nombre)
                                .orElseThrow(() -> new IllegalArgumentException("Rol no válido: " + nombre)))
                        .toList()
        );

        UsuarioEntity usuarioEntity = this.usuarioMapper.convertFromBusiness(usuario);

        usuarioEntity.setClave(this.passwordEncoder.encode(usuario.getClave()));
        usuarioEntity.setEnabled(true);
        usuarioEntity.setRoles(roles);

        return this.usuarioMapper.convertFromEntity(
                this.usuarioRepository.save(usuarioEntity)
        );


    }

    public void deleteUsuario(Integer id) {
        this.usuarioRepository.deleteById(id);

    }
}
