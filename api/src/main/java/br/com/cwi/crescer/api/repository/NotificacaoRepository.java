package br.com.cwi.crescer.api.repository;

import br.com.cwi.crescer.api.domain.Notificacao;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NotificacaoRepository extends CrudRepository<Notificacao, Integer> {
    List<Notificacao> findByVisualizadaFalseOrderByIdNotificacaoDesc();
}