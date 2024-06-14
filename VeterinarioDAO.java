package VeterinarioDAO;

import Classes.Veterinario;
import Exception.ExceptionDAO;
import java.sql.Connection;
import java.util.List;

public interface VeterinarioDAO {
    void cadastrarVeterinario(Veterinario veterinario) throws ExceptionDAO;
    List<Veterinario> buscarVeterinarios();
    void atualizarVeterinario(Veterinario veterinario) throws ExceptionDAO;
    void removerVeterinario(int id) throws ExceptionDAO;
    Veterinario buscarVeterinario(int id);
    boolean existe(Veterinario veterinario) throws ExceptionDAO;
    void setConnection(Connection conn);
}
