package br.com.cwi.crescer.api.service.usuario;

import br.com.cwi.crescer.api.controller.response.ListaHeaderResponse;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.mapper.lista.ListaHeaderResponseMapper;
import br.com.cwi.crescer.api.service.seguranca.IdentificarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class BuscarListasPorUsuarioService {

    @Autowired
    private IdentificarUsuarioService identificarUsuarioService;

    @Autowired
    private ListaHeaderResponseMapper listaHeaderResponseMapper;

    public List<ListaHeaderResponse> buscar() {

        Usuario usuario = identificarUsuarioService.doIt();

        Collections.sort(usuario.getListas());

        return listaHeaderResponseMapper.convert(usuario.getListas());
    }
}