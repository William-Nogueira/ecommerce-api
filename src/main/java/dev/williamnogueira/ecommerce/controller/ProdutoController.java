package dev.williamnogueira.ecommerce.controller;

import dev.williamnogueira.ecommerce.model.Categoria;
import dev.williamnogueira.ecommerce.model.Produto;
import dev.williamnogueira.ecommerce.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/produtos")
@CrossOrigin
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @GetMapping
    public Page<Produto> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "100") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Optional<Produto> findById(@PathVariable long id) {
        return repository.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void create(@Valid @RequestBody Produto produto) {
        repository.save(produto);
    }

    @PutMapping("/{id}")
    public void update(@Valid @RequestBody Produto produto, @PathVariable long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado.");
        }
        repository.save(produto);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado.");
        }
        repository.deleteById(id);
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<Page<Produto>> findByCategoria(@PathVariable("categoria") String categoriaString,
                                                         @RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "100") int size) {
        try {
            Categoria categoria = Categoria.valueOf(categoriaString.toUpperCase());
            Pageable pageable = PageRequest.of(page, size);
            List<Produto> produtosList = repository.findAllByCategoria(categoria, pageable);
            Page<Produto> produtosPage = new PageImpl<>(produtosList, pageable, produtosList.size());
            return ResponseEntity.ok(produtosPage);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/marca/{marca}")
    public ResponseEntity<Page<Produto>> findByMarca(@PathVariable("marca") String marca,
                                                     @RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "100") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Produto> produtosList = repository.findAllByMarcaProduto(marca, pageable);
        Page<Produto> produtosPage = new PageImpl<>(produtosList, pageable, produtosList.size());
        return ResponseEntity.ok(produtosPage);
    }

}
