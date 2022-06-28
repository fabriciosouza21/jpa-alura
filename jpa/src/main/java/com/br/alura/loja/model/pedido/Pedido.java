package com.br.alura.loja.model.pedido;

import com.br.alura.loja.model.cliente.Cliente;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate data;
    @Column(name = "valor_total")
    private BigDecimal valorTotal = BigDecimal.ZERO;
    @ManyToOne
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> items = new ArrayList<>();


    public Pedido() {
    }

    public Pedido(LocalDate data, BigDecimal valorTotal, Cliente cliente) {
        this.data = data;
        this.valorTotal = valorTotal;
        this.cliente = cliente;
    }

    private String descricao;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<ItemPedido> getItems() {
        return items;
    }

    public void setItems(List<ItemPedido> items) {
        this.items = items;
    }

    public void adicionarItem(ItemPedido item){
        item.setPedido(this);
        this.items.add(item);
        this.valorTotal = valorTotal.add(item.getValorTotal());
    }
}
