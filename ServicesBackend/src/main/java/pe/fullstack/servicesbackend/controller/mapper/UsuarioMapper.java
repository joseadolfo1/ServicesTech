package pe.fullstack.servicesbackend.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import pe.fullstack.servicesbackend.config.UserInfoDetails;
import pe.fullstack.servicesbackend.controller.request.CreateUsuarioRequest;
import pe.fullstack.servicesbackend.controller.response.UsuarioResponse;
import pe.fullstack.servicesbackend.domain.business.Usuario;
import pe.fullstack.servicesbackend.repository.UsuarioEntity;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UsuarioMapper {

    @Mapping(target = "roles", ignore = true)
    Usuario convertFromEntity(UsuarioEntity entity);

    List<Usuario> convertFromEntity(List<UsuarioEntity> entities);

    UsuarioResponse convert(Usuario usuario);

    List<UsuarioResponse> convert(List<Usuario> usuarios);

    @Mapping(target = "createdBy", source = "userInfoDetails.usuarioId")
    @Mapping(target = "roles", source = "request.roles")
    Usuario convert(CreateUsuarioRequest request, UserInfoDetails userInfoDetails);

    @Mapping(target = "roles", ignore = true)
    UsuarioEntity convertFromBusiness(Usuario usuario);
    
}