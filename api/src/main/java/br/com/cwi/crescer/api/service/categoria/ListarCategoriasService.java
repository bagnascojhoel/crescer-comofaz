package br.com.cwi.crescer.api.service.categoria;

import br.com.cwi.crescer.api.controller.response.CategoriaResponse;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.mapper.categoria.CategoriaResponseMapper;
import br.com.cwi.crescer.api.repository.CategoriaRepository;
import br.com.cwi.crescer.api.service.seguranca.IdentificarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListarCategoriasService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CategoriaResponseMapper categoriaResponseMapper;

    @Autowired
    private IdentificarUsuarioService identificarUsuarioService;

    public List<CategoriaResponse> listar() {

        Usuario usuario = identificarUsuarioService.doIt();

        return categoriaRepository.findAll()
                .stream()
                .map(categoria -> {
                    boolean isAdicionada = usuario.getInteresses().contains(categoria);
                    return categoriaResponseMapper.convert(categoria, isAdicionada);
                })
                .collect(Collectors.toList());
    }
}