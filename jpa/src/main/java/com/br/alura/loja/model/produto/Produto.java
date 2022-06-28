package com.br.alura.loja.model.produto;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "produtos")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal amout;
    private String description;
    @ManyToOne
    private Categoria category;

    @Column(name = "data_cadastro")
    private LocalDate dataCadastrato = LocalDate.now();

    public Produto() {
    }

    public Produto(String name, BigDecimal amout, String description, Categoria category) {
        this.name = name;
        this.amout = amout;
        this.description = description;
        this.category = category;
    }

    public Categoria getCategory() {
        return category;
    }

    public void setCategory(Categoria category) {
        this.category = category;
    }

    public LocalDate getDataCadastrato() {
        return dataCadastrato;
    }

    public void setDataCadastrato(LocalDate dataCadastrato) {
        this.dataCadastrato = dataCadastrato;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmout() {
        return amout;
    }

    public void setAmout(BigDecimal amout) {
        this.amout = amout;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
