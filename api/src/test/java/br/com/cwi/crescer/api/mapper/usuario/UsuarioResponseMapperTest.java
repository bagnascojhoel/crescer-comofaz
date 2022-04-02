package br.com.cwi.crescer.api.mapper.usuario;

import br.com.cwi.crescer.api.controller.response.UsuarioResponse;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.mapper.lista.ListarPostResponseMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

@RunWith(MockitoJUnitRunner.class)
public class UsuarioResponseMapperTest {

    @InjectMocks
    UsuarioResponseMapper tested;

    @Mock
    ModelMapper modelMapper;

    @Mock
    ListarPostResponseMapper listarPostResponseMapper;

    @Test
    public void deveMapearCorretamente(){

            Usuario usuario = new Usuario();
            UsuarioResponse response = new UsuarioResponse();

            Mockito.when(modelMapper.map(usuario, UsuarioResponse.class)).thenReturn(response);

            UsuarioResponse result = tested.convert(usuario, 0);

            Mockito.verify(modelMapper).map(usuario, UsuarioResponse.class);

            Assert.assertEquals(response, result);
    }
}