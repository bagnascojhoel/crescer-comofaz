package br.com.cwi.crescer.api.domain;

import br.com.cwi.crescer.api.security.ProviderType;
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
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @OneToMany(mappedBy="usuario")
    private List<Post> posts = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "favorito",
               joinColumns = @JoinColumn(name = "id_usuario"),
               inverseJoinColumns = @JoinColumn(name = "id_post"))
    private List<Post> favoritos = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "feito",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_post"))
    private List<Post> feitos = new ArrayList<>();

    @Column(name = "id_provedor")
    private String providerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_provedor")
    private ProviderType provider;

    @Column
    private String nome;

    @Column
    private String email;

    @Column
    private String foto;

    @ManyToMany
    @JoinTable(name = "usuario_categoria",
               joinColumns = @JoinColumn(name = "id_usuario"),
               inverseJoinColumns = @JoinColumn(name = "id_categoria"))
    private List<Categoria> interesses = new ArrayList<>();

    @OneToMany(mappedBy="usuario")
    private List<Lista> listas = new ArrayList<>();
}