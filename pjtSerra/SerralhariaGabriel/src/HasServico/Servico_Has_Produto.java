/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HasServico;

import PacoteServico.*;

/**
 *
 * @author 20161134110008
 */
public class Servico_Has_Produto {

    private int Cod_Servico;
    private int Cod_Produto;
    private String Descricao;
    private int Quantidade;
    private String Tamanho;
    private String Medida;
    private double Preco;
    private double Preco_Revenda;

    public int getCod_Servico() {
        return Cod_Servico;
    }

    public void setCod_Servico(int Cod_Servico) {
        this.Cod_Servico = Cod_Servico;
    }

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

    public String getTamanho() {
        return Tamanho;
    }

    public void setTamanho(String Tamanho) {
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
