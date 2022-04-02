package br.com.cwi.crescer.api.service.usuario;

import br.com.cwi.crescer.api.domain.Categoria;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.repository.CategoriaRepository;
import br.com.cwi.crescer.api.repository.UsuarioRepository;
import br.com.cwi.crescer.api.service.seguranca.IdentificarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtualizarInteressesService {

    @Autowired
    private IdentificarUsuarioService identificarUsuarioService;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void doIt(List<Integer> idInteresses) {

        Usuario usuario = identificarUsuarioService.doIt();

        List<Categoria> interessesAtualizados = categoriaRepository.findAllByIdCategoriaIn(idInteresses);

        usuario.setInteresses(interessesAtualizados);

        usuarioRepository.save(usuario);

    }

}