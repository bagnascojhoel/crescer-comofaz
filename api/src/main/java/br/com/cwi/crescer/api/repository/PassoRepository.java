package br.com.cwi.crescer.api.repository;

import br.com.cwi.crescer.api.domain.Passo;
import br.com.cwi.crescer.api.domain.Post;
import org.springframework.data.repository.CrudRepository;

public interface PassoRepository extends CrudRepository<Passo, Integer> {

    void deleteAllByPost(Post post);

}