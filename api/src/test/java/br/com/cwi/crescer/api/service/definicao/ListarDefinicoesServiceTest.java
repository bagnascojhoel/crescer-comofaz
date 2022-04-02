package br.com.cwi.crescer.api.service.definicao;

import br.com.cwi.crescer.api.domain.enums.Definicao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ListarDefinicoesServiceTest {

    @InjectMocks
    private ListarDefinicoesService tested;

    @Test
    public void deveListarComSucesso(){

        List<Definicao> response = Arrays.asList(Definicao.values());

        List<Definicao> result = tested.listar();

        Assert.assertEquals(response, result);
    }
}