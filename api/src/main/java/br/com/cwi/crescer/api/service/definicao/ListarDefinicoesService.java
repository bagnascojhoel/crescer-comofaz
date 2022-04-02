package br.com.cwi.crescer.api.service.definicao;

import br.com.cwi.crescer.api.domain.enums.Definicao;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ListarDefinicoesService {

    public List<Definicao> listar() {
        return Arrays.asList(Definicao.values());
    }
}