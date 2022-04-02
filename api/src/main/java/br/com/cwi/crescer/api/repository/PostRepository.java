package br.com.cwi.crescer.api.repository;

import br.com.cwi.crescer.api.domain.Categoria;
import br.com.cwi.crescer.api.domain.Post;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PostRepository extends PagingAndSortingRepository<Post, Integer>, JpaSpecificationExecutor<Post> {

    List<Post> findAll();

    List<Post> findDistinctByTagsInOrderByIdPostDesc(List<Categoria> tags);

    List<Post> findDistinctByTagsIdCategoriaIn(List<Integer> idCategorias);

    List<Post> findDistinctByTagsInAndUsuarioIdUsuarioNot(List<Categoria> tags, Integer idUsuario);
}