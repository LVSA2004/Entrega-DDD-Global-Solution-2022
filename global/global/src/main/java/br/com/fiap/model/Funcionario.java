package br.com.fiap.model;

public class Funcionario {
    

private String nome;
private String telefone;
private String email;
private String endereco;
private String login;
private String senha;



public Funcionario(String nome, String telefone, String email, String endereco, String login, String senha) {
    this.nome = nome;
    this.telefone = telefone;
    this.email = email;
    this.endereco = endereco;
    this.login = login;
    this.senha = senha;
}



public String getNome() {
    return nome;
}



public void setNome(String nome) {
    this.nome = nome;
}



public String getTelefone() {
    return telefone;
}



public void setTelefone(String telefone) {
    this.telefone = telefone;
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



public String getLogin() {
    return login;
}



public void setLogin(String login) {
    this.login = login;
}



public String getSenha() {
    return senha;
}



public void setSenha(String senha) {
    this.senha = senha;
}



@Override
public String toString() {
    return "Cliente [nome=" + nome + ", telefone=" + telefone + ", email=" + email + ", endereco=" + endereco +  ", login=" + login + ", senha=" + senha + "]";
}



}
