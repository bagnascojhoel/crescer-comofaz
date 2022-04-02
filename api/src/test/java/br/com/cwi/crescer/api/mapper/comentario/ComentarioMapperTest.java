package br.com.cwi.crescer.api.mapper.comentario;

import br.com.cwi.crescer.api.controller.request.ComentarioRequest;
import br.com.cwi.crescer.api.domain.Comentario;
import br.com.cwi.crescer.api.domain.Post;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.domain.enums.Definicao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ComentarioMapperTest {

    @InjectMocks
    ComentarioMapper tested;

    @Test
    public void deveMapearComSucesso(){

        ComentarioRequest comentarioRequest = new ComentarioRequest();
        Post post = new Post();
        post.setIdPost(1);
        Usuario usuario = new Usuario();
        Definicao definicao = Definicao.CONTRIBUICAO;

        comentarioRequest.setDefinicao(definicao);
        comentarioRequest.setTexto("texto");

        Comentario result = tested.convert(comentarioRequest, post, usuario);

        Assert.assertEquals(comentarioRequest.getDefinicao(), result.getDefinicao());
        Assert.assertEquals(comentarioRequest.getTexto(), result.getTexto());
    }
}