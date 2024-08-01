package br.edu.ifpr.irati.ads.dao;

import br.edu.ifpr.irati.ads.modelo.Prova;

public class TesteProva {

    public static void main(String[] args) {

        ProvaDAO provaDAO = new ProvaDAO();
        Prova prova = provaDAO.carregarProva();
        provaDAO.salvarProva(prova);
        System.out.println("Prova salva com sucesso!");

    }

}
