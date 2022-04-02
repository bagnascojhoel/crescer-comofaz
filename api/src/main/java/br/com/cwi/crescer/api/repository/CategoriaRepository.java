package br.com.cwi.crescer.api.repository;

import br.com.cwi.crescer.api.domain.Categoria;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoriaRepository extends CrudRepository<Categoria, Integer> {
    
    List<Categoria> findAll();
    
    List<Categoria> findAllByIdCategoriaIn(List<Integer> idTags);
}