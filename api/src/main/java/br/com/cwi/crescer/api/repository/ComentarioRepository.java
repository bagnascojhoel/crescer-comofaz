package br.com.cwi.crescer.api.repository;

import br.com.cwi.crescer.api.domain.Comentario;
import org.springframework.data.repository.CrudRepository;

public interface ComentarioRepository extends CrudRepository<Comentario, Integer> {
}