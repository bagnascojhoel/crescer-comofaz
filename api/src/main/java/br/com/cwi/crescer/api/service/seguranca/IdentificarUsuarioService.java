package br.com.cwi.crescer.api.service.seguranca;

import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.exception.NaoAutorizadoException;
import br.com.cwi.crescer.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IdentificarUsuarioService {

    @Autowired
    private IdentificarEmissorDoRequestService identificarEmissorDoRequestService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario doIt(){

        Usuario emissorRequest = identificarEmissorDoRequestService.identificar();

        Optional<Usuario> usuario = usuarioRepository.findFirstByProviderIdAndProvider(emissorRequest.getProviderId(), emissorRequest.getProvider());

        if(!usuario.isPresent())
            throw new NaoAutorizadoException("Usuário não autorizado.");

        return usuario.get();

    }

}
