package br.com.cwi.crescer.api.service.usuario;

import br.com.cwi.crescer.api.controller.request.EditarUsuarioRequest;
import br.com.cwi.crescer.api.controller.response.UsuarioResponse;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.mapper.usuario.EditarUsuarioMapper;
import br.com.cwi.crescer.api.mapper.usuario.UsuarioResponseMapper;
import br.com.cwi.crescer.api.repository.UsuarioRepository;
import br.com.cwi.crescer.api.service.amazon.FileSaverService;
import br.com.cwi.crescer.api.service.seguranca.IdentificarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Service
public class EditarPerfilService {

    @Autowired
    private IdentificarUsuarioService identificarUsuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EditarUsuarioMapper editarUsuarioMapper;

    @Autowired
    private UsuarioResponseMapper usuarioResponseMapper;

    @Autowired
    private FileSaverService fileSaverService;

    public UsuarioResponse doIt(EditarUsuarioRequest request, MultipartFile imagem) {

        Usuario usuarioAEditar = identificarUsuarioService.doIt();
        Usuario usuarioEditado;

        String urlFoto =  fileSaverService.write(imagem, usuarioAEditar.hashCode());

        if (Objects.isNull(urlFoto))
            usuarioEditado = editarUsuarioMapper.apply(usuarioAEditar, request.getNome());

        else
            usuarioEditado = editarUsuarioMapper.apply(usuarioAEditar, request.getNome(), urlFoto);

        usuarioRepository.save(usuarioEditado);

        return usuarioResponseMapper.convert(usuarioEditado);
    }
}