package com.schons.Sales.System.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schons.Sales.System.Dao.ClienteDAO;
import com.schons.Sales.System.Entity.Cliente;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private ClienteDAO clienteDAO;

    public ClienteController(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    @PostMapping
    public Cliente insert(@RequestBody Cliente cliente) {
        return clienteDAO.insert(cliente);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable int id) {
        return clienteDAO.delete(id);
    }

    @GetMapping
    public List<Cliente> listAll() {
        return clienteDAO.listAll();
    }

    @GetMapping("/{id}")
    public Cliente findById(@PathVariable int id){
        return clienteDAO.findById(id);
    }
}
