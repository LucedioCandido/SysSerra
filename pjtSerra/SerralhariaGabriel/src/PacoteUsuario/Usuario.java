package PacoteUsuario;

/**
 *
 * @author 20161134110005
 */
public class Usuario {

    private String Login_Usuario;
    private String Nome_Usuario;
    private String Senha;
    private String Restricao;

    public String getLogin_Usuario() {
        return Login_Usuario;
    }

    public void setLogin_Usuario(String Login_Usuario) {
        this.Login_Usuario = Login_Usuario;
    }

    public String getNome_Usuario() {
        return Nome_Usuario;
    }

    public void setNome_Usuario(String Nome_Usuario) {
        this.Nome_Usuario = Nome_Usuario;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String Senha) {
        this.Senha = Senha;
    }

    public String getRestricao() {
        return Restricao;
    }

    public void setRestricao(String Restricao) {
        this.Restricao = Restricao;
    }

}
