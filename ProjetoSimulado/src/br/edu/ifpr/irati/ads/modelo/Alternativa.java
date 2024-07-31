package br.edu.ifpr.irati.ads.modelo;

public class Alternativa {
    
    private int id;
    private String texto;
    private int valor;

    public Alternativa() {
        id = 0;
        texto = "";
        valor = 0;
    }

    public Alternativa(int id, String texto, int valor) {
        this.id = id;
        this.texto = texto;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
    
    
}
