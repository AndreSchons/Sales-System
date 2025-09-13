package com.schons.Sales.System.Controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schons.Sales.System.Dao.PedidoDAO;
import com.schons.Sales.System.Entity.Pedido;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    private PedidoDAO pedidoDAO;

    public PedidoController(PedidoDAO pedidoDAO) {
        this.pedidoDAO = pedidoDAO;
    }

    @PostMapping
    public Pedido insert(@RequestBody Pedido pedido) {
        return pedidoDAO.insert(pedido);
    }

    @DeleteMapping
    public void delete(@PathVariable int id) {
        pedidoDAO.delete(id);
    }
}
