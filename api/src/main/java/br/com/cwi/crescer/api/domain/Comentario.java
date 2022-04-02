package br.com.cwi.crescer.api.domain;

import br.com.cwi.crescer.api.domain.enums.Definicao;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "idComentario")
@Entity
public class Comentario implements Comparable<Comentario> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idComentario;

    @ManyToOne
    @JoinColumn(name = "id_post")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "comentario", cascade = CascadeType.ALL)
    private List<Resposta> respostas = new ArrayList<>();

    @Column
    private String texto;

    @Column
    private String foto;

    @Enumerated(value = EnumType.STRING)
    private Definicao definicao;

    @Override
    public int compareTo(Comentario c2) {
        return c2.getIdComentario().compareTo(this.idComentario);
    }
}