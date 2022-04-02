package br.com.cwi.crescer.api.mapper.notificacao;

import br.com.cwi.crescer.api.domain.Comentario;
import br.com.cwi.crescer.api.domain.Notificacao;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.domain.enums.TipoNotificacao;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoMapper {

    public Notificacao convert(Comentario comentario, Usuario usuarioANotificar, Usuario usuarioDaInteracao, TipoNotificacao tipo) {
        Notificacao notificacao = new Notificacao();
        notificacao.setComentario(comentario);
        notificacao.setPost(comentario.getPost());
        notificacao.setUsuarioANotificar(usuarioANotificar);
        notificacao.setUsuarioDaInteracao(usuarioDaInteracao);
        notificacao.setTipo(tipo);
        notificacao.setVisualizada(false);
        return notificacao;
    }
}
