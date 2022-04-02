package br.com.cwi.crescer.api.repository;

import br.com.cwi.crescer.api.domain.Categoria;
import br.com.cwi.crescer.api.domain.Lista;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ListaRepository extends CrudRepository<Lista, Integer> {

    List<Lista> findDistinctByPostsTagsInAndIsPrivadoFalseOrderByIdListaDesc(List<Categoria> categorias);

}