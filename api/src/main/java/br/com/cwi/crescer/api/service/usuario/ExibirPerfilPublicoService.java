package br.com.cwi.crescer.api.service.usuario;

import br.com.cwi.crescer.api.controller.response.UsuarioPublicoResponse;
import br.com.cwi.crescer.api.domain.Lista;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.exception.NaoEncontradoException;
import br.com.cwi.crescer.api.mapper.usuario.UsuarioPublicoResponseMapper;
import br.com.cwi.crescer.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExibirPerfilPublicoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioPublicoResponseMapper usuarioPublicoResponseMapper;

    public UsuarioPublicoResponse doIt(Integer idUsuario){

        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);

        if (!usuario.isPresent())
            throw new NaoEncontradoException("Usuario não encontrado");

        List<Lista> listasPublicas = usuario.get()
                .getListas()
                .stream()
                .filter(lista -> !lista.isPrivado())
                .collect(Collectors.toList());

        usuario.get().setListas(listasPublicas);

        Collections.sort(listasPublicas);

        Integer totalContribuicoes = usuario.get().getPosts().size();

        return usuarioPublicoResponseMapper.apply(usuario.get(), totalContribuicoes);
    }

}
