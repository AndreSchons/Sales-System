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
import com.schons.Sales.System.Entity.Cliente;

@Service
public class ClienteDAO {

    public Cliente insert(Cliente cliente) {
        String sql = "INSERT INTO clientes(nome, email, cpf) VALUES (?,?,?)";
        try (Connection conexao = Conexao.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getCpf());
            stmt.executeUpdate();
            
            try(ResultSet rs = stmt.getGeneratedKeys()) {
                if(rs.next()) {
                    cliente.setId(rs.getInt(1));
                }
            }
            return cliente;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(int id){
        String sql = "DELETE FROM clientes WHERE id = ?";
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

    public List<Cliente> listAll() {
        String sql = "SELECT * FROM clientes";
        List<Cliente> clientes = new ArrayList<>();
        try (Connection conexao = Conexao.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String cpf = rs.getString("cpf");
                Cliente cliente = new Cliente(id, nome, email, cpf);
                clientes.add(cliente);
            }
                return clientes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Cliente findById (int id) {
        String sql = "SELECT * FROM clientes WHERE id = ?";
        try (Connection conexao = Conexao.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String cpf = rs.getString("cpf");
                Cliente cliente = new Cliente(id,nome,email,cpf);
                return cliente;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
