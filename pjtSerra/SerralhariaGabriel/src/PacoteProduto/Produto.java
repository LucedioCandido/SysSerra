package PacoteProduto;

/**
 *
 * @author 20161134110005
 */
public class Produto {

    private int Cod_Produto;
    private String Descricao;
    private int Quantidade;
    private double Tamanho;
    private String Medida;
    private double Preco;
    private double Preco_Revenda;

    public int getCod_Produto() {
        return Cod_Produto;
    }

    public void setCod_Produto(int Cod_Produto) {
        this.Cod_Produto = Cod_Produto;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public int getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(int Quantidade) {
        this.Quantidade = Quantidade;
    }

    public double getTamanho() {
        return Tamanho;
    }

    public void setTamanho(double Tamanho) {
        this.Tamanho = Tamanho;
    }

    public String getMedida() {
        return Medida;
    }

    public void setMedida(String Medida) {
        this.Medida = Medida;
    }

    public double getPreco() {
        return Preco;
    }

    public void setPreco(double Preco) {
        this.Preco = Preco;
    }

    public double getPreco_Revenda() {
        return Preco_Revenda;
    }

    public void setPreco_Revenda(double Preco_Revenda) {
        this.Preco_Revenda = Preco_Revenda;
    }

}
