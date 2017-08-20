package br.com.filipe1309.ichat.modelo;

public class Mensagem {

    private String texto;
    private int id;

    public Mensagem(int id, String texto) {
        this.id = id;
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }

    public int getId() {
        return id;
    }
}
