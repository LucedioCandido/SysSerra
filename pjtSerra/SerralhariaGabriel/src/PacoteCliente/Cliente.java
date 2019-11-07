package PacoteCliente;

/**
 *
 * @author 20161134110005
 */
public class Cliente {

    //Campos ou atributos da classe Cliente
    private String Cpf_Cliente;//cpf do cliente
    private String Nome_Cliente;//nome do cliente
    private String Rua_Cliente;//rua do cliente
    private int Numero_Cliente;//numero da casa do cliente
    private String Bairro_Cliente;//bairro do cliente
    private String Cidade_Cliente;//cidade do cliente
    private String Tel_Cliente;//telefone composto do cliente
    private String Estado_Cliente; // estado do cliente

    public String getEstado_Cliente() {
        return Estado_Cliente;
    }

    public void setEstado_Cliente(String Estado_Cliente) {
        this.Estado_Cliente = Estado_Cliente;
    }
    

    //métodos acessores de todos os campos referentes a classe Cliente
    //método que pega o cpf do cliente
    public String getCpf_Cliente() {
        return Cpf_Cliente;
    }

    //método que seta o cpf do cliente
    public void setCpf_Cliente(String Cpf_Cliente) {
        this.Cpf_Cliente = Cpf_Cliente;
    }

    //método que pega o nome do cliente
    public String getNome_Cliente() {
        return Nome_Cliente;
    }

    //método que seta o cpf do cliente
    public void setNome_Cliente(String Nome_Cliente) {
        this.Nome_Cliente = Nome_Cliente;
    }

    //método que pega a rua do cliente
    public String getRua_Cliente() {
        return Rua_Cliente;
    }

    //método que seta a rua do cliente
    public void setRua_Cliente(String Rua_Cliente) {
        this.Rua_Cliente = Rua_Cliente;
    }

    //método que pega a rua do cliente
    public int getNumero_Cliente() {
        return Numero_Cliente;
    }

    //método que seta o número do cliente
    public void setNumero_Cliente(int Numero_Cliente) {
        this.Numero_Cliente = Numero_Cliente;
    }

    //método que pega o bairro do cliente
    public String getBairro_Cliente() {
        return Bairro_Cliente;
    }

    //método que seta o bairro do cliente
    public void setBairro_Cliente(String Bairro_Cliente) {
        this.Bairro_Cliente = Bairro_Cliente;
    }

    //método que pega a cidade do cliente
    public String getCidade_Cliente() {
        return Cidade_Cliente;
    }

    //método que seta a cidade do cliente
    public void setCidade_Cliente(String Cidade_Cliente) {
        this.Cidade_Cliente = Cidade_Cliente;
    }

    //método que pega o telefone composto do cliente
    public String getTel_Cliente() {
        return Tel_Cliente;
    }

    //método que seta o telefone composto do cliente
    public void setTel_Cliente(String Tel_Cliente) {
        this.Tel_Cliente = Tel_Cliente;
    }

}
