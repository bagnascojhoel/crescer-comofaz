package br.com.cwi.crescer.api.service.lista;

import br.com.cwi.crescer.api.controller.response.ListaResponse;
import br.com.cwi.crescer.api.domain.Lista;
import br.com.cwi.crescer.api.exception.NaoAutorizadoException;
import br.com.cwi.crescer.api.exception.NaoEncontradoException;
import br.com.cwi.crescer.api.mapper.lista.ListaResponseMapper;
import br.com.cwi.crescer.api.repository.ListaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuscarListaPublicoService {

    @Autowired
    private ListaRepository listaRepository;

    @Autowired
    private ListaResponseMapper listaResponseMapper;

    public ListaResponse doIt(Integer idLista) {
        Optional<Lista> lista = listaRepository.findById(idLista);

        if (!lista.isPresent())
            throw new NaoEncontradoException("Lista não encontrada.");

        if (lista.get().isPrivado())
            throw new NaoAutorizadoException("A lista que você está buscando é privada.");

        return listaResponseMapper.convert(lista.get());
    }
}
