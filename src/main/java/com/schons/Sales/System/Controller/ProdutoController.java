package com.schons.Sales.System.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schons.Sales.System.Dao.ProdutoDAO;
import com.schons.Sales.System.Entity.Produto;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private ProdutoDAO produtoDAO;

    public ProdutoController(ProdutoDAO produtoDAO) {
        this.produtoDAO = produtoDAO;
    }

    @PostMapping
    public Produto insert(@RequestBody Produto produto) {
        return produtoDAO.insert(produto);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable int id) {
        return produtoDAO.delete(id);
    }

    @GetMapping
    public List<Produto> listAll() {
        return produtoDAO.listAll();
    }
}
