package org.example;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "postgres";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();


            String sqlCreateTable = "CREATE TABLE produtos (" +
                    "id INT PRIMARY KEY, " +
                    "nome VARCHAR(50), " +
                    "descricao VARCHAR(255), " +
                    "preco DECIMAL(10, 2))";
            stmt.executeUpdate(sqlCreateTable);

            // Inserção
            String sqlInsert = "INSERT INTO produtos (id, nome, descricao, preco) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmtInsert = conn.prepareStatement(sqlInsert);
            pstmtInsert.setInt(1, 1);
            pstmtInsert.setString(2, "Produto1");
            pstmtInsert.setString(3, "Descrição do Produto1");
            pstmtInsert.setDouble(4, 10.50);
            pstmtInsert.executeUpdate();

            // Atualização
            String sqlUpdate = "UPDATE produtos SET preco = ? WHERE id = ?";
            PreparedStatement pstmtUpdate = conn.prepareStatement(sqlUpdate);
            pstmtUpdate.setDouble(1, 20.99);
            pstmtUpdate.setInt(2, 1);
            pstmtUpdate.executeUpdate();

            // Consulta
            String sqlSelect = "SELECT * FROM produtos";
            Statement stmtSelect = conn.createStatement();
            ResultSet rs = stmtSelect.executeQuery(sqlSelect);
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" +
                        rs.getString("nome") + "\t" +
                        rs.getString("descricao") + "\t" +
                        rs.getDouble("preco"));
            }

            // Exclusão
            String sqlDelete = "DELETE FROM produtos WHERE id = ?";
            PreparedStatement pstmtDelete = conn.prepareStatement(sqlDelete);
            pstmtDelete.setInt(1, 1);
            pstmtDelete.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}