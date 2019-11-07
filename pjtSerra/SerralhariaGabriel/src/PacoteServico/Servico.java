package PacoteServico;

public class Servico {

    private int Cod_Servico;
    private String Descricao_Servico;
    private String Cpf_Cliente;
    private String Data_Inicio;
    private String Data_Entrega;
    private String Pagamento;
    private double Valor;
    private String Servico_Status;
    private double mao_Obra;
    private String Login_Usuario;
    private double QuantidadeM2;
    private double Lucro;

    public double getLucro() {
        return Lucro;
    }

    public void setLucro(double Lucro) {
        this.Lucro = Lucro;
    }

    public int getCod_Servico() {
        return Cod_Servico;
    }

    public void setCod_Servico(int Cod_Servico) {
        this.Cod_Servico = Cod_Servico;
    }

    public String getDescricao_Servico() {
        return Descricao_Servico;
    }

    public void setDescricao_Servico(String Descricao_Servico) {
        this.Descricao_Servico = Descricao_Servico;
    }

    public String getCpf_Cliente() {
        return Cpf_Cliente;
    }

    public void setCpf_Cliente(String Cpf_Cliente) {
        this.Cpf_Cliente = Cpf_Cliente;
    }

    public String getData_Inicio() {
        return Data_Inicio;
    }

    public void setData_Inicio(String Data_Inicio) {
        this.Data_Inicio = Data_Inicio;
    }

    public String getData_Entrega() {
        return Data_Entrega;
    }

    public void setData_Entrega(String Data_Entrega) {
        this.Data_Entrega = Data_Entrega;
    }

    public String getPagamento() {
        return Pagamento;
    }

    public void setPagamento(String Pagamento) {
        this.Pagamento = Pagamento;
    }

    public Double getValor() {
        return Valor;
    }

    public void setValor(Double Valor) {
        this.Valor = Valor;
    }

    public String getServico_Status() {
        return Servico_Status;
    }

    public void setServico_Status(String Servico_Status) {
        this.Servico_Status = Servico_Status;
    }

    public Double getMao_Obra() {
        return mao_Obra;
    }

    public void setMao_Obra(Double mao_Obra) {
        this.mao_Obra = mao_Obra;
    }

    public String getLogin_Usuario() {
        return Login_Usuario;
    }

    public void setLogin_Usuario(String Login_Usuario) {
        this.Login_Usuario = Login_Usuario;
    }

    public double getQuantidadeM2() {
        return QuantidadeM2;
    }

    public void setQuantidadeM2(double QuantidadeM2) {
        this.QuantidadeM2 = QuantidadeM2;
    }

}
