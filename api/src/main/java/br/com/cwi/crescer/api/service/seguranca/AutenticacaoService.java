package br.com.cwi.crescer.api.service.seguranca;

import br.com.cwi.crescer.api.controller.response.AutenticarUsuarioResponse;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.mapper.usuario.AutenticarUsuarioResponseMapper;
import br.com.cwi.crescer.api.repository.UsuarioRepository;
import br.com.cwi.crescer.api.service.usuario.CadastrarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private IdentificarEmissorDoRequestService identificarEmissorDoRequestService;

    @Autowired
    private CadastrarUsuarioService cadastrarUsuarioService;

    @Autowired
    private AutenticarUsuarioResponseMapper autenticarUsuarioResponseMapper;

    public AutenticarUsuarioResponse autenticar() {

        Usuario emissorRequest = identificarEmissorDoRequestService.identificar();

        Optional<Usuario> usuario = usuarioRepository.findFirstByProviderIdAndProvider(emissorRequest.getProviderId(), emissorRequest.getProvider());

        if (!usuario.isPresent())
            return cadastrarUsuarioService.cadastrar(emissorRequest);

        AutenticarUsuarioResponse response = autenticarUsuarioResponseMapper.convert(usuario.get());

        response.setPrimeiroAcesso(usuario.get().getInteresses().isEmpty());

        return response;

    }
}
