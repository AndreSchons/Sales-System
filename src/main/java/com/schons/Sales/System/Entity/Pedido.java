package com.schons.Sales.System.Entity;

import java.sql.Date;
import java.util.List;

public class Pedido {

    private int id;
    private Date data;
    private Cliente cliente;
    private List<Produto_Pedido> produto_pedidos;
    private double valorTotal;

    public Pedido() {
        
    }

    public Pedido(int id, Date data, Cliente cliente, List<Produto_Pedido> produto_pedidos, double valorTotal) {
        this.id = id;
        this.data = data;
        this.cliente = cliente;
        this.produto_pedidos = produto_pedidos;
        this.valorTotal = valorTotal;
    }

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Produto_Pedido> getProduto_pedidos() {
        return produto_pedidos;
    }

    public void setProduto_pedidos(List<Produto_Pedido> produto_pedidos) {
        this.produto_pedidos = produto_pedidos;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    
}
