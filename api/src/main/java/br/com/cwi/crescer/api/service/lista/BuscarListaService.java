package br.com.cwi.crescer.api.service.lista;

import br.com.cwi.crescer.api.controller.response.ListaResponse;
import br.com.cwi.crescer.api.domain.Lista;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.exception.NaoAutorizadoException;
import br.com.cwi.crescer.api.exception.NaoEncontradoException;
import br.com.cwi.crescer.api.mapper.lista.ListaResponseMapper;
import br.com.cwi.crescer.api.repository.ListaRepository;
import br.com.cwi.crescer.api.service.seguranca.IdentificarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuscarListaService {

    @Autowired
    private ListaRepository listaRepository;

    @Autowired
    private IdentificarUsuarioService identificarUsuarioService;

    @Autowired
    private ListaResponseMapper listaResponseMapper;

    public ListaResponse buscar(Integer idLista) {
        Optional<Lista> listaOptional = listaRepository.findById(idLista);

        if (!listaOptional.isPresent())
            throw new NaoEncontradoException("Lista não encontrada.");

        Lista lista = listaOptional.get();
        if (lista.isPrivado()) {
            Usuario emissor = identificarUsuarioService.doIt();

            if (!emissor.getIdUsuario().equals(lista.getUsuario().getIdUsuario()))
                throw new NaoAutorizadoException("Esta lista é privada e não pertence a você.");
        }

        return listaResponseMapper.convert(lista);
    }
}
