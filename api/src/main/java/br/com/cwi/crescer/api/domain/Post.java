package br.com.cwi.crescer.api.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPost;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Column
    private String titulo;

    @Column
    private String descricao;

    @Column
    private String imagemCapa;

    @Column
    private String dica;

    @ElementCollection
    @CollectionTable(joinColumns = @JoinColumn(name = "id_post"))
    private List<String> materiais = new ArrayList<>();

    @Column
    private Integer duracaoMinutos;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Passo> passos = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<Comentario> comentarios = new ArrayList<>();

    @Column
    private Integer dificuldade;

    @ManyToMany
    @JoinTable(name = "post_categoria",
            joinColumns = @JoinColumn(name = "id_post"),
            inverseJoinColumns = @JoinColumn(name = "id_categoria"))
    private List<Categoria> tags = new ArrayList<>();
}