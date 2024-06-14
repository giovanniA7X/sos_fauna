package AnimalDAOImpl;

import AnimalDAO.AnimalDAO;
import Classes.Animal;
import Exception.ExceptionDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AnimalDAOImpl implements AnimalDAO {
    private Connection conn;
    private static final Logger logger = Logger.getLogger(AnimalDAOImpl.class.getName());

    @Override
    public void cadastrarAnimal(Animal animal) throws ExceptionDAO {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Animal (especie, idadeEstimada, sexo, status, local, data, chip) VALUES(?,?,?,?,?,?,?)");
            ps.setString(1, animal.getEspecie());
            ps.setInt(2, animal.getIdadeEstimada());
            ps.setString(3, animal.getSexo());
            ps.setString(4, animal.getStatus());
            ps.setString(5, animal.getLocal());
            ps.setDate(6, java.sql.Date.valueOf(animal.getData()));
            ps.setInt(7, animal.getChip());
            ps.executeUpdate();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Erro ao cadastrar animal", ex);
            throw new ExceptionDAO("Erro ao cadastrar animal", ex);
        }
    }
    @Override
    public List<Animal> buscarAnimais() {
        List<Animal> animais = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Animal");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Animal animal = new Animal();
                animal.setId(rs.getInt("id"));
                animal.setEspecie(rs.getString("especie"));
                animal.setIdadeEstimada(rs.getInt("idadeEstimada"));
                animal.setSexo(rs.getString("sexo"));
                animal.setStatus(rs.getString("status"));
                animal.setLocal(rs.getString("local"));
                animal.setData(rs.getDate("data").toLocalDate());
                animal.setChip(rs.getInt("chip"));
                animais.add(animal);
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Erro ao buscar animais", ex);
        }
        return animais;
    }

    @Override
    public void atualizarAnimal(Animal animal) throws ExceptionDAO {
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE Animal SET especie=?, idadeEstimada=?, sexo=?, status=?, local=?, data=?, chip=? WHERE idAnimal=?");
            ps.setString(1, animal.getEspecie());
            ps.setInt(2, animal.getIdadeEstimada());
            ps.setString(3, animal.getSexo());
            ps.setString(4, animal.getStatus());
            ps.setString(5, animal.getLocal());
            ps.setDate(6, java.sql.Date.valueOf(animal.getData()));
            ps.setInt(7, animal.getChip());
            ps.setInt(8, animal.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Erro ao atualizar animal", ex);
            throw new ExceptionDAO("Erro ao atualizar animal", ex);
        }
    }

    @Override
    public void removerAnimal(int id) throws ExceptionDAO {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Animal WHERE idAnimal=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Erro ao remover animal", ex);
            throw new ExceptionDAO("Erro ao remover animal", ex);
        }
    }

    @Override
    public Animal buscarAnimal(int id) {
        Animal animal = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Animal WHERE idAnimal=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                animal = new Animal();
                animal.setId(rs.getInt("idAnimal"));
                animal.setEspecie(rs.getString("especie"));
                animal.setIdadeEstimada(rs.getInt("idadeEstimada"));
                animal.setSexo(rs.getString("sexo"));
                animal.setStatus(rs.getString("status"));
                animal.setLocal(rs.getString("local"));
                animal.setData(rs.getDate("data").toLocalDate());
                animal.setChip(rs.getInt("chip"));
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Erro ao buscar animal", ex);
        }
        return animal;
    }

    @Override
    public void setConnection(Connection connection) {
        this.conn = connection;
    }

}