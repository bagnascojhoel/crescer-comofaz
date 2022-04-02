package br.com.cwi.crescer.api.mapper.passo;

import br.com.cwi.crescer.api.controller.response.PassoResponse;
import br.com.cwi.crescer.api.domain.Passo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PassoResponseMapper {

    @Autowired
    private ModelMapper modelMapper;

    public PassoResponse convert(Passo passo) {
        return modelMapper.map(passo, PassoResponse.class);
    }
}