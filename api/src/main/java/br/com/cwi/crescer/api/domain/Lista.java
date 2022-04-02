package br.com.cwi.crescer.api.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "idLista")
@Entity
public class Lista implements Comparable<Lista> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLista;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToMany
    @JoinTable(name = "lista_post",
            joinColumns = @JoinColumn(name = "id_lista"),
            inverseJoinColumns = @JoinColumn(name = "id_post"))
    private List<Post> posts = new ArrayList<>();

    @Column
    private String assunto;


    @Column(name = "is_privado")
    private boolean isPrivado;

    @Override
    public int compareTo(Lista l2) {
        return l2.getIdLista().compareTo(this.idLista);
    }
}