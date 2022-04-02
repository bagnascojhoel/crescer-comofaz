package br.com.cwi.crescer.api.service.notificacao;

import br.com.cwi.crescer.api.controller.response.NotificacaoResponse;
import br.com.cwi.crescer.api.domain.Notificacao;
import br.com.cwi.crescer.api.mapper.notificacao.NotificacaoResponseMapper;
import br.com.cwi.crescer.api.repository.NotificacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuscarNotificacaoService {

    @Autowired
    private NotificacaoRepository notificacaoRepository;

    @Autowired
    private NotificacaoResponseMapper notificacaoResponseMapper;

    public List<NotificacaoResponse> doIt() {

        List<Notificacao> notificacoes = notificacaoRepository.findByVisualizadaFalseOrderByIdNotificacaoDesc();

        return notificacoes.stream()
                           .map(notificacaoResponseMapper::convert)
                           .collect(Collectors.toList());

    }
}