package br.com.cwi.crescer.api.service.notificacao;

import br.com.cwi.crescer.api.domain.Notificacao;
import br.com.cwi.crescer.api.exception.NaoEncontradoException;
import br.com.cwi.crescer.api.repository.NotificacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AtualizarNotificacaoService {

    @Autowired
    private NotificacaoRepository notificacaoRepository;

    public void doIt(Integer idNotificacao) {

        Optional<Notificacao> notificacao = notificacaoRepository.findById(idNotificacao);

        if (!notificacao.isPresent())
            throw new NaoEncontradoException("Notificação não encontrada.");

        notificacao.get().setVisualizada(true);

        notificacaoRepository.save(notificacao.get());
    }
}