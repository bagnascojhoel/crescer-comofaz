package br.com.cwi.crescer.api.domain;

import br.com.cwi.crescer.api.domain.enums.TipoNotificacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idNotificacao;

    @ManyToOne
    @JoinColumn(name = "id_post")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "id_comentario")
    private Comentario comentario;

    @ManyToOne
    @JoinColumn(name = "id_usuario_a_notificar")
    private Usuario usuarioANotificar;

    @ManyToOne
    @JoinColumn(name = "id_usuario_da_interacao")
    private Usuario usuarioDaInteracao;

    @Enumerated(value = EnumType.STRING)
    private TipoNotificacao tipo;

    @Column
    private boolean visualizada;

}
