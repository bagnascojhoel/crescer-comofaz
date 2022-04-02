package br.com.cwi.crescer.api.mapper.usuario;

import br.com.cwi.crescer.api.controller.request.UsuarioRequest;
import br.com.cwi.crescer.api.domain.Usuario;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

@RunWith(MockitoJUnitRunner.class)
public class UsuarioMapperTest {

    @InjectMocks
    UsuarioMapper tested;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void deveMapearCorretamente(){

        UsuarioRequest request = new UsuarioRequest();
        Usuario usuario = new Usuario();

        Mockito.when(modelMapper.map(request, Usuario.class)).thenReturn(usuario);

        Usuario result = tested.convert(request);

        Mockito.verify(modelMapper).map(request, Usuario.class);

        Assert.assertEquals(usuario, result);

    }

}