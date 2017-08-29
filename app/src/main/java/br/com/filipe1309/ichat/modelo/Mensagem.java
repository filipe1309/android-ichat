package br.com.filipe1309.ichat.modelo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Mensagem implements Serializable {

    @SerializedName("text")
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
