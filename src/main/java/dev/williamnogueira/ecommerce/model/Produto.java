package dev.williamnogueira.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name="TB_Produto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Enumerated
    private Categoria categoria;
    @NotBlank
    private String nome;
    @NotBlank
    private String marcaProduto;
    @NotNull
    private BigDecimal preco;
    @NotNull
    private boolean temDesconto;
    private BigDecimal precoComDesconto;
    @NotNull
    private byte parcelas;
    @NotNull
    private boolean freteGratis;
    @NotBlank
    private String imgUrl;
}