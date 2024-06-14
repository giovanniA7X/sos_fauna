package Classes;

import java.time.LocalDate;

/**
 *
 * @author giova
 */
public class Animal {
    private int id;
    private String especie;
    private int idadeEstimada;
    private String sexo;
    private String status;
    private String local;
    private LocalDate data;
    private int chip;
    

    
     public Animal(String especie, int idadeEstimada, String sexo, String status, String local, LocalDate data, int chip) {
        this.especie = especie;
        this.idadeEstimada = idadeEstimada;
        this.sexo = sexo;
        this.status = status;
        this.local = local;
        this.data = data;
        this.chip = chip;
    }

    public Animal() {
       
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public int getIdadeEstimada() {
        return idadeEstimada;
    }

    public void setIdadeEstimada(int idadeEstimada) {
        this.idadeEstimada = idadeEstimada;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getChip() {
        return chip;
    }

    public void setChip(int chip) {
        this.chip = chip;
    }
    
}