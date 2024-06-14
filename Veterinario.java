package Classes;

/**
 *
 * @author giova
 */
public class Veterinario {
    private int id;
    private String nome;
    private String senha;
    private String crmv;
    private String cpf;
    private String email;
    private String endereco;
    private String telefone;
    
     public Veterinario(String nome, String senha, String crmv, String cpf, String email, String endereco, String telefone) {
        this.nome = nome;
        this.senha = senha;
        this.crmv = crmv;
        this.cpf = cpf;
        this.email = email;
        this.endereco = endereco;
        this.telefone = telefone;
    }
    public Veterinario(){
         
    }

    public Veterinario(String nome, String senha) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCrmv() {
        return crmv;
    }

    public void setCrmv(String crmv) {
        this.crmv = crmv;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}