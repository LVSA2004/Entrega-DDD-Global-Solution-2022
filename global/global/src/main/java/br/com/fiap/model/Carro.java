package br.com.fiap.model;

public class Carro {
    
    private String nome;
    private String modelo;
    private int ano;
    private String placa;
    private String aluguel;

    public Carro(String nome, String modelo, int ano, String placa, String aluguel) {
        this.nome = nome;
        this.modelo = modelo;
        this.ano = ano;
        this.placa = placa;
        this.aluguel = aluguel;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getAluguel() {
        return aluguel;
    }

    public void setAluguel(String aluguel) {
        this.aluguel = aluguel;
    }

    @Override
    public String toString() {
        return "Carro [nome=" + nome + ", modelo=" + modelo + ", ano=" + ano + ", placa=" + placa + "]";
    }
    
    

    
}
