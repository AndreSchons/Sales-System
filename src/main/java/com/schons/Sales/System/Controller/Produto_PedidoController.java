package com.schons.Sales.System.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schons.Sales.System.Dao.Produto_PedidoDAO;
import com.schons.Sales.System.Entity.Produto_Pedido;

@RestController
@RequestMapping("/produto_pedido")
public class Produto_PedidoController {

    private Produto_PedidoDAO produto_PedidoDAO;

    public Produto_PedidoController(Produto_PedidoDAO produto_PedidoDAO) {
        this.produto_PedidoDAO = produto_PedidoDAO;
    }

    @GetMapping
    public List<Produto_Pedido> listAll() {
        return produto_PedidoDAO.listAll();
    }


}
