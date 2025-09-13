package com.schons.Sales.System.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.schons.Sales.System.Conexao.Conexao;
import com.schons.Sales.System.Entity.Produto;

@Service
public class ProdutoDAO {

    public Produto insert(Produto produto) {
        String sql = "INSERT INTO produtos (nome, preco, categoria) VALUES (?,?,?)";
        try (Connection conexao = Conexao.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setString(3, produto.getCategoria());
            stmt.executeUpdate();

            try(ResultSet rs = stmt.getGeneratedKeys()) {
                if(rs.next()) {
                    produto.setId(rs.getInt(1));
                }
            }
            return produto;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM produtos WHERE id = ?";
        try (Connection conexao = Conexao.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if(rowsAffected > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Produto> listAll() {
        String sql = "SELECT * FROM produtos";
        List<Produto> produtos = new ArrayList<>();
        try (Connection conexao = Conexao.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                double preco = rs.getDouble("preco");
                String categoria = rs.getString("categoria");
                Produto produto = new Produto(id, nome, preco, categoria);
                produtos.add(produto);
            }
            return produtos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}