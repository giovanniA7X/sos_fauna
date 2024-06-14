package AnimalDAO;

import Classes.Animal;
import Exception.ExceptionDAO;


import java.sql.Connection;
import java.util.List;

public interface AnimalDAO {

    void cadastrarAnimal(Animal animal) throws ExceptionDAO;
    List<Animal> buscarAnimais();
    void atualizarAnimal(Animal animal) throws ExceptionDAO;
    void removerAnimal(int id) throws ExceptionDAO;
    Animal buscarAnimal(int id);
    void setConnection(Connection connection);
    
}
