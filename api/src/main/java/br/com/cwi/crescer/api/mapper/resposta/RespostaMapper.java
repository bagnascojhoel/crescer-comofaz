package br.com.cwi.crescer.api.mapper.resposta;

import br.com.cwi.crescer.api.domain.Comentario;
import br.com.cwi.crescer.api.domain.Resposta;
import br.com.cwi.crescer.api.domain.Usuario;
import org.springframework.stereotype.Component;

@Component
public class RespostaMapper {

    public Resposta convert(String texto, Comentario comentario, Usuario usuario) {
        Resposta resposta = new Resposta();
        resposta.setTexto(texto);
        resposta.setComentario(comentario);
        resposta.setUsuario(usuario);
        return resposta;
    }
}