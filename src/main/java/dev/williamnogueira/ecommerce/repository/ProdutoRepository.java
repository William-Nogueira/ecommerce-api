package dev.williamnogueira.ecommerce.repository;

import dev.williamnogueira.ecommerce.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
