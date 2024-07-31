package br.edu.ifpr.irati.ads.modelo;

public class Resposta {
    
    private int id;
    private Questao questao;
    private Candidato candidato;
    private int soma;

    public Resposta() {
        id = 0;
        questao = new Questao();
        candidato = new Candidato();
        soma = 0;
    }

    public Resposta(int id, Questao questao, Candidato candidato, int soma) {
        this.id = id;
        this.questao = questao;
        this.candidato = candidato;
        this.soma = soma;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Questao getQuestao() {
        return questao;
    }

    public void setQuestao(Questao questao) {
        this.questao = questao;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public int getSoma() {
        return soma;
    }

    public void setSoma(int soma) {
        this.soma = soma;
    }
    
    
    
}
