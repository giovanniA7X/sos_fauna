package DAO;

import Conexao.ConexaoBD;
import Classes.Empresa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author giova
 */
public class EmpresaDAO {
     public boolean existe(Empresa empresa) throws Exception{
        String sql = "SELECT * FROM Empresa WHERE nome = ? AND senha = ?";
        try (Connection conn = ConexaoBD.obterConexao();
                PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, empresa.getNome());
            ps.setString(2, empresa.getSenha());
            try (ResultSet rs = ps.executeQuery()){
                return rs.next();
            }
        }
    }
}