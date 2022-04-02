package br.com.cwi.crescer.api.utils;

import br.com.cwi.crescer.api.domain.Post;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class PostSpecifications {

    private PostSpecifications() {}

    public static Specification<Post> withDynamicQuery(String titulo, Integer dificuldade) {
        return (post, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (dificuldade != null && dificuldade != 0)
                predicates.add(builder.and(builder.equal(post.get("dificuldade"), dificuldade)));

            if (titulo != null)
                predicates.add(builder.and(builder.like(post.get("titulo"), "%" + titulo + "%")));

            Predicate[] predicatesArray = new Predicate[predicates.size()];
            return builder.and(predicates.toArray(predicatesArray));
        };
    }
}