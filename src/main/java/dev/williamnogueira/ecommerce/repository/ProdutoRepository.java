package dev.williamnogueira.ecommerce.repository;

import dev.williamnogueira.ecommerce.model.Categoria;
import dev.williamnogueira.ecommerce.model.Produto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findAllByCategoria(Categoria categoria, Pageable pageable);
    List<Produto> findAllByMarcaProduto(String marca, Pageable pageable);
}