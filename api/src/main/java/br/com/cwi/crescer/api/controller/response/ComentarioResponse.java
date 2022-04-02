package br.com.cwi.crescer.api.controller.response;

import br.com.cwi.crescer.api.domain.enums.Definicao;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ComentarioResponse {
    private Integer idComentario;
    private Integer usuarioIdUsuario;
    private String usuarioNome;
    private String usuarioFoto;
    private String texto;
    private String foto;
    private Definicao definicao;
    private List<RespostaResponse> respostas;
}