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
        
        Alternativa altq3[] = new Alternativa[4];
        altq3[0] = new Alternativa(1, " Vibrio cholerae é uma bactéria do tipo vibrião. É o agente etiológico da cólera, doença intestinal grave. A infecção humana ocorre tipicamente pela via fecal-oral, ou seja, pela ingestão de água e de alimentos contaminados.", 1);
        altq3[1] = new Alternativa(2, " Vibrio cholerae é uma bactéria do tipo vibrião. É o agente etiológico da cólera, doença intestinal grave. A infecção humana ocorre tipicamente pela via fecal-oral, ou seja, pela ingestão de água e de alimentos contaminados.", 2);
        altq3[2] = new Alternativa(3, " Vibrio cholerae é uma bactéria do tipo vibrião. É o agente etiológico da cólera, doença intestinal grave. A infecção humana ocorre tipicamente pela via fecal-oral, ou seja, pela ingestão de água e de alimentos contaminados.", 4);
        altq3[3] = new Alternativa(4, " Vibrio cholerae é uma bactéria do tipo vibrião. É o agente etiológico da cólera, doença intestinal grave. A infecção humana ocorre tipicamente pela via fecal-oral, ou seja, pela ingestão de água e de alimentos contaminados.", 8);
        Questao q3 = new Questao(3, 3,
                new Disciplina(3, "Biologia"),
                "",
                "Sobre as bactérias e a saúde humana, assinale o que for correto.",
                "", altq3, 0);
        questoes.add(q3);

        Alternativa altq4[] = new Alternativa[4];
        altq4[0] = new Alternativa(1, "O preservativo (também chamado popularmente de camisinha) é um método anticoncepcional muito eficaz e ainda previne a transmissão de infecções sexualmente transmissíveis (chamadas anteriormente de doenças sexualmente transmissíveis).", 1);
        altq4[1] = new Alternativa(2, "O preservativo (também chamado popularmente de camisinha) é um método anticoncepcional muito eficaz e ainda previne a transmissão de infecções sexualmente transmissíveis (chamadas anteriormente de doenças sexualmente transmissíveis).", 2);
        altq4[2] = new Alternativa(3, "O preservativo (também chamado popularmente de camisinha) é um método anticoncepcional muito eficaz e ainda previne a transmissão de infecções sexualmente transmissíveis (chamadas anteriormente de doenças sexualmente transmissíveis).", 4);
        altq4[3] = new Alternativa(4, "O preservativo (também chamado popularmente de camisinha) é um método anticoncepcional muito eficaz e ainda previne a transmissão de infecções sexualmente transmissíveis (chamadas anteriormente de doenças sexualmente transmissíveis).", 8);
        Questao q4 = new Questao(4, 4,
                new Disciplina(3, "Biologia"),
                "",
                "Sobre os métodos contraceptivos, assinale o que for correto.",
                "", altq4, 0);
        questoes.add(q4);

        Alternativa altq5[] = new Alternativa[4];
        altq5[0] = new Alternativa(1, "Temos 240 números pares com três algarismos distintos.", 1);
        altq5[1] = new Alternativa(2, "Temos 240 números pares com três algarismos distintos.", 2);
        altq5[2] = new Alternativa(3, "Temos 240 números pares com três algarismos distintos.", 4);
        altq5[3] = new Alternativa(4, "Temos 240 números pares com três algarismos distintos.", 8);
        Questao q5 = new Questao(5, 5,
                new Disciplina(3, "Matemática"),
                "",
                "Considerando os números de dois, três, quatro e cinco algarismos distintos formados utilizando-se apenas",
                "", altq5, 3);
        questoes.add(q5);

        Alternativa altq6[] = new Alternativa[4];
        altq6[0] = new Alternativa(1, "O composto fenol (hidroxibenzeno) exibe uma cadeia carbônica aromática e mononuclear.", 1);
        altq6[1] = new Alternativa(2, "O composto fenol (hidroxibenzeno) exibe uma cadeia carbônica aromática e mononuclear.", 2);
        altq6[2] = new Alternativa(4, "O composto fenol (hidroxibenzeno) exibe uma cadeia carbônica aromática e mononuclear.", 4);
        altq6[3] = new Alternativa(8, "O composto fenol (hidroxibenzeno) exibe uma cadeia carbônica aromática e mononuclear.", 8);
        Questao q6 = new Questao(6, 6,
                new Disciplina(3, "Química"),
                "",
                "A respeito da classificação de cadeias carbônicas em compostos orgânicos, assinale o que for correto.",
                "", altq6, 15);
        questoes.add(q6);

        Alternativa altq7[] = new Alternativa[4];
        altq7[0] = new Alternativa(1, "Suécia e Dinamarca são países nórdicos da Europa que ainda são monarquias.", 1);
        altq7[1] = new Alternativa(2, "Suécia e Dinamarca são países nórdicos da Europa que ainda são monarquias.", 2);
        altq7[2] = new Alternativa(4, "Suécia e Dinamarca são países nórdicos da Europa que ainda são monarquias.", 4);
        altq7[3] = new Alternativa(8, "Suécia e Dinamarca são países nórdicos da Europa que ainda são monarquias.", 8);
        Questao q7 = new Questao(7, 7,
                new Disciplina(4, "História"),
                "",
                "Sobre as monarquias no mundo contemporâneo, assinale o que for correto.",
                "", altq7, 5);
        questoes.add(q7);

        Alternativa altq8[] = new Alternativa[4];
        altq8[0] = new Alternativa(1, "A área da educação, do nível básico ao superior, se inscreve como importante atividade econômica do terceiro setor em países desenvolvidos.", 1);
        altq8[1] = new Alternativa(2, "A área da educação, do nível básico ao superior, se inscreve como importante atividade econômica do terceiro setor em países desenvolvidos.", 2);
        altq8[2] = new Alternativa(4, "A área da educação, do nível básico ao superior, se inscreve como importante atividade econômica do terceiro setor em países desenvolvidos.", 4);
        altq8[3] = new Alternativa(8, "A área da educação, do nível básico ao superior, se inscreve como importante atividade econômica do terceiro setor em países desenvolvidos.", 8);
        Questao q8 = new Questao(8, 8,
                new Disciplina(5, "Geografia"),
                "",
                "Sobre o setor terciário da economia, assinale o que for correto.",
                "", altq8, 6);
        questoes.add(q8);

        Alternativa altq9[] = new Alternativa[4];
        altq9[0] = new Alternativa(1, "A probabilidade de a soma ser ímpar é 2/5.", 1);
        altq9[1] = new Alternativa(2, "A probabilidade de a soma ser ímpar é 2/5.", 2);
        altq9[2] = new Alternativa(4, "A probabilidade de a soma ser ímpar é 2/5.", 4);
        altq9[3] = new Alternativa(8, "A probabilidade de a soma ser ímpar é 2/5.", 8);
        Questao q9 = new Questao(9, 9,
                new Disciplina(6, "Matemática"),
                "",
                " Em uma caixa, há seis cartas numeradas de 1 a 6.\n"
                + "Considerando que serão retiradas duas cartas, ao acaso\n"
                + "e sem reposição, e que será calculada a soma dos\n"
                + "números escritos nessas cartas, assinale o que for correto.\n\n"
                + "A probabilidade de a soma ser ímpar é 2/5.",
                "", altq9, 15);
        questoes.add(q9);

        Alternativa altq10[] = new Alternativa[4];
        altq10[0] = new Alternativa(1, "A área do trapézio é 26 centímetros quadrados.", 1);
        altq10[1] = new Alternativa(2, "A área do trapézio é 26 centímetros quadrados.", 2);
        altq10[2] = new Alternativa(4, "A área do trapézio é 26 centímetros quadrados.", 4);
        altq10[3] = new Alternativa(8, "A área do trapézio é 26 centímetros quadrados.", 8);

        Questao q10 = new Questao(10, 10,
                new Disciplina(3, "Matemática"),
                "",
                "Sobre o trapézio, assinale o que for correto.\n\n"
                + "Se as bases de um trapézio medem, respectivamente, 10 centímetros e 3 centímetros, e a medida de cada um dos outros dois lados é de 5 centímetros, então sua área é de 26 centímetros quadrados.",
                "", altq10, 0);

        questoes.add(q10);
        return p;
    }
    
}
