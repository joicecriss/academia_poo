/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.model;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author CAUPT - ALUNOS
 */
public class ConexoesBanco {
    public ConexoesBanco getConexoesBanco() {
        try {
            Properties properties = new Properties();
            properties.setProperty("user", "root");
            properties.setProperty("password", "1234");
            properties.setProperty("useSSL", "false");
            properties.setProperty("useTimezone", "true");
            properties.setProperty("serverTimezone", "America/Sao_Paulo");
            properties.setProperty("allowPublicKeyRetrieval","true");
            
            String con = "jdbc:mysql://localhost/academia_poo";
            return (ConexoesBanco) DriverManager.getConnection(con, properties);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
