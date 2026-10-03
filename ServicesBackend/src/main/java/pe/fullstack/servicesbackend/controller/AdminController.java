package pe.fullstack.servicesbackend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pe.fullstack.servicesbackend.config.UserInfoDetails;
import pe.fullstack.servicesbackend.controller.mapper.UsuarioMapper;
import pe.fullstack.servicesbackend.controller.request.CreateUsuarioRequest;
import pe.fullstack.servicesbackend.controller.response.UsuarioResponse;
import pe.fullstack.servicesbackend.domain.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*") //Solo con esta linea no tengo problemas de conexion del localhost
public class AdminController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioMapper usuarioMapper;

    //Generando una api para ver el listado de los usuarios en general
    @GetMapping("/usuarios")
    @ResponseStatus(HttpStatus.OK)
    public List<UsuarioResponse> getAdminUsuarios() {
        return this.usuarioMapper.convert(
                this.usuarioService.getUsuarios()
        );
    }

    //Generar una api para ver al usuario solo por su ID
    @GetMapping("/usuarios/{id}")
    public UsuarioResponse getUsuario(@PathVariable("id") Integer id) throws Exception {
        return this.usuarioMapper.convert(
                this.usuarioService.getUsuario(id)
        );

    }

    @PostMapping("/usuarios")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponse createUsuario(@Valid @RequestBody CreateUsuarioRequest request, @AuthenticationPrincipal UserInfoDetails userInfoDetails ) {
        return this.usuarioMapper.convert(
                this.usuarioService.createUsuario(
                        this.usuarioMapper.convert(request,userInfoDetails)
                )
        );
    }

    @DeleteMapping("/usuarios/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUsuario(@PathVariable("id") Integer id) {
        this.usuarioService.deleteUsuario(id);
    }

}