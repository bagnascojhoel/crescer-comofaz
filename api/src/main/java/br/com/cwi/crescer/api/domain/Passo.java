package br.com.cwi.crescer.api.domain;

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
public class Passo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPasso;

    @ManyToOne
    @JoinColumn(name = "id_post")
    private Post post;

    @Column
    private String texto;

    @Column
    private String foto;
}