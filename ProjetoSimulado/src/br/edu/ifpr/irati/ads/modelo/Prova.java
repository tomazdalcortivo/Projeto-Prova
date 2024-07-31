package br.edu.ifpr.irati.ads.modelo;

import java.util.LinkedList;
import java.util.List;

public class Prova {
    
    private int id;
    private int ano;
    private List<Questao> questoes;

    public Prova() {
        id = 0;
        ano = 0;
        questoes = new LinkedList<>();
    }

    public Prova(int id, int ano, List<Questao> questoes) {
        this.id = id;
        this.ano = ano;
        this.questoes = questoes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public List<Questao> getQuestoes() {
        return questoes;
    }

    public void setQuestoes(List<Questao> questoes) {
        this.questoes = questoes;
    }
    
    
    
}
