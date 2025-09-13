package com.schons.Sales.System.Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.schons.Sales.System.Conexao.Conexao;
import com.schons.Sales.System.Entity.Pedido;
import com.schons.Sales.System.Entity.Produto_Pedido;

@Service
public class PedidoDAO {

    public Pedido insert(Pedido pedido) {
        String sql = "INSERT INTO pedidos (data, id_cliente) VALUES (?,?)";
        try (Connection conexao = Conexao.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDate(1, Date.valueOf(LocalDate.now()));
            stmt.setInt(2, pedido.getCliente().getId());
            stmt.executeUpdate();

            try(ResultSet rs = stmt.getGeneratedKeys()){
                if(rs.next()) {
                    pedido.setId(rs.getInt(1));
                }
            }

            Produto_PedidoDAO ppDAO = new Produto_PedidoDAO();
        if(pedido.getProduto_pedidos() != null) {
            for(Produto_Pedido pp : pedido.getProduto_pedidos()) {
                pp.setPedido(pedido);
                ppDAO.insert(pp);
            }
        }
        return pedido;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
      try (Connection conexao = Conexao.getConnection()) {
        try(PreparedStatement stmt = conexao.prepareStatement("DELETE FROM produto_pedido WHERE id_pedido = ?")){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
        try(PreparedStatement stmt = conexao.prepareStatement("DELETE FROM pedidos WHERE id = ?")){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }
}
