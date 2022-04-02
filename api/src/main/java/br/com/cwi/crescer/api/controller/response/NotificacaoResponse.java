package br.com.cwi.crescer.api.controller.response;

import br.com.cwi.crescer.api.domain.enums.Definicao;
import br.com.cwi.crescer.api.domain.enums.TipoNotificacao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificacaoResponse {

    private Integer idNotificacao;
    private Integer postIdPost;
    private String postTitulo;
    private Integer comentarioIdComentario;
    private Definicao comentarioDefinicao;
    private String usuarioDaInteracaoNome;
    private TipoNotificacao tipo;
    private boolean visualizada;

}