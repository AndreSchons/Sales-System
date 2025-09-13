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
import com.schons.Sales.System.Entity.Pedido;
import com.schons.Sales.System.Entity.Produto;
import com.schons.Sales.System.Entity.Produto_Pedido;

@Service
public class Produto_PedidoDAO {

    public Produto_Pedido insert(Produto_Pedido produto_Pedido) {
        String sql = "INSERT INTO produto_pedido (id_pedido, id_produto, quantidade) VALUES (?,?,?)";
        try (Connection conexao = Conexao.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, produto_Pedido.getPedido().getId());
            stmt.setInt(2, produto_Pedido.getProduto().getId());
            stmt.setInt(3, produto_Pedido.getQuantidade());
            stmt.executeUpdate();

            try(ResultSet rs = stmt.getGeneratedKeys()){
                if(rs.next()) {
                    produto_Pedido.setId(rs.getInt(1));
                }
            }
            return produto_Pedido;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Produto_Pedido> listAll() {
        String sql = """
                SELECT 
                       c.id as cliente_id, 
                       c.nome as cliente_nome,
                       pr.id as produto_id,
                       pr.nome as produto_nome,
                       pp.quantidade as quantidade,
                       p.id as pedido_id,
                       p.data as pedido_data 
                FROM produto_pedido pp
                JOIN pedidos p on pp.id_pedido = p.id 
                JOIN produtos pr on pp.id_produto = pr.id
                JOIN clientes c on p.id_cliente = c.id
                """;
        List<Produto_Pedido> lista = new ArrayList<>();
        try (Connection conexao = Conexao.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("cliente_nome"));

                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("pedido_id"));
                pedido.setData(rs.getDate("pedido_data"));
                pedido.setCliente(cliente);

                Produto produto = new Produto();
                produto.setId(rs.getInt("produto_id"));
                produto.setNome(rs.getString("produto_nome"));
                
                Produto_Pedido produto_pedido = new Produto_Pedido();
                produto_pedido.setPedido(pedido);
                produto_pedido.setProduto(produto);
                produto_pedido.setQuantidade(rs.getInt("quantidade"));

                lista.add(produto_pedido);
            }
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
