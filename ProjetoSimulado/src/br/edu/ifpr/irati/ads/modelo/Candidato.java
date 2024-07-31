package br.edu.ifpr.irati.ads.modelo;

public class Candidato {
    
    private int id;
    private String cpf;

    public Candidato() {
        cpf = "";
        id = 0;
    }

    public Candidato(int id, String cpf) {
        this.id = id;
        this.cpf = cpf;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    
    
}
