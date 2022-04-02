package br.com.cwi.crescer.api.repository;

import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.security.ProviderType;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Integer> {

    List<Usuario> findAll();

    Optional<Usuario> findFirstByProviderIdAndProvider(String providerId, ProviderType provider);


}