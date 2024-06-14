package Classes;

/**
 *
 * @author giova
 */
public class Empresa {
    private String nome;
    private String senha;
    
     public Empresa(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }
     
      public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }
  
}