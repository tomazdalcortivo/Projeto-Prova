package br.edu.ifpr.irati.ads.dao;

import br.edu.ifpr.irati.ads.modelo.Prova;

public class TesteProva {

    public static void main(String[] args) {
        
        ProvaDAO provaDAO = new ProvaDAO();
        Prova p = provaDAO.carregarProva();
        System.out.println(p.getQuestoes().get(0).getAlternativas()[0].getTexto());
        
    }
    
}
