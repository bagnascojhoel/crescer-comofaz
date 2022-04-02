package br.com.cwi.crescer.api.service.lista;

import br.com.cwi.crescer.api.controller.request.ListaRequest;
import br.com.cwi.crescer.api.controller.response.ListaHeaderResponse;
import br.com.cwi.crescer.api.domain.Lista;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.mapper.lista.ListaHeaderResponseMapper;
import br.com.cwi.crescer.api.mapper.lista.ListaMapper;
import br.com.cwi.crescer.api.repository.ListaRepository;
import br.com.cwi.crescer.api.service.seguranca.IdentificarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastrarListaService {

    @Autowired
    private IdentificarUsuarioService identificarUsuarioService;

    @Autowired
    private ListaRepository listaRepository;

    @Autowired
    private ListaMapper listaMapper;

    @Autowired
    private ListaHeaderResponseMapper listaHeaderResponseMapper;

    public ListaHeaderResponse cadastrar(ListaRequest request) {

        Usuario usuario = identificarUsuarioService.doIt();

        Lista lista = listaMapper.convert(request, usuario);

        return listaHeaderResponseMapper.convert(listaRepository.save(lista));
    }
}