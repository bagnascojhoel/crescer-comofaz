package br.com.cwi.crescer.api.mapper.resposta;

import br.com.cwi.crescer.api.controller.response.RespostaResponse;
import br.com.cwi.crescer.api.domain.Resposta;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RespostaResponseMapper {

    @Autowired
    private ModelMapper modelMapper;

    public RespostaResponse convert(Resposta resposta) {
        return modelMapper.map(resposta, RespostaResponse.class);
    }
}