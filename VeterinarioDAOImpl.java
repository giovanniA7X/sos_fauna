package VeterinarioDAOImpl;

import VeterinarioDAO.VeterinarioDAO;
import Classes.Veterinario;
import Exception.ExceptionDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VeterinarioDAOImpl implements VeterinarioDAO {
    private Connection conn;
    private static final Logger logger = Logger.getLogger(VeterinarioDAOImpl.class.getName());

    @Override
    public void cadastrarVeterinario(Veterinario veterinario) throws ExceptionDAO {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Veterinario (nome, senha, crmv, cpf, email, endereco, telefone) VALUES(?,?,?,?,?,?,?)");
            ps.setString(1, veterinario.getNome());
            ps.setString(2, veterinario.getSenha());
            ps.setString(3, veterinario.getCrmv());
            ps.setString(4, veterinario.getCpf());
            ps.setString(5, veterinario.getEmail());
            ps.setString(6, veterinario.getEndereco());
            ps.setString(7, veterinario.getTelefone());
            ps.executeUpdate();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Erro ao cadastrar veterinário", ex);
            throw new ExceptionDAO("Erro ao cadastrar veterinário", ex);
        }
    }
    @Override
    public List<Veterinario> buscarVeterinarios() {
        List<Veterinario> veterinarios = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Veterinario");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Veterinario veterinario = new Veterinario();
                veterinario.setId(rs.getInt("id"));
                veterinario.setNome(rs.getString("nome"));
                veterinario.setSenha(rs.getString("senha"));
                veterinario.setCrmv(rs.getString("crmv"));
                veterinario.setCpf(rs.getString("cpf"));
                veterinario.setEmail(rs.getString("email"));
                veterinario.setEndereco(rs.getString("endereco"));
                veterinario.setTelefone(rs.getString("telefone"));
                veterinarios.add(veterinario);
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Erro ao buscar veterinarios", ex);
        }
        return veterinarios;
    }

    @Override
    public void atualizarVeterinario(Veterinario veterinario) throws ExceptionDAO {
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE Veterinario SET nome=?, senha=?, crmv=?, cpf=?, email=?, endereco=?, telefone=? WHERE idVeterinario=?");
            ps.setString(1, veterinario.getNome());
            ps.setString(2, veterinario.getSenha());
            ps.setString(3, veterinario.getCrmv());
            ps.setString(4, veterinario.getCpf());
            ps.setString(5, veterinario.getEmail());
            ps.setString(6, veterinario.getEndereco());
            ps.setString(7, veterinario.getTelefone());
            ps.setInt(8, veterinario.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Erro ao atualizar animal", ex);
            throw new ExceptionDAO("Erro ao atualizar animal", ex);
        }
    }
    @Override
    public void removerVeterinario(int id) throws ExceptionDAO {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Veterinario WHERE idVeterinario=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Erro ao remover veterinário", ex);
            throw new ExceptionDAO("Erro ao remover veterinário", ex);
        }
    }

    @Override
    public Veterinario buscarVeterinario(int id) {
        Veterinario veterinario = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Veterinario WHERE idVeterinario=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                veterinario = new Veterinario();
                veterinario.setId(rs.getInt("idVeterinario"));
                veterinario.setNome(rs.getString("nome"));
                veterinario.setSenha(rs.getString("senha"));
                veterinario.setCrmv(rs.getString("crmv"));
                veterinario.setCpf(rs.getString("cpf"));
                veterinario.setEmail(rs.getString("email"));
                veterinario.setEndereco(rs.getString("endereco"));
                veterinario.setTelefone(rs.getString("telefone"));
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Erro ao buscar animal", ex);
        }
        return veterinario;
    }
    

    @Override
    public boolean existe(Veterinario veterinario) throws ExceptionDAO {
        boolean existe = true;
        String sql = "SELECT * FROM Veterinario WHERE nome = ? AND senha = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, veterinario.getNome());
            ps.setString(2, veterinario.getSenha());
            try (ResultSet rs = ps.executeQuery()) {
                existe = rs.next(); // Retorna true se houver algum resultado
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Erro ao verificar existência de veterinário", ex);
            throw new ExceptionDAO("Erro ao verificar existência de veterinário", ex);
        }
        return existe;
    }


    @Override
    public void setConnection(Connection connection) {
        this.conn = connection;
    }
}
