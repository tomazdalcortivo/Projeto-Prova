package br.edu.ifpr.irati.ads.dao;

import br.edu.ifpr.irati.ads.modelo.Alternativa;
import br.edu.ifpr.irati.ads.modelo.Disciplina;
import br.edu.ifpr.irati.ads.modelo.Prova;
import br.edu.ifpr.irati.ads.modelo.Questao;
import java.util.ArrayList;
import java.util.List;

public class ProvaDAO {
    
    
    public Prova carregarProva(){
        
        Prova p = new Prova();
        p.setId(1);
        p.setAno(2023);
        List<Questao> questoes = new ArrayList<>();
        //adicionar as questões
        Alternativa altq1[] = new Alternativa[4];
        altq1[0] = new Alternativa(1,"No trecho \"Eu acho que ele é uma droga de liberação lenta\" há uma oração subordinada substantiva objetiva direta introduzida pelo termo que\"", 1);
        altq1[1] = new Alternativa(2,"No trecho \"Eu acho que ele é uma droga de liberação lenta\" há uma oração subordinada substantiva objetiva direta introduzida pelo termo que\"", 2);
        altq1[2] = new Alternativa(3,"No trecho \"Eu acho que ele é uma droga de liberação lenta\" há uma oração subordinada substantiva objetiva direta introduzida pelo termo que\"", 4);
        altq1[3] = new Alternativa(4,"No trecho \"Eu acho que ele é uma droga de liberação lenta\" há uma oração subordinada substantiva objetiva direta introduzida pelo termo que\"", 8);
        Questao q1 = new Questao(1, 1,
                new Disciplina(1,"Língua Portuguesa"),
                "", 
                "De acordo com as normas da língua portuguesa, assinale o que for correto.", 
                "/imagens_questoes/img_q12_2023.png", 
                altq1, 5);
        questoes.add(q1);
        
        
        Alternativa altq2[] = new Alternativa[4];
        altq2[0] = new Alternativa(1,"Podemos afirmar que a fratura social à qual se refere o professor João Cezar de Castro Rocha está ligada ao fato de a violência ser muitas vezes naturalizada e oculta em nosso país", 1);
        altq2[1] = new Alternativa(1,"Podemos afirmar que a fratura social à qual se refere o professor João Cezar de Castro Rocha está ligada ao fato de a violência ser muitas vezes naturalizada e oculta em nosso país", 2);
        altq2[2] = new Alternativa(1,"Podemos afirmar que a fratura social à qual se refere o professor João Cezar de Castro Rocha está ligada ao fato de a violência ser muitas vezes naturalizada e oculta em nosso país", 4);
        altq2[3] = new Alternativa(1,"Podemos afirmar que a fratura social à qual se refere o professor João Cezar de Castro Rocha está ligada ao fato de a violência ser muitas vezes naturalizada e oculta em nosso país", 8);
        Questao q2 = new Questao(2, 2, 
                new Disciplina(1,"Português"),
                "", 
                "Conforme o texto, assinale o que for correto:", 
                "", 
                altq2, 8);
        questoes.add(q2);      
        p.setQuestoes(questoes);
        return p;
    }
    
}
